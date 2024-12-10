package org.project_kessel.relations.interceptors.utils;

import io.quarkus.runtime.types.ParameterizedTypeImpl;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.interceptor.InvocationContext;
import org.project_kessel.api.relations.v1beta1.SubjectReference;
import org.project_kessel.relations.converters.ObjectRefConverter;
import org.project_kessel.relations.converters.SubjectRefConverter;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.Principal;

public class BeanUtils {
    public static Type resolveFilterableObjectType(InvocationContext context) {
        Class<?> returnClass = context.getMethod().getReturnType();
        if (returnClass.getTypeParameters().length == 0) {
            throw new RuntimeException(
                    "Can't resolve access filtered object type from Collection since there is no type parameter");
        }
        if (returnClass.getTypeParameters().length > 1) {
            throw new RuntimeException(
                    "Can't resolve access filtered object type from Collection due to multiple, and therefore ambiguous"
                            + ", type parameters");
        }

        return ((ParameterizedType) context.getMethod().getGenericReturnType()).getActualTypeArguments()[0];
    }

    public static <T> SubjectReference resolveSubject(InvocationContext context) throws RuntimeException {
        Parameter[] parameters = context.getMethod().getParameters();
        /* Take the first resolvable Principal from the container */
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (Principal.class.isAssignableFrom(parameter.getType())) {
                try {
                    @SuppressWarnings("unchecked")
                    SubjectRefConverter<T> subjectRefConverter = (SubjectRefConverter<T>)
                            retrieveSubjectConverterFromContainer(parameter.getType());
                    if (subjectRefConverter != null) {
                        @SuppressWarnings("unchecked")
                        T subject = (T) context.getParameters()[i];
                        return subjectRefConverter.convert(subject);
                    }
                } catch (RuntimeException e) {
                    // do nothing just yet
                }
            }
        }
        throw new RuntimeException(
                "Can't resolve SubjectReference from Subject. Probably no suitable SubjectRefConverter found");
    }

    public static SubjectRefConverter<?> retrieveSubjectConverterFromContainer(Type subjectType) {
        Class<?> converterBaseType = SubjectRefConverter.class;
        // TODO: add impl of ParameterizedType to remove quarkus dependency with ParameterizedTypeImpl
        ParameterizedTypeImpl converterType = new ParameterizedTypeImpl(converterBaseType, subjectType);

        try {
            var container = CDI.current().getBeanContainer();
            var beanManager = CDI.current().getBeanManager();
            @SuppressWarnings("unchecked")
            Bean<SubjectRefConverter<?>> converterBean = (Bean<SubjectRefConverter<?>>)
                    container.resolve(container.getBeans(converterType));
            return (SubjectRefConverter<?>) beanManager.getReference(converterBean, converterBean.getBeanClass(),
                    beanManager.createCreationalContext(converterBean));
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't locate or instantiate an SubjectRefConverter bean for type "
                    + subjectType.getTypeName(), e);
        }
    }

    public static ObjectRefConverter<?> retrieveConverterFromContainer(Type filterableType) {
        Class<?> converterBaseType = ObjectRefConverter.class;
        // TODO: add impl of ParameterizedType to remove quarkus dependency with ParameterizedTypeImpl
        ParameterizedTypeImpl converterType = new ParameterizedTypeImpl(converterBaseType, filterableType);

        try {
            var container = CDI.current().getBeanContainer();
            var beanManager = CDI.current().getBeanManager();
            @SuppressWarnings("unchecked")
            Bean<ObjectRefConverter<?>> converterBean = (Bean<ObjectRefConverter<?>>)
                    container.resolve(container.getBeans(converterType));
            return (ObjectRefConverter<?>) beanManager.getReference(converterBean, converterBean.getBeanClass(),
                    beanManager.createCreationalContext(converterBean));
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't locate or instantiate an ObjectRefConverter bean for type "
                    + filterableType.getTypeName(), e);
        }
    }
}
