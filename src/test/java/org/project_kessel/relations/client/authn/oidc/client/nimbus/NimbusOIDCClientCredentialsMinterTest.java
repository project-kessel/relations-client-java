package org.project_kessel.relations.client.authn.oidc.client.nimbus;

import org.junit.jupiter.api.Test;
import org.project_kessel.relations.client.RelationsGrpcClientsManagerTest;
import org.project_kessel.relations.client.authn.oidc.client.ClientCredentialsRefreshers;
import org.project_kessel.relations.client.fake.FakeIdp;

import static org.junit.jupiter.api.Assertions.*;

public class NimbusOIDCClientCredentialsMinterTest {

    @Test
    void shouldReturnBearerHeaderWhenIdPAuthenticates() {
        var minter = new NimbusOIDCClientCredentialsMinter();
        var config = RelationsGrpcClientsManagerTest.dummyAuthConfigWithGoodOIDCClientCredentials()
                .clientCredentialsConfig();
        ClientCredentialsRefreshers.BearerHeader bearerHeader = null;
        try {
            FakeIdp fakeIdp = new FakeIdp(8090);
            fakeIdp.start();
            bearerHeader = minter.authenticateAndRetrieveAuthorizationHeader(config.get());
            fakeIdp.stop();
        } catch (ClientCredentialsRefreshers.OIDCClientCredentialsMinterException e) {
            fail("Should not throw exception if authn is successful.");
        }

        assertNotNull(bearerHeader);
        assertEquals("Bearer blah", bearerHeader.getAuthorizationHeader());
    }

    @Test
    void shouldThrowExceptionWhenIdPAuthenticationFails() {
        var minter = new NimbusOIDCClientCredentialsMinter();
        var config = RelationsGrpcClientsManagerTest.dummyAuthConfigWithGoodOIDCClientCredentials()
                .clientCredentialsConfig();
        FakeIdp fakeIdp = new FakeIdp(8090, false);
        try {
            fakeIdp.start();
            minter.authenticateAndRetrieveAuthorizationHeader(config.get());
            fail("Should throw exception if authn is not successful.");
        } catch (ClientCredentialsRefreshers.OIDCClientCredentialsMinterException e) {
            // success
        } catch (Exception e) {
            fail("OIDCClientCredentialsMinterException expected.");
        } finally {
            fakeIdp.stop();
        }
    }
}
