package org.project_kessel.relations.client;

import java.util.logging.Logger;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.grpc.stub.StreamObservers;
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor;

import org.project_kessel.api.relations.v1.GetLivezReply;
import org.project_kessel.api.relations.v1.GetLivezRequest;
import org.project_kessel.api.relations.v1.GetReadyzReply;
import org.project_kessel.api.relations.v1.GetReadyzRequest;
import org.project_kessel.api.relations.v1.KesselHealthGrpc;
import io.smallrye.mutiny.Uni;

public class HealthClient {
    private static final Logger logger = Logger.getLogger(CheckClient.class.getName());

    private final KesselHealthGrpc.KesselHealthStub asyncStub;
    private final KesselHealthGrpc.KesselHealthBlockingStub blockingStub;


    HealthClient(Channel channel) {
        asyncStub = KesselHealthGrpc.newStub(channel);
        blockingStub = KesselHealthGrpc.newBlockingStub(channel);
    }

    public void readyz(GetReadyzRequest request,
     StreamObserver<GetReadyzReply> responseObserver) {
        asyncStub.getReadyz(request, responseObserver);
    }
    
     public void livez(GetLivezRequest request,
     StreamObserver<GetLivezReply> responseObserver) {
        asyncStub.getLivez(request, responseObserver);
    }

     public GetReadyzReply readyz(GetReadyzRequest request) {
        return blockingStub.getReadyz(request);
    }
    
     public GetLivezReply livez(GetLivezRequest request) {
        return blockingStub.getLivez(request);
    }

    public Uni<GetReadyzReply> readyzUni(GetReadyzRequest request) {
        final UnicastProcessor<GetReadyzReply> responseProcessor = UnicastProcessor.create();

       var streamObserver = new StreamObserver<GetReadyzReply>()  {
            @Override
            public void onNext(GetReadyzReply response) {
                responseProcessor.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                responseProcessor.onError(t);
            }

            @Override
            public void onCompleted() {
                responseProcessor.onComplete();
            }
       };

        var uni = Uni.createFrom().publisher(responseProcessor);
        readyz(request, streamObserver);

        return uni;
    }

    public Uni<GetLivezReply> livezUni(GetLivezRequest request) {
        final UnicastProcessor<GetLivezReply> responseProcessor = UnicastProcessor.create();

       var streamObserver = new StreamObserver<GetLivezReply>()  {
            @Override
            public void onNext(GetLivezReply response) {
                responseProcessor.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                responseProcessor.onError(t);
            }

            @Override
            public void onCompleted() {
                responseProcessor.onComplete();
            }
       };

        var uni = Uni.createFrom().publisher(responseProcessor);
        livez(request, streamObserver);

        return uni;
    }

}
