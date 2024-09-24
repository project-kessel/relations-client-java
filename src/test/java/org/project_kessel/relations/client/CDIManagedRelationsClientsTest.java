package org.project_kessel.relations.client;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.project_kessel.clients.authn.AuthenticationConfig;


class CDIManagedRelationsClientsTest {
    static RelationsConfig makeDummyConfig(boolean secure, RelationsConfig.AuthenticationConfig authnConfig) {
        return new RelationsConfig() {
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

    static RelationsConfig.AuthenticationConfig makeDummyAuthenticationConfig(boolean authnEnabled) {
        return new RelationsConfig.AuthenticationConfig() {
            @Override
            public AuthenticationConfig.AuthMode mode() {
                if (!authnEnabled) {
                    return AuthenticationConfig.AuthMode.DISABLED;
                }
                // pick some arbitrary non disabled mode
                return AuthenticationConfig.AuthMode.OIDC_CLIENT_CREDENTIALS;
            }

            @Override
            public Optional<RelationsConfig.OIDCClientCredentialsConfig> clientCredentialsConfig() {
                if (!authnEnabled) {
                    return Optional.empty();
                }

                // provide dummy config matching mode, above.
                return Optional.of(new RelationsConfig.OIDCClientCredentialsConfig() {
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
        RelationsConfig config = makeDummyConfig(false, makeDummyAuthenticationConfig(false));
        CDIManagedRelationsClients cdiManagedRelationsClients = new CDIManagedRelationsClients();

        try (MockedStatic<RelationsGrpcClientsManager> dummyManager = Mockito.mockStatic(
                RelationsGrpcClientsManager.class, new CallsRealMethods())) {
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
        RelationsConfig config = makeDummyConfig(false, makeDummyAuthenticationConfig(true));
        CDIManagedRelationsClients cdiManagedRelationsClients = new CDIManagedRelationsClients();

        try (MockedStatic<RelationsGrpcClientsManager> dummyManager = Mockito.mockStatic(
                RelationsGrpcClientsManager.class, new CallsRealMethods())) {
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
        RelationsConfig config = makeDummyConfig(true, makeDummyAuthenticationConfig(false));
        CDIManagedRelationsClients cdiManagedRelationsClients = new CDIManagedRelationsClients();

        try (MockedStatic<RelationsGrpcClientsManager> dummyManager = Mockito.mockStatic(
                RelationsGrpcClientsManager.class, new CallsRealMethods())) {
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
        RelationsConfig config = makeDummyConfig(true, makeDummyAuthenticationConfig(true));
        CDIManagedRelationsClients cdiManagedRelationsClients = new CDIManagedRelationsClients();

        try (MockedStatic<RelationsGrpcClientsManager> dummyManager = Mockito.mockStatic(
                RelationsGrpcClientsManager.class, new CallsRealMethods())) {
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
