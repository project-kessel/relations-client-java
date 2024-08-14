package org.project_kessel.relations.client.authn;

import io.grpc.CallCredentials;
import io.grpc.auth.MoreCallCredentials;

import java.time.Duration;
import java.util.Optional;

import org.project_kessel.relations.client.Config;
import org.project_kessel.relations.client.authn.oidc.client.OIDCClientCredentialsCallCredentials;

import com.google.auth.oauth2.OAuth2Credentials;
import com.google.auth.oauth2.OAuth2CredentialsWithRefresh;
import com.google.auth.oauth2.OAuth2CredentialsWithRefresh.OAuth2RefreshHandler;

import org.project_kessel.relations.client.authn.oidc.client.ClientCredentialsRefreshers;

public class CallCredentialsFactory {

    private CallCredentialsFactory() {

    }

    public static CallCredentials create(Config.AuthenticationConfig authnConfig)
            throws CallCredentialsCreationException {
        if (authnConfig == null) {
            throw new CallCredentialsCreationException(
                    "AuthenticationConfig is required to create CallCredentials and must not be null.");
        }

        try {
            switch (authnConfig.mode()) {
                case DISABLED:
                    return null;
                case OIDC_CLIENT_CREDENTIALS:
                    var refresher = clientCredentialsRefresher(authnConfig);
                    var creds = OAuth2CredentialsWithRefresh.newBuilder()
                            .setRefreshHandler(refresher)
                            .setRefreshMargin(Duration.ofSeconds(60))
                            .build();
                    return MoreCallCredentials.from(creds);
            }
        } catch (OIDCClientCredentialsCallCredentialsException e) {
            throw new CallCredentialsCreationException("Failed to create OIDCClientCredentialsCallCredentials.", e);
        }

        return null;
    }

    public static class CallCredentialsCreationException extends Exception {
        public CallCredentialsCreationException(String message) {
            super(message);
        }

        public CallCredentialsCreationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private static OAuth2RefreshHandler clientCredentialsRefresher(Config.AuthenticationConfig authnConfig)
            throws OIDCClientCredentialsCallCredentialsException {
        var clientCredentialsConfig = validateAndExtractConfig(authnConfig);

        Optional<String> minterImpl = clientCredentialsConfig.oidcClientCredentialsMinterImplementation();
        try {
            if (minterImpl.isPresent()) {
                return ClientCredentialsRefreshers.forName(minterImpl.get());
            } else {
                return ClientCredentialsRefreshers.forDefaultImplementation();
            }
        } catch (ClientCredentialsRefreshers.OIDCClientCredentialsMinterException e) {
            throw new OIDCClientCredentialsCallCredentialsException(
                    "Couldn't create GrpcCallCredentials because minter impl not instantiated.", e);
        }
    }

    /*
     * We don't know that smallrye config validation will be used by clients, so do
     * some validation here.
     */

    static Config.OIDCClientCredentialsConfig validateAndExtractConfig(Config.AuthenticationConfig authnConfig)
            throws OIDCClientCredentialsCallCredentialsException {
        if (authnConfig.clientCredentialsConfig().isEmpty()) {
            throw new OIDCClientCredentialsCallCredentialsException(
                    "ClientCredentialsConfig is required for OIDC client credentials authentication method.");
        }
        if (authnConfig.clientCredentialsConfig().get().issuer() == null) {
            throw new OIDCClientCredentialsCallCredentialsException("ClientCredentialsConfig Issuer must not be null.");
        }
        if (authnConfig.clientCredentialsConfig().get().clientId() == null) {
            throw new OIDCClientCredentialsCallCredentialsException(
                    "ClientCredentialsConfig Client id must not be null.");
        }
        if (authnConfig.clientCredentialsConfig().get().clientSecret() == null) {
            throw new OIDCClientCredentialsCallCredentialsException(
                    "ClientCredentialsConfig Client secret must not be null.");
        }

        return authnConfig.clientCredentialsConfig().get();
    }

    public static class OIDCClientCredentialsCallCredentialsException extends Exception {
        public OIDCClientCredentialsCallCredentialsException(String message) {
            super(message);
        }

        public OIDCClientCredentialsCallCredentialsException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
