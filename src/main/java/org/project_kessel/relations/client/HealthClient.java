package org.project_kessel.relations.client;

import java.util.logging.Logger;

import io.grpc.Channel;
import org.project_kessel.api.relations.v1.GetLivezResponse;
import org.project_kessel.api.relations.v1.GetLivezRequest;
import org.project_kessel.api.relations.v1.GetReadyzResponse;
import org.project_kessel.api.relations.v1.GetReadyzRequest;
import org.project_kessel.api.relations.v1.KesselHealthServiceGrpc;
import org.project_kessel.api.relations.v1beta1.KesselCheckServiceGrpc;
import org.project_kessel.clients.KesselClient;

public class HealthClient extends KesselClient<KesselHealthServiceGrpc.KesselHealthServiceStub, KesselHealthServiceGrpc.KesselHealthServiceBlockingStub> {
    private static final Logger logger = Logger.getLogger(HealthClient.class.getName());

    HealthClient(Channel channel) {
        super(KesselHealthServiceGrpc.newStub(channel), KesselHealthServiceGrpc.newBlockingStub(channel));
    }

     public GetReadyzResponse readyz(GetReadyzRequest request) {
        return blockingStub.getReadyz(request);
    }
    
     public GetLivezResponse livez(GetLivezRequest request) {
        return blockingStub.getLivez(request);
    }
}
