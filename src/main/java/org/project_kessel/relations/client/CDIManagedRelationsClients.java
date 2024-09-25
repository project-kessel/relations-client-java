package org.project_kessel.relations.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.project_kessel.clients.authn.AuthenticationConfig.AuthMode;

/**
 * A managed bean for providing clients for injection in apps.
 * It has the current limitation that only one underlying grpc connection can be configured.
 * However, it is still possible to create more via KesselClientsManager implementation directly.
 * This class does nothing unless the client is being managed by a CDI container (e.g. Quarkus)
 */
@ApplicationScoped
public class CDIManagedRelationsClients {
    @Produces
    RelationsGrpcClientsManager getManager(RelationsConfig config) {
        return RelationsGrpcClientsManager.forClientsWithConfig(config);
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
