package org.project_kessel.relations.client;

import java.util.logging.Logger;

import io.grpc.Channel;
import org.project_kessel.api.relations.v1.*;

public class HealthClient {
    private static final Logger logger = Logger.getLogger(HealthClient.class.getName());

    private final KesselHealthServiceGrpc.KesselHealthServiceBlockingStub blockingStub;

    HealthClient(Channel channel) {
        blockingStub = KesselHealthServiceGrpc.newBlockingStub(channel);
    }

     public GetReadyzResponse readyz(GetReadyzRequest request) {
        return blockingStub.getReadyz(request);
    }
    
     public GetLivezResponse livez(GetLivezRequest request) {
        return blockingStub.getLivez(request);
    }
}
