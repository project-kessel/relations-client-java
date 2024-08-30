package org.project_kessel.relations.client;

import org.project_kessel.clients.authn.AuthenticationConfig;
import org.project_kessel.clients.authn.oidc.client.OIDCClientCredentialsAuthenticationConfig;
import org.project_kessel.clients.authn.oidc.client.OIDCClientCredentialsAuthenticationConfig.OIDCClientCredentialsConfig;

public class AuthnConfigConverter {

    public static AuthenticationConfig convert(Config.AuthenticationConfig authnConfig) {
        if (authnConfig == null) {
            return null;
        }
        AuthenticationConfig convertedAuthnConfig;
        if (authnConfig.clientCredentialsConfig().isPresent()) {
            Config.OIDCClientCredentialsConfig oidcClientCredentialsConfig =
                    authnConfig.clientCredentialsConfig().get();

            convertedAuthnConfig = new OIDCClientCredentialsAuthenticationConfig();
            var convertedOidcClientCredentialsConfig = new OIDCClientCredentialsConfig();
            convertedOidcClientCredentialsConfig.setIssuer(oidcClientCredentialsConfig.issuer());
            convertedOidcClientCredentialsConfig.setClientId(oidcClientCredentialsConfig.clientId());
            convertedOidcClientCredentialsConfig.setClientSecret(oidcClientCredentialsConfig.clientSecret());
            convertedOidcClientCredentialsConfig.setScope(oidcClientCredentialsConfig.scope());
            convertedOidcClientCredentialsConfig.setOidcClientCredentialsMinterImplementation(
                    oidcClientCredentialsConfig.oidcClientCredentialsMinterImplementation());

            ((OIDCClientCredentialsAuthenticationConfig) convertedAuthnConfig).setCredentialsConfig(
                    convertedOidcClientCredentialsConfig);
        } else {
            convertedAuthnConfig = new AuthenticationConfig();
        }

        convertedAuthnConfig.setMode(authnConfig.mode());

        return convertedAuthnConfig;
    }
}
