package org.project_kessel.relations.client;

import io.grpc.Metadata;
import org.junit.jupiter.api.AfterAll;
import org.project_kessel.api.relations.v1beta1.CheckRequest;
import org.project_kessel.api.relations.v1beta1.KesselCheckServiceGrpc;
import org.project_kessel.api.relations.v1beta1.KesselLookupServiceGrpc;
import org.project_kessel.api.relations.v1beta1.KesselTupleServiceGrpc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project_kessel.clients.KesselClient;
import org.project_kessel.clients.authn.AuthenticationConfig;
import org.project_kessel.clients.fake.GrpcServerSpy;

import java.util.Optional;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.project_kessel.clients.util.CertUtil.*;

public class RelationsGrpcClientsManagerTest {
    private static final Metadata.Key<String> authorizationKey = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    @BeforeAll
    static void testSetup() {
        /* Make sure all client managers shutdown/removed before tests */
        RelationsGrpcClientsManager.shutdownAll();
        /* Add self-signed cert to keystore, trust manager and SSL context for TLS testing. */
        addTestCACertToTrustStore();
    }

    @AfterEach
    void testTeardown() {
        /* Make sure all client managers shutdown/removed after each test */
        RelationsGrpcClientsManager.shutdownAll();
    }

    @AfterAll
    static void removeTestSetup() {
        /* Remove self-signed cert */
        removeTestCACertFromKeystore();
    }

    /*
      End-to-end tests against fake IdP and/or fake grpc relations-api
     */

    @Test
    void testManagersHoldIntendedCredentialsInChannel() throws Exception {
        Config.AuthenticationConfig authnConfig = dummyAuthConfigWithGoodOIDCClientCredentials();
        var manager = RelationsGrpcClientsManager.forInsecureClients("localhost:7000");
        var manager2 = RelationsGrpcClientsManager.forInsecureClients("localhost:7001", authnConfig);
        var manager3 = RelationsGrpcClientsManager.forSecureClients("localhost:7002");
        var manager4 = RelationsGrpcClientsManager.forSecureClients("localhost:7003", authnConfig);

        var checkClient = manager.getCheckClient();
        var checkClient2 = manager2.getCheckClient();
        var checkClient3 = manager3.getCheckClient();
        var checkClient4 = manager4.getCheckClient();

        var cd1 = GrpcServerSpy.runAgainstTemporaryServerWithDummyServices(7000, () -> checkClient.check(CheckRequest.getDefaultInstance()));
        var cd2 = GrpcServerSpy.runAgainstTemporaryServerWithDummyServices(7001, () -> checkClient2.check(CheckRequest.getDefaultInstance()));
        var cd3 = GrpcServerSpy.runAgainstTemporaryTlsServerWithDummyServices(7002, () -> checkClient3.check(CheckRequest.getDefaultInstance()));
        var cd4 = GrpcServerSpy.runAgainstTemporaryTlsServerWithDummyServices(7003, () -> checkClient4.check(CheckRequest.getDefaultInstance()));

        assertNull(cd1.getMetadata().get(authorizationKey));
        assertEquals("NONE", cd1.getCall().getSecurityLevel().toString());

        assertNotNull(cd2.getMetadata().get(authorizationKey));
        assertEquals("NONE", cd2.getCall().getSecurityLevel().toString());

        assertNull(cd3.getMetadata().get(authorizationKey));
        assertEquals("PRIVACY_AND_INTEGRITY", cd3.getCall().getSecurityLevel().toString());

        assertNotNull(cd4.getMetadata().get(authorizationKey));
        assertEquals("PRIVACY_AND_INTEGRITY", cd4.getCall().getSecurityLevel().toString());
    }

    /*
     Tests relying on reflection. Brittle and could be removed in future.
     */

    @Test
    void testSameChannelUsedByClientsInternal() throws Exception {
        var manager = RelationsGrpcClientsManager.forInsecureClients("localhost:8080");
        var checkClient = manager.getCheckClient();
        var relationTuplesClient = manager.getRelationTuplesClient();
        var lookupClient = manager.getLookupClient();

        var checkAsyncStubField = KesselClient.class.getDeclaredField("asyncStub");
        checkAsyncStubField.setAccessible(true);
        var checkChannel = ((KesselCheckServiceGrpc.KesselCheckServiceStub)checkAsyncStubField.get(checkClient)).getChannel();
        var relationTuplesAsyncStubField = KesselClient.class.getDeclaredField("asyncStub");
        relationTuplesAsyncStubField.setAccessible(true);
        var relationTuplesChannel = ((KesselTupleServiceGrpc.KesselTupleServiceStub)relationTuplesAsyncStubField.get(relationTuplesClient)).getChannel();
        var lookupAsyncStubField = KesselClient.class.getDeclaredField("asyncStub");
        lookupAsyncStubField.setAccessible(true);
        var lookupChannel = ((KesselLookupServiceGrpc.KesselLookupServiceStub)lookupAsyncStubField.get(lookupClient)).getChannel();

        assertEquals(checkChannel, relationTuplesChannel);
        assertEquals(lookupChannel, relationTuplesChannel);
    }

    public static Config.AuthenticationConfig dummyAuthConfigWithGoodOIDCClientCredentials() {
        return new Config.AuthenticationConfig() {
            @Override
            public AuthenticationConfig.AuthMode mode() {
                return AuthenticationConfig.AuthMode.OIDC_CLIENT_CREDENTIALS; // any non-disabled value
            }

            @Override
            public Optional<Config.OIDCClientCredentialsConfig> clientCredentialsConfig() {
                return Optional.of(new Config.OIDCClientCredentialsConfig() {
                    @Override
                    public String issuer() {
                        return "http://localhost:8090";
                    }

                    @Override
                    public String clientId() {
                        return "test";
                    }

                    @Override
                    public String clientSecret() {
                        return "test";
                    }

                    @Override
                    public Optional<String[]> scope() {
                        return Optional.empty();
                    }

                    @Override
                    public Optional<String> oidcClientCredentialsMinterImplementation() {
                        return Optional.empty();
                    }
                });
            }
        };
    }
}