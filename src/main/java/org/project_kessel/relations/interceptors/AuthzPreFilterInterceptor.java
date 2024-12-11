package org.project_kessel.relations.interceptors;

import static org.project_kessel.relations.interceptors.utils.BeanUtils.resolveFilterableObjectType;
import static org.project_kessel.relations.interceptors.utils.BeanUtils.resolveSubject;
import static org.project_kessel.relations.interceptors.utils.BeanUtils.retrieveConverterFromContainer;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.transaction.UserTransaction;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.project_kessel.api.relations.v1beta1.LookupResourcesRequest;
import org.project_kessel.api.relations.v1beta1.SubjectReference;
import org.project_kessel.relations.annotations.AuthzPreFilter;
import org.project_kessel.relations.client.LookupClient;
import org.project_kessel.relations.converters.ObjectRefConverter;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AuthzPreFilter(permission = "")
@Interceptor
public class AuthzPreFilterInterceptor {
    @Inject
    EntityManager entityManager;

    @Inject
    LookupClient lookupClient;

    @Inject
    UserTransaction userTransaction;

    @AroundInvoke
    public <T> Object preFilterByAccess(InvocationContext context) throws Exception {
        SubjectReference subjectRef = null;

        try {
            /* Resolve the subject of access, or principal */
            subjectRef = resolveSubject(context);
        } catch (Throwable t) {
            throw new AuthzPreFilterException("Could not resolve the subject of the authz query", t);
        }

        Class<?> returnType = context.getMethod().getReturnType();
        if (Collection.class.isAssignableFrom(returnType)) {
            try {
                /* Need to find out what type of Collection this is so we can inject the right converter */
                Type filterableType = resolveFilterableObjectType(context);
                @SuppressWarnings("unchecked")
                ObjectRefConverter<T> converter = (ObjectRefConverter<T>)
                        retrieveConverterFromContainer(filterableType);

                List<String> resourceIds = lookupResources(context, converter, subjectRef);

                if (resourceIds.isEmpty()) {
                    /* User has access to no resources. No need to query the db. */
                    return Collections.<T>emptyList();
                }

                /* Require hibernate underneath JPA/EntityManager. */
                final Session hibernateSession = entityManager.unwrap(Session.class);
                userTransaction.begin();
                /* TODO: the Filter actually needs to be per-model, since the relations-api call will be per object type
                    otherwise all annotated entities will be filtered with the ids from relations-api call. */
                Filter authzPreFilter = hibernateSession.enableFilter(AuthzPreFilter.FILTER_NAME);
                authzPreFilter.setParameterList(AuthzPreFilter.PARAM_NAME, resourceIds);
                authzPreFilter.validate();

                @SuppressWarnings("unchecked")
                var objects = (Collection<T>) context.proceed();

                hibernateSession.disableFilter(AuthzPreFilter.FILTER_NAME);
                userTransaction.commit();

                return objects;
            } catch (Throwable e) {
                throw new AuthzPreFilterException("Could not filter results for access using kessel relations API", e);
            }
        } else {
            throw new AuthzPreFilterException("AuthzPreFilter can only filter a Collection of objects");
        }
    }

    <T> List<String> lookupResources(InvocationContext context, ObjectRefConverter<T> converter,
                                     SubjectReference subjectRef) {
        var responses = lookupClient.lookupResources(LookupResourcesRequest.newBuilder()
                .setResourceType(converter.objectType())
                .setRelation(context.getInterceptorBinding(AuthzPreFilter.class).permission())
                .setSubject(subjectRef)
                .build());
        Stream<String> resourceIds = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(responses, Spliterator.ORDERED),
                false).map(i -> i.getResource().getId());
        return resourceIds.collect(Collectors.toList());
    }

    public static class AuthzPreFilterException extends RuntimeException {
        public AuthzPreFilterException(String message) {
            super(message);
        }

        public AuthzPreFilterException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
