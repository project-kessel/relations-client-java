package client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

/**
 * A managed bean for providing relations api clients for injection in apps.
 * Performs some lifecycle management.
 * It has the current limitation that only one underlying grpc connection can be configured.
 * However, it is still possible to create more via RelationsGrpcClientsManager directly.
 * This class does nothing unless the client is being managed by a CDI container (e.g. Quarkus)
 */
@ApplicationScoped
public class CDIManagedClients {
    @Produces
    public RelationsGrpcClientsManager getManager(Config config) {
        var isSecureClients = config.isSecureClients();
        var targetUrl = config.targetUrl();

        if (isSecureClients) {
            return RelationsGrpcClientsManager.forSecureClients(targetUrl);
        }

        return RelationsGrpcClientsManager.forInsecureClients(targetUrl);
    }

    void shutdownClientsManager(@Disposes RelationsGrpcClientsManager manager) {
        RelationsGrpcClientsManager.shutdownManager(manager);
    }

    @Produces
    public CheckClient getCheckClient(RelationsGrpcClientsManager manager) {
        return manager.getCheckClient();
    }

    @Produces
    public RelationTuplesClient getRelationsClient(RelationsGrpcClientsManager manager) {
        return manager.getRelationTuplesClient();
    }
}
