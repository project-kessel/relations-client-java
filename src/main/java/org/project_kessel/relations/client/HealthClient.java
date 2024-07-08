package org.project_kessel.relations.client;

import java.util.logging.Logger;

import io.grpc.Channel;
import build.buf.gen.kessel.relations.v1.GetLivezResponse;
import build.buf.gen.kessel.relations.v1.GetLivezRequest;
import build.buf.gen.kessel.relations.v1.GetReadyzResponse;
import build.buf.gen.kessel.relations.v1.GetReadyzRequest;
import build.buf.gen.kessel.relations.v1.KesselHealthServiceGrpc;

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
