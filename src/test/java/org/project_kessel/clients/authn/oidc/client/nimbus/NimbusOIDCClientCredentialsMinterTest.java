package org.project_kessel.clients.authn.oidc.client.nimbus;

import org.junit.jupiter.api.Test;
import org.project_kessel.clients.authn.CallCredentialsFactoryTest;
import org.project_kessel.clients.authn.oidc.client.OIDCClientCredentialsMinter;
import org.project_kessel.clients.fake.FakeIdp;

import static org.junit.jupiter.api.Assertions.*;

public class NimbusOIDCClientCredentialsMinterTest {

    @Test
    void shouldReturnBearerHeaderWhenIdPAuthenticates() {
        var minter = new NimbusOIDCClientCredentialsMinter();
        var config = CallCredentialsFactoryTest.dummyAuthConfigWithGoodOIDCClientCredentials().clientCredentialsConfig();
        OIDCClientCredentialsMinter.BearerHeader bearerHeader = null;
        try {
            FakeIdp fakeIdp = new FakeIdp(8090);
            fakeIdp.start();
            bearerHeader = minter.authenticateAndRetrieveAuthorizationHeader(config);
            fakeIdp.stop();
        } catch (OIDCClientCredentialsMinter.OIDCClientCredentialsMinterException e) {
            fail("Should not throw exception if authn is successful.");
        }

        assertNotNull(bearerHeader);
        assertEquals("Bearer blah", bearerHeader.getAuthorizationHeader());
    }

    @Test
    void shouldThrowExceptionWhenIdPAuthenticationFails() {
        var minter = new NimbusOIDCClientCredentialsMinter();
        var config = CallCredentialsFactoryTest.dummyAuthConfigWithGoodOIDCClientCredentials().clientCredentialsConfig();
        FakeIdp fakeIdp = new FakeIdp(8090, false);
        try {
            fakeIdp.start();
            minter.authenticateAndRetrieveAuthorizationHeader(config);
            fail("Should throw exception if authn is not successful.");
        } catch (OIDCClientCredentialsMinter.OIDCClientCredentialsMinterException e) {
            // success
        } catch(Exception e) {
            fail("OIDCClientCredentialsMinterException expected.");
        } finally {
            fakeIdp.stop();
        }
    }
}
