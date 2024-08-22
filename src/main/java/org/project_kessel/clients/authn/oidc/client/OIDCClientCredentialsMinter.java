package org.project_kessel.clients.authn.oidc.client;

import org.project_kessel.clients.authn.oidc.client.nimbus.NimbusOIDCClientCredentialsMinter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Optional;

public abstract class OIDCClientCredentialsMinter {
    private static final Class<?> defaultMinter = NimbusOIDCClientCredentialsMinter.class;

    public static OIDCClientCredentialsMinter forDefaultImplementation() throws OIDCClientCredentialsMinterException {
        return forClass(defaultMinter);
    }

    public static OIDCClientCredentialsMinter forClass(Class<?> minterClass) throws OIDCClientCredentialsMinterException {
        try {
            Constructor<?> constructor  = minterClass.getConstructor();
            return (OIDCClientCredentialsMinter)constructor.newInstance();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new OIDCClientCredentialsMinterException("Can't create instance of OIDC client credentials minter", e);
        }
    }

    public static OIDCClientCredentialsMinter forName(String name) throws OIDCClientCredentialsMinterException {
        try {
            Class<?> minterImplClass = Class.forName(name);
            return forClass(minterImplClass);
        } catch(ClassNotFoundException e) {
            throw new OIDCClientCredentialsMinterException("Can't find the specified OIDC client credentials minter implementation", e);
        }
    }

    public abstract BearerHeader authenticateAndRetrieveAuthorizationHeader(OIDCClientCredentialsAuthenticationConfig.OIDCClientCredentialsConfig clientConfig) throws OIDCClientCredentialsMinterException;

    public static class BearerHeader {
        private final String authorizationHeader;
        private final Optional<LocalDateTime> expiry;

        public BearerHeader(String authorizationHeader, Optional<LocalDateTime> expiry) {
            this.authorizationHeader = authorizationHeader;
            this.expiry = expiry;
        }

        public String getAuthorizationHeader() {
            return authorizationHeader;
        }

        public boolean isExpired() {
            return expiry.map(t -> t.isBefore(LocalDateTime.now())).orElse(true);
        }
    }

    /**
     * Utility method to derive an expiry dateTime from a just granted token with expires_in set.
     * @param expiresIn 0 is expected if expiresIn is not set or otherwise not applicable.
     * @return
     */
    public static Optional<LocalDateTime> getExpiryDateFromExpiresIn(long expiresIn) {
        Optional<LocalDateTime> expiryTime;
        if (expiresIn != 0) {
            // this processing happens some time after token is granted with lifetime so subtract buffer from lifetime
            long bufferSeconds = 60;
            if(expiresIn < bufferSeconds) {
                expiryTime = Optional.empty();
            } else {
                expiryTime = Optional.of(LocalDateTime.now().plusSeconds(expiresIn).minusSeconds(bufferSeconds));
            }
        } else {
            expiryTime = Optional.empty();
        }

        return expiryTime;
    }

    public static Class<?> getDefaultMinterImplementation() {
        return defaultMinter;
    }

    public static class OIDCClientCredentialsMinterException extends Exception {
        public OIDCClientCredentialsMinterException(String message) {
            super(message);
        }
        public OIDCClientCredentialsMinterException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
