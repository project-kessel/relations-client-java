package org.project_kessel.relations.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.enterprise.util.Nonbinding;
import jakarta.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@InterceptorBinding
@Retention(RUNTIME)
@Target({ElementType.TYPE, METHOD})
public @interface AuthzPostFilter {
    @Nonbinding String permission();
}
