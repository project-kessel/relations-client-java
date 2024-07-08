package org.project_kessel.relations.client;

import java.util.logging.Logger;

import io.grpc.Channel;
import org.project_kessel.api.relations.v1.GetLivezReply;
import org.project_kessel.api.relations.v1.GetLivezRequest;
import org.project_kessel.api.relations.v1.GetReadyzReply;
import org.project_kessel.api.relations.v1.GetReadyzRequest;
import org.project_kessel.api.relations.v1.KesselHealthGrpc;

public class HealthClient {
    private static final Logger logger = Logger.getLogger(HealthClient.class.getName());

    private final KesselHealthGrpc.KesselHealthBlockingStub blockingStub;

    HealthClient(Channel channel) {
        blockingStub = KesselHealthGrpc.newBlockingStub(channel);
    }

     public GetReadyzReply readyz(GetReadyzRequest request) {
        return blockingStub.getReadyz(request);
    }
    
     public GetLivezReply livez(GetLivezRequest request) {
        return blockingStub.getLivez(request);
    }
}
