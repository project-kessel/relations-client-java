package org.project_kessel.relations.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.project_kessel.api.relations.v1beta1.KesselCheckServiceGrpc;
import org.project_kessel.api.relations.v1beta1.KesselLookupServiceGrpc;
import org.project_kessel.api.relations.v1beta1.KesselTupleServiceGrpc;
import org.project_kessel.clients.KesselClient;
import org.project_kessel.clients.authn.AuthenticationConfig;

public class RelationsGrpcClientsManagerTest {
    /*
     Tests relying on reflection. Brittle and could be removed in future.
     */

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

    @Test
    void testSameChannelUsedByClientsInternal() throws Exception {
        var manager = RelationsGrpcClientsManager.forInsecureClients("localhost:8080");
        var checkClient = manager.getCheckClient();
        var relationTuplesClient = manager.getRelationTuplesClient();
        var lookupClient = manager.getLookupClient();

        var checkAsyncStubField = KesselClient.class.getDeclaredField("asyncStub");
        checkAsyncStubField.setAccessible(true);
        var checkChannel =
                ((KesselCheckServiceGrpc.KesselCheckServiceStub) checkAsyncStubField.get(checkClient)).getChannel();
        var relationTuplesAsyncStubField = KesselClient.class.getDeclaredField("asyncStub");
        relationTuplesAsyncStubField.setAccessible(true);
        var relationTuplesChannel = ((KesselTupleServiceGrpc.KesselTupleServiceStub) relationTuplesAsyncStubField.get(
                relationTuplesClient)).getChannel();
        var lookupAsyncStubField = KesselClient.class.getDeclaredField("asyncStub");
        lookupAsyncStubField.setAccessible(true);
        var lookupChannel = ((KesselLookupServiceGrpc.KesselLookupServiceStub) lookupAsyncStubField
                .get(lookupClient)).getChannel();

        assertEquals(checkChannel, relationTuplesChannel);
        assertEquals(lookupChannel, relationTuplesChannel);
    }
}