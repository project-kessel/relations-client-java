package org.project_kessel.relations.client.authn;

import io.grpc.CallCredentials;
import org.project_kessel.relations.client.Config;
import org.project_kessel.relations.client.authn.oidc.client.OIDCClientCredentialsCallCredentials;

public class CallCredentialsFactory {

    public static CallCredentials create(Config.AuthenticationConfig authnConfig) throws CallCredentialsCreationException {
        if (authnConfig == null) {
            throw new CallCredentialsCreationException("AuthenticationConfig is required to create CallCredentials and must not be null.");
        }

        try {
            switch (authnConfig.mode()) {
                case DISABLED:
                    return null;
                case OIDC_CLIENT_CREDENTIALS:
                    return new OIDCClientCredentialsCallCredentials(authnConfig);
            }
        } catch (OIDCClientCredentialsCallCredentials.OIDCClientCredentialsCallCredentialsException e) {
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
}
