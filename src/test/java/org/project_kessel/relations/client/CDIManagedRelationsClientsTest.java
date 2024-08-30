package org.project_kessel.relations.client;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.project_kessel.clients.authn.AuthenticationConfig;


class CDIManagedRelationsClientsTest {
    static Config makeDummyConfig(boolean secure, Config.AuthenticationConfig authnConfig) {
        return new Config() {
            @Override
            public boolean isSecureClients() {
                return secure;
            }

            @Override
            public String targetUrl() {
                return "localhost";
            }

            @Override
            public Optional<AuthenticationConfig> authenticationConfig() {
                return Optional.of(authnConfig);
            }
        };
    }

    static Config.AuthenticationConfig makeDummyAuthenticationConfig(boolean authnEnabled) {
        return new Config.AuthenticationConfig() {
            @Override
            public AuthenticationConfig.AuthMode mode() {
                if (!authnEnabled) {
                    return AuthenticationConfig.AuthMode.DISABLED;
                }
                // pick some arbitrary non disabled mode
                return AuthenticationConfig.AuthMode.OIDC_CLIENT_CREDENTIALS;
            }

            @Override
            public Optional<Config.OIDCClientCredentialsConfig> clientCredentialsConfig() {
                if (!authnEnabled) {
                    return Optional.empty();
                }

                // provide dummy config matching mode, above.
                return Optional.of(new Config.OIDCClientCredentialsConfig() {
                    @Override
                    public String issuer() {
                        return "";
                    }

                    @Override
                    public String clientId() {
                        return "";
                    }

                    @Override
                    public String clientSecret() {
                        return "";
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
    void testInsecureNoAuthnMakesCorrectManagerCall() {
        Config config = makeDummyConfig(false, makeDummyAuthenticationConfig(false));
        CDIManagedRelationsClients cdiManagedRelationsClients = new CDIManagedRelationsClients();

        try (MockedStatic<RelationsGrpcClientsManager> dummyManager = Mockito.mockStatic(
                RelationsGrpcClientsManager.class)) {
            cdiManagedRelationsClients.getManager(config);
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forInsecureClients(anyString()),
                    times(1)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forInsecureClients(anyString(), any()),
                    times(0)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forSecureClients(anyString()),
                    times(0)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forSecureClients(anyString(), any()),
                    times(0)
            );
        }
    }

    @Test
    void testInsecureWithAuthnMakesCorrectManagerCall() {
        Config config = makeDummyConfig(false, makeDummyAuthenticationConfig(true));
        CDIManagedRelationsClients cdiManagedRelationsClients = new CDIManagedRelationsClients();

        try (MockedStatic<RelationsGrpcClientsManager> dummyManager = Mockito.mockStatic(
                RelationsGrpcClientsManager.class)) {
            cdiManagedRelationsClients.getManager(config);
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forInsecureClients(anyString()),
                    times(0)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forInsecureClients(anyString(), any()),
                    times(1)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forSecureClients(anyString()),
                    times(0)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forSecureClients(anyString(), any()),
                    times(0)
            );
        }
    }

    @Test
    void testSecureNoAuthnMakesCorrectManagerCall() {
        Config config = makeDummyConfig(true, makeDummyAuthenticationConfig(false));
        CDIManagedRelationsClients cdiManagedRelationsClients = new CDIManagedRelationsClients();

        try (MockedStatic<RelationsGrpcClientsManager> dummyManager = Mockito.mockStatic(
                RelationsGrpcClientsManager.class)) {
            cdiManagedRelationsClients.getManager(config);
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forInsecureClients(anyString()),
                    times(0)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forInsecureClients(anyString(), any()),
                    times(0)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forSecureClients(anyString()),
                    times(1)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forSecureClients(anyString(), any()),
                    times(0)
            );
        }
    }

    @Test
    void testSecureWithAuthnMakesCorrectManagerCall() {
        Config config = makeDummyConfig(true, makeDummyAuthenticationConfig(true));
        CDIManagedRelationsClients cdiManagedRelationsClients = new CDIManagedRelationsClients();

        try (MockedStatic<RelationsGrpcClientsManager> dummyManager = Mockito.mockStatic(
                RelationsGrpcClientsManager.class)) {
            cdiManagedRelationsClients.getManager(config);
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forInsecureClients(anyString()),
                    times(0)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forInsecureClients(anyString(), any()),
                    times(0)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forSecureClients(anyString()),
                    times(0)
            );
            dummyManager.verify(
                    () -> RelationsGrpcClientsManager.forSecureClients(anyString(), any()),
                    times(1)
            );
        }
    }
}
