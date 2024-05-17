package client;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 * A managed bean for providing relations api clients for injection in apps.
 * Performs some lifecycle management.
 * It has the current limitation that only one underlying grpc connection can be configured.
 * However, it is still possible to create more via RelationsGrpcClientsManager directly.
 * This class does nothing unless the client is being managed by a CDI container (e.g. Quarkus)
 */
@ApplicationScoped
public class CDIManagedClients {
    @Inject
    Config config;

    private RelationsGrpcClientsManager getManager() {
        var isSecureClients = config.isSecureClients();
        var targetUrl = config.targetUrl();

        if (isSecureClients) {
            return RelationsGrpcClientsManager.forSecureClients(targetUrl);
        }

        return RelationsGrpcClientsManager.forInsecureClients(targetUrl);
    }

    @PreDestroy
    void shutdownClientsManager() {
        RelationsGrpcClientsManager.shutdownManager(getManager());
    }

    @Produces
    public CheckClient getCheckClient() {
        return getManager().getCheckClient();
    }

    @Produces
    public RelationTuplesClient getRelationsClient() {
        return getManager().getRelationTuplesClient();
    }
}
