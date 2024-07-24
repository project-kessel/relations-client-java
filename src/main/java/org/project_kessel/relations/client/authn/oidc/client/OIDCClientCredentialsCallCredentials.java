package org.project_kessel.relations.client.authn.oidc.client;

import io.grpc.Metadata;
import io.grpc.Status;
import org.project_kessel.relations.client.Config;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public class OIDCClientCredentialsCallCredentials extends io.grpc.CallCredentials {
    private static final Metadata.Key<String> authorizationKey = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    private final Config.OIDCClientCredentialsConfig clientCredentialsConfig;
    private final OIDCClientCredentialsMinter minter;

    private final AtomicReference<OIDCClientCredentialsMinter.BearerHeader> storedBearerHeaderRef = new AtomicReference<>();

    public OIDCClientCredentialsCallCredentials(Config.AuthenticationConfig authnConfig) throws OIDCClientCredentialsCallCredentialsException {
        if(authnConfig.clientCredentialsConfig().isEmpty()) {
            throw new OIDCClientCredentialsCallCredentialsException("ClientCredentialsConfig is required for OIDC client credentials authentication method.");
        }
        this.clientCredentialsConfig = authnConfig.clientCredentialsConfig().get();

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

    public static class OIDCClientCredentialsCallCredentialsException extends Exception {
        public OIDCClientCredentialsCallCredentialsException(String message) {
            super(message);
        }

        public OIDCClientCredentialsCallCredentialsException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
