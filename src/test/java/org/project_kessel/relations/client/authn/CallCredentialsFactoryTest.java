package org.project_kessel.relations.client.authn;

import org.junit.jupiter.api.Test;
import org.project_kessel.relations.client.Config;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.project_kessel.relations.client.RelationsGrpcClientsManagerTest.dummyAuthConfigWithGoodOIDCClientCredentials;

class CallCredentialsFactoryTest {

    @Test
    void testCreateOIDCClientCallCredentials() {
        Config.AuthenticationConfig authnConfig = dummyAuthConfigWithGoodOIDCClientCredentials();
        try {
            CallCredentialsFactory.create(authnConfig);
        } catch (CallCredentialsFactory.CallCredentialsCreationException e) {
            fail("CallCredentialsFactory creation for OIDC client should not throw an exception when OIDC client config is good.");
        }
    }

    @Test
    void testFailToCreateCallCredentialsWhenAuthnConfigEmpty() {
        Config.AuthenticationConfig authnConfig = null;
        try {
            CallCredentialsFactory.create(authnConfig);
            fail("CallCredentialsFactory creation for OIDC client should throw an exception when OIDC client config is empty.");
        } catch (CallCredentialsFactory.CallCredentialsCreationException e) {
        }
    }

    @Test
    void testFailToCreateCallCredentialsForOIDCWhenConfigEmpty() {
        Config.AuthenticationConfig authnConfig = new Config.AuthenticationConfig() {
            @Override
            public Config.AuthMode mode() {
                return Config.AuthMode.OIDC_CLIENT_CREDENTIALS;
            }

            @Override
            public Optional<Config.OIDCClientCredentialsConfig> clientCredentialsConfig() {
                return Optional.empty();
            }
        };
        try {
            CallCredentialsFactory.create(authnConfig);
            fail("CallCredentialsFactory creation for OIDC client should throw an exception when OIDC client config is empty.");
        } catch (CallCredentialsFactory.CallCredentialsCreationException e) {
        }
    }

}
