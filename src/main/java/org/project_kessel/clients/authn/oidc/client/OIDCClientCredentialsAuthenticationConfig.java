package org.project_kessel.clients.authn.oidc.client;

import org.project_kessel.clients.authn.AuthenticationConfig;

import java.util.Optional;

public class OIDCClientCredentialsAuthenticationConfig extends AuthenticationConfig {
    OIDCClientCredentialsConfig credentialsConfig;

    public OIDCClientCredentialsConfig clientCredentialsConfig() {
        return credentialsConfig;
    }

    public void setCredentialsConfig(OIDCClientCredentialsConfig credentialsConfig) {
        this.credentialsConfig = credentialsConfig;
    }

    public static class OIDCClientCredentialsConfig {
        String issuer;
        String clientId;
        String clientSecret;
        Optional<String[]> scope;
        Optional<String> oidcClientCredentialsMinterImplementation;

        public String issuer() {
            return issuer;
        }

        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }

        public String clientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String clientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public Optional<String[]> scope() {
            return scope;
        }

        public void setScope(Optional<String[]> scope) {
            this.scope = scope;
        }

        public Optional<String> oidcClientCredentialsMinterImplementation() {
            return oidcClientCredentialsMinterImplementation;
        }

        public void setOidcClientCredentialsMinterImplementation(Optional<String> oidcClientCredentialsMinterImplementation) {
            this.oidcClientCredentialsMinterImplementation = oidcClientCredentialsMinterImplementation;
        }
    }
}
