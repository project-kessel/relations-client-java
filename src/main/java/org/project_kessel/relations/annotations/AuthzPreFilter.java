package org.project_kessel.relations.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.enterprise.util.Nonbinding;
import jakarta.interceptor.InterceptorBinding;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@InterceptorBinding
@Retention(RUNTIME)
@Target({ElementType.TYPE, METHOD})
@FilterDef(name = AuthzPreFilter.FILTER_NAME,
        parameters = @ParamDef(name = AuthzPreFilter.PARAM_NAME, type = String.class))
public @interface AuthzPreFilter {
    String FILTER_NAME = "authzPreFilter";
    String PARAM_NAME = "id";

    @Nonbinding String permission();
}
