package org.project_kessel.relations.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

/**
 * A managed bean for providing relations api clients for injection in apps.
 * It has the current limitation that only one underlying grpc connection can be configured.
 * However, it is still possible to create more via RelationsGrpcClientsManager directly.
 * This class does nothing unless the client is being managed by a CDI container (e.g. Quarkus)
 */
@ApplicationScoped
public class CDIManagedClients {
    @Produces
    RelationsGrpcClientsManager getManager(Config config) {
        var isSecureClients = config.isSecureClients();
        var targetUrl = config.targetUrl();

        if (isSecureClients) {
            return RelationsGrpcClientsManager.forSecureClients(targetUrl);
        }

        return RelationsGrpcClientsManager.forInsecureClients(targetUrl);
    }

    @Produces
    CheckClient getCheckClient(RelationsGrpcClientsManager manager) {
        return manager.getCheckClient();
    }

    @Produces
    RelationTuplesClient getRelationsClient(RelationsGrpcClientsManager manager) {
        return manager.getRelationTuplesClient();
    }

    @Produces
    LookupClient getLookupClient(RelationsGrpcClientsManager manager) {
        return manager.getLookupClient();
    }
}
