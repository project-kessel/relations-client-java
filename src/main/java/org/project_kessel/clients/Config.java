package org.project_kessel.clients;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

import java.util.Optional;

/**
 * Interface for injecting config into container managed beans.
 * It has the current limitation that only one underlying grpc connection can be configured.
 * Does nothing if this client is not being managed by a container.
 * Works directly for Quarkus. Might need an implementation class for future Spring Boot config.
 */
@ConfigMapping(prefix = "relations-api")
public interface Config {
    enum AuthMode {
        DISABLED,
        OIDC_CLIENT_CREDENTIALS
    }

    @WithDefault("false")
    boolean isSecureClients();
    String targetUrl();

    @WithName("authn")
    Optional<AuthenticationConfig> authenticationConfig();

    interface AuthenticationConfig {
        @WithDefault("disabled")
        AuthMode mode();
        @WithName("client")
        Optional<OIDCClientCredentialsConfig> clientCredentialsConfig();
    }

     interface OIDCClientCredentialsConfig {
        String issuer();
        @WithName("id")
        String clientId();
        @WithName("secret")
        String clientSecret();
        Optional<String[]> scope();
        Optional<String> oidcClientCredentialsMinterImplementation();
    }
}
