package org.project_kessel.relations.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ApplicationScoped
public class TestBeans {
    public static final String APPLICATION_SCOPED_CLIENT_MANAGER = "ApplicationScopedClientManager";

    @Produces
    @ApplicationScoped
    @TestQualifier(APPLICATION_SCOPED_CLIENT_MANAGER)
    RelationsGrpcClientsManager getApplicationScopedRelationsGrpcClientsManager() {
        return RelationsGrpcClientsManager.forInsecureClients("localhost:8080");
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE,METHOD,FIELD,PARAMETER})
    public @interface TestQualifier {
        String value() default "";

        class Literal extends AnnotationLiteral<TestQualifier> implements TestQualifier {
            private String value;

            public Literal(String value) {
                this.value = value;
            }

            @Override
            public String value() {
                return value;
            }
        }
    }
}
