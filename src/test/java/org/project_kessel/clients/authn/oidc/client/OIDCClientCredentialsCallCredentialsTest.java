package org.project_kessel.clients.authn.oidc.client;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultEventExecutor;
import org.junit.jupiter.api.Test;
import org.project_kessel.clients.authn.AuthenticationConfig;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.project_kessel.clients.authn.oidc.client.OIDCClientCredentialsMinter.getDefaultMinterImplementation;

public class OIDCClientCredentialsCallCredentialsTest {

    @Test
    void initializationShouldFailWithNullIssuer() {
        try {
            var authConfig = makeAuthConfig(null, "some", "some");
            new OIDCClientCredentialsCallCredentials(authConfig);
        }
        catch (OIDCClientCredentialsCallCredentials.OIDCClientCredentialsCallCredentialsException e) {
            return; // expected
        }

        fail("Issuer should not be null");
    }

    @Test
    void initializationShouldFailWithNullClientId() {
        try {
            var authConfig = makeAuthConfig("some", null, "some");
            new OIDCClientCredentialsCallCredentials(authConfig);
        }
        catch (OIDCClientCredentialsCallCredentials.OIDCClientCredentialsCallCredentialsException e) {
            return; // expected
        }

        fail("Client id should not be null");
    }

    @Test
    void initializationShouldFailWithNullClientSecret() {
        try {
            var authConfig = makeAuthConfig("some", "some", null);
            new OIDCClientCredentialsCallCredentials(authConfig);
        }
        catch (OIDCClientCredentialsCallCredentials.OIDCClientCredentialsCallCredentialsException e) {
            return; // expected
        }

        fail("Client secret should not be null");
    }

    @Test
    void unknownSpecifiedMinterShouldThrowException() {
        var authConfig = makeAuthConfig("some", "some", "some", Optional.empty(),
                Optional.of("one.bogus.clazz"));
        try {
            new OIDCClientCredentialsCallCredentials(authConfig);
        }
        catch (OIDCClientCredentialsCallCredentials.OIDCClientCredentialsCallCredentialsException e) {
            return; // expected
        }

        fail("Shouldn't be able to instantiate OIDCClientCredentialsCallCredentials with a bogus minter.");
    }

    @Test
    void knownSpecifiedMinterShouldNotThrowException() {
        var authConfig = makeAuthConfig("some", "some", "some", Optional.empty(),
                Optional.of(getDefaultMinterImplementation().getName()));
        try {
            new OIDCClientCredentialsCallCredentials(authConfig);
        }
        catch (OIDCClientCredentialsCallCredentials.OIDCClientCredentialsCallCredentialsException e) {
            fail("Should be able create default minter with no problems.");
        }
    }

    @Test
    void unspecifiedMinterShouldUseDefaultAndNotThrowException() {
        var authConfig = makeAuthConfig("some", "some", "some", Optional.empty(),
                Optional.empty());
        try {
            new OIDCClientCredentialsCallCredentials(authConfig);
        }
        catch (OIDCClientCredentialsCallCredentials.OIDCClientCredentialsCallCredentialsException e) {
            fail("Should be able create default minter with no problems.");
        }
    }

    @Test
    void shouldApplyBearerMetadata() throws InterruptedException {
        var authConfig = makeAuthConfig("some", "some", "some", Optional.empty(),
                Optional.empty());
        var oidcClientCredentialsConfig = authConfig.clientCredentialsConfig();
        var minter = makeFakeMinter(true, 0);
        var callCreds = new OIDCClientCredentialsCallCredentials(oidcClientCredentialsConfig, minter);
        final AtomicReference<Metadata> metaDataRef = new AtomicReference<>();
        final AtomicReference<Status> statusRef = new AtomicReference<>();
        var latch = new CountDownLatch(1);
        var metaDataApplier = makeFakeMetadataApplier(metaDataRef, statusRef, latch);

        callCreds.applyRequestMetadata(null, new DefaultEventExecutor(), metaDataApplier);

        latch.await();
        assertEquals("token0", metaDataRef.get().get(OIDCClientCredentialsCallCredentials.authorizationKey));
        assertNull(statusRef.get());
    }

    @Test
    void shouldApplyPreviouslyObtainedTokenWhenInLifetime() throws InterruptedException {
        var authConfig = makeAuthConfig("some", "some", "some", Optional.empty(),
                Optional.empty());
        var oidcClientCredentialsConfig = authConfig.clientCredentialsConfig();
        var minter = makeFakeMinter(true, 100000); // big lifetime
        var callCreds = new OIDCClientCredentialsCallCredentials(oidcClientCredentialsConfig, minter);
        final AtomicReference<Metadata> metaDataRef = new AtomicReference<>();
        final AtomicReference<Status> statusRef = new AtomicReference<>();
        var latch = new CountDownLatch(1);
        var metaDataApplier = makeFakeMetadataApplier(metaDataRef, statusRef, latch);

        callCreds.applyRequestMetadata(null, new DefaultEventExecutor(), metaDataApplier);

        latch.await();
        var latch2 = new CountDownLatch(1);
        var metaDataApplier2 = makeFakeMetadataApplier(metaDataRef, statusRef, latch2);

        callCreds.applyRequestMetadata(null, new DefaultEventExecutor(), metaDataApplier2);

        latch2.await();
        // token0 is the original minted token -- shows there was no second authentication and new token
        assertEquals("token0", metaDataRef.get().get(OIDCClientCredentialsCallCredentials.authorizationKey));
        assertNull(statusRef.get());
    }

    @Test
    void shouldApplyNewTokenWhenOutOfLifetime() throws InterruptedException {
        var authConfig = makeAuthConfig("some", "some", "some", Optional.empty(),
                Optional.empty());
        var oidcClientCredentialsConfig = authConfig.clientCredentialsConfig();
        var minter = makeFakeMinter(true, 0); // zero lifetime forces new auth token
        var callCreds = new OIDCClientCredentialsCallCredentials(oidcClientCredentialsConfig, minter);
        final AtomicReference<Metadata> metaDataRef = new AtomicReference<>();
        final AtomicReference<Status> statusRef = new AtomicReference<>();
        var latch = new CountDownLatch(1);
        var metaDataApplier = makeFakeMetadataApplier(metaDataRef, statusRef, latch);

        callCreds.applyRequestMetadata(null, new DefaultEventExecutor(), metaDataApplier);

        latch.await();
        var latch2 = new CountDownLatch(1);
        var metaDataApplier2 = makeFakeMetadataApplier(metaDataRef, statusRef, latch2);

        callCreds.applyRequestMetadata(null, new DefaultEventExecutor(), metaDataApplier2);

        latch2.await();
        // token1 is the second minted token -- shows that when out of lifetime there is a second authn and new token
        assertEquals("token1", metaDataRef.get().get(OIDCClientCredentialsCallCredentials.authorizationKey));
        assertNull(statusRef.get());
    }

    @Test
    void shouldApplyUnauthenticatedWhenAuthnFails() throws InterruptedException {
        var authConfig = makeAuthConfig("some", "some", "some", Optional.empty(),
                Optional.empty());
        var oidcClientCredentialsConfig = authConfig.clientCredentialsConfig();
        var minter = makeFakeMinter(false, 0);
        var callCreds = new OIDCClientCredentialsCallCredentials(oidcClientCredentialsConfig, minter);
        final AtomicReference<Metadata> metaDataRef = new AtomicReference<>();
        final AtomicReference<Status> statusRef = new AtomicReference<>();
        var latch = new CountDownLatch(1);
        var metaDataApplier = makeFakeMetadataApplier(metaDataRef, statusRef, latch);

        callCreds.applyRequestMetadata(null, new DefaultEventExecutor(), metaDataApplier);

        latch.await();
        assertNull(metaDataRef.get());
        assertEquals(Status.Code.UNAUTHENTICATED, statusRef.get().getCode());
    }

    static OIDCClientCredentialsMinter makeFakeMinter(boolean alwaysSucceedsOrFails, long tokensExpireIn) {
        return new OIDCClientCredentialsMinter() {
            int mintedNumber = 0;

            @Override
            public BearerHeader authenticateAndRetrieveAuthorizationHeader(OIDCClientCredentialsAuthenticationConfig.OIDCClientCredentialsConfig clientConfig) throws OIDCClientCredentialsMinterException {
                if (!alwaysSucceedsOrFails) {
                    throw new OIDCClientCredentialsMinterException("Authentication failed.");
                }

                Optional<LocalDateTime> expiry = Optional.of(LocalDateTime.now().plusSeconds(tokensExpireIn));
                return new BearerHeader("token" + mintedNumber++, expiry);
            }
        };
    }

    static CallCredentials.MetadataApplier makeFakeMetadataApplier(AtomicReference<Metadata> metaDataRef, AtomicReference<Status> statusRef, CountDownLatch latch) {
        return new CallCredentials.MetadataApplier() {
            @Override
            public void apply(Metadata headers) {
                metaDataRef.set(headers);
                latch.countDown();
            }

            @Override
            public void fail(Status status) {
                statusRef.set(status);
                latch.countDown();
            }
        };
    }

    public static OIDCClientCredentialsAuthenticationConfig makeAuthConfig(String issuer, String clientId, String clientSecret) {
        return makeAuthConfig(issuer, clientId, clientSecret, Optional.empty(), Optional.empty());
    }

    public static OIDCClientCredentialsAuthenticationConfig makeAuthConfig(String issuer, String clientId, String clientSecret, Optional<String[]> scope, Optional<String> minterImpl) {
        var oidcClientCredentialsConfig = new OIDCClientCredentialsAuthenticationConfig.OIDCClientCredentialsConfig();
        oidcClientCredentialsConfig.setIssuer(issuer);
        oidcClientCredentialsConfig.setClientId(clientId);
        oidcClientCredentialsConfig.setClientSecret(clientSecret);
        oidcClientCredentialsConfig.setScope(scope);
        oidcClientCredentialsConfig.setOidcClientCredentialsMinterImplementation(minterImpl);

        var authnConfig = new OIDCClientCredentialsAuthenticationConfig();
        authnConfig.setMode(null);
        authnConfig.setCredentialsConfig(oidcClientCredentialsConfig);

        return authnConfig;
    }
}
