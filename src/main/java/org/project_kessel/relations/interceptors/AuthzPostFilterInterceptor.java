package org.project_kessel.relations.interceptors;

import static org.project_kessel.relations.interceptors.utils.BeanUtils.resolveFilterableObjectType;
import static org.project_kessel.relations.interceptors.utils.BeanUtils.resolveSubject;
import static org.project_kessel.relations.interceptors.utils.BeanUtils.retrieveConverterFromContainer;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.project_kessel.api.relations.v1beta1.CheckRequest;
import org.project_kessel.api.relations.v1beta1.CheckResponse;
import org.project_kessel.api.relations.v1beta1.ObjectReference;
import org.project_kessel.api.relations.v1beta1.SubjectReference;
import org.project_kessel.relations.annotations.AuthzPostFilter;
import org.project_kessel.relations.client.CheckClient;
import org.project_kessel.relations.converters.ObjectRefConverter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AuthzPostFilter(permission = "")
@Interceptor
public class AuthzPostFilterInterceptor {
    static final String RELATIONS_SUBJECT_NAMESPACE = "rbac";
    static final String RELATIONS_SUBJECT_TYPE = "user";

    @Inject
    CheckClient checkClient;

    @AroundInvoke
    public <T> Object postFilterByAccess(InvocationContext context) throws Exception {
        SubjectReference subjectRef = null;

        try {
            /* Resolve the subject of access, or principal */
            subjectRef = resolveSubject(context);
        } catch (Throwable t) {
            throw new AuthzPreFilterInterceptor.AuthzPreFilterException(
                    "Could not resolve the subject of the authz query", t);
        }

        Class<?> returnType = context.getMethod().getReturnType();
        if (Collection.class.isAssignableFrom(returnType)) {
            try {
                /* Need to find out what type of Collection this is so we can inject the right converter */
                Type filterableType = resolveFilterableObjectType(context);
                @SuppressWarnings("unchecked")
                ObjectRefConverter<T> converter = (ObjectRefConverter<T>)
                        retrieveConverterFromContainer(filterableType);

                @SuppressWarnings("unchecked")
                var objects = (Collection<T>) context.proceed();

                String relation = context.getInterceptorBinding(AuthzPostFilter.class).permission();

                return filterByAccess(objects, converter, subjectRef, relation);
            } catch (Throwable e) {
                throw new AuthzPostFilterException("Could not filter results for access using kessel relations API", e);
            }
        } else {
            throw new AuthzPostFilterException("AuthzPostFilter can only filter a Collection of objects");
        }
    }

    <T> Collection<T> filterByAccess(Collection<T> objects, ObjectRefConverter<T> converter,
                                     SubjectReference subjectRef, String relation) {
        /* Make a copy to ensure that remove() is supported and collection is modifiable.
        *  TODO: Maybe there is a way to test for this to avoid creation in every instance. */
        Collection<T> copy = null;
        if (List.class.isAssignableFrom(objects.getClass())) {
            copy = new ArrayList<>(objects);
        } else if (Set.class.isAssignableFrom(objects.getClass())) {
            copy = new HashSet<>(objects);
        } else {
            throw new AuthzPostFilterException("Unsupported Collection for authz filtering. List and Set supported");
        }
        var iterator = copy.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            ObjectReference ref = converter.convert(item);
            boolean hasAccess = checkClient.check(
                    CheckRequest.newBuilder()
                            .setResource(ref)
                            .setRelation(relation)
                            .setSubject(subjectRef)
                    .build())
                    .getAllowed() == CheckResponse.Allowed.ALLOWED_TRUE;
            if (!hasAccess) {
                iterator.remove();
            }
        }

        return copy;
    }

    public static class AuthzPostFilterException extends RuntimeException {
        public AuthzPostFilterException(String message) {
            super(message);
        }

        public AuthzPostFilterException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
