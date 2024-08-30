package org.project_kessel.relations.client;

import java.util.logging.Logger;

import io.grpc.Channel;
import org.project_kessel.api.relations.v1.GetLivezResponse;
import org.project_kessel.api.relations.v1.GetLivezRequest;
import org.project_kessel.api.relations.v1.GetReadyzResponse;
import org.project_kessel.api.relations.v1.GetReadyzRequest;
import org.project_kessel.api.relations.v1.KesselRelationsHealthServiceGrpc;
import org.project_kessel.clients.KesselClient;

public class HealthClient extends KesselClient<KesselRelationsHealthServiceGrpc.KesselRelationsHealthServiceStub, KesselRelationsHealthServiceGrpc.KesselRelationsHealthServiceBlockingStub> {
    private static final Logger logger = Logger.getLogger(HealthClient.class.getName());

    HealthClient(Channel channel) {
        super(KesselRelationsHealthServiceGrpc.newStub(channel), KesselRelationsHealthServiceGrpc.newBlockingStub(channel));
    }

     public GetReadyzResponse readyz(GetReadyzRequest request) {
        return blockingStub.getReadyz(request);
    }
    
     public GetLivezResponse livez(GetLivezRequest request) {
        return blockingStub.getLivez(request);
    }
}
