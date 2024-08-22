package org.project_kessel.clients.authn.oidc.client;

import io.grpc.Metadata;
import io.grpc.Status;
import org.project_kessel.clients.authn.AuthenticationConfig;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public class OIDCClientCredentialsCallCredentials extends io.grpc.CallCredentials {
    static final Metadata.Key<String> authorizationKey = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    private final OIDCClientCredentialsAuthenticationConfig.OIDCClientCredentialsConfig clientCredentialsConfig;
    private final OIDCClientCredentialsMinter minter;

    private final AtomicReference<OIDCClientCredentialsMinter.BearerHeader> storedBearerHeaderRef = new AtomicReference<>();

    public OIDCClientCredentialsCallCredentials(AuthenticationConfig authnConfig) throws OIDCClientCredentialsCallCredentialsException {
        this.clientCredentialsConfig = validateAndExtractConfig(authnConfig);

        Optional<String> minterImpl = clientCredentialsConfig.oidcClientCredentialsMinterImplementation();
        try {
            if(minterImpl.isPresent()) {
                this.minter = OIDCClientCredentialsMinter.forName(minterImpl.get());
            } else {
                this.minter = OIDCClientCredentialsMinter.forDefaultImplementation();
            }
        } catch (OIDCClientCredentialsMinter.OIDCClientCredentialsMinterException e) {
            throw new OIDCClientCredentialsCallCredentialsException("Couldn't create GrpcCallCredentials because minter impl not instantiated.", e);
        }
    }

    OIDCClientCredentialsCallCredentials(OIDCClientCredentialsAuthenticationConfig.OIDCClientCredentialsConfig clientCredentialsConfig, OIDCClientCredentialsMinter minter) {
        this.clientCredentialsConfig = clientCredentialsConfig;
        this.minter = minter;
    }

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor appExecutor, MetadataApplier applier) {
        appExecutor.execute(() -> {
            try {
                synchronized (storedBearerHeaderRef) {
                    if (storedBearerHeaderRef.get() == null || storedBearerHeaderRef.get().isExpired()) {
                        storedBearerHeaderRef.set(minter.authenticateAndRetrieveAuthorizationHeader(clientCredentialsConfig));
                    }

                    Metadata headers = new Metadata();
                    headers.put(authorizationKey, storedBearerHeaderRef.get().getAuthorizationHeader());
                    applier.apply(headers);
                }
            } catch (Exception e) {
                applier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }

    /**
     * For unusual cases where stored credentials (i.e. token), which may be long-lived, is bad and needs to be flushed.
     */
    public void flushStoredCredentials() {
        synchronized (storedBearerHeaderRef) {
            storedBearerHeaderRef.set(null);
        }
    }

    static OIDCClientCredentialsAuthenticationConfig.OIDCClientCredentialsConfig validateAndExtractConfig(AuthenticationConfig authnConfig) throws OIDCClientCredentialsCallCredentialsException {
        if (!(authnConfig instanceof OIDCClientCredentialsAuthenticationConfig)) {
            throw new OIDCClientCredentialsCallCredentialsException("ClientCredentialsConfig is required for OIDC client credentials authentication method.");
        }

        var oidcClientCredentialsAuthenticationConfig = (OIDCClientCredentialsAuthenticationConfig) authnConfig;
        if(oidcClientCredentialsAuthenticationConfig.clientCredentialsConfig() == null) {
            throw new OIDCClientCredentialsCallCredentialsException("ClientCredentialsConfig cannot be null.");
        }

        if(oidcClientCredentialsAuthenticationConfig.clientCredentialsConfig().issuer() == null) {
            throw new OIDCClientCredentialsCallCredentialsException("ClientCredentialsConfig Issuer must not be null.");
        }
        if(oidcClientCredentialsAuthenticationConfig.clientCredentialsConfig().clientId() == null) {
            throw new OIDCClientCredentialsCallCredentialsException("ClientCredentialsConfig Client id must not be null.");
        }
        if(oidcClientCredentialsAuthenticationConfig.clientCredentialsConfig().clientSecret() == null) {
            throw new OIDCClientCredentialsCallCredentialsException("ClientCredentialsConfig Client secret must not be null.");
        }

        return oidcClientCredentialsAuthenticationConfig.clientCredentialsConfig();
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
