package org.project_kessel.relations.client;

import org.project_kessel.api.relations.v1beta1.CheckRequest;
import org.project_kessel.api.relations.v1beta1.CheckResponse;
import org.project_kessel.api.relations.v1beta1.KesselCheckServiceGrpc;
import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor;

import java.util.logging.Logger;

public class CheckClient {
    private static final Logger logger = Logger.getLogger(CheckClient.class.getName());

    private final KesselCheckServiceGrpc.KesselCheckServiceStub asyncStub;
    private final KesselCheckServiceGrpc.KesselCheckServiceBlockingStub blockingStub;

    CheckClient(Channel channel) {
        asyncStub = KesselCheckServiceGrpc.newStub(channel);
        blockingStub = KesselCheckServiceGrpc.newBlockingStub(channel);
    }

    public void check(CheckRequest request,
                      StreamObserver<CheckResponse> responseObserver) {
        asyncStub.check(request, responseObserver);
    }

    public Uni<CheckResponse> checkUni(CheckRequest request) {
        final UnicastProcessor<CheckResponse> responseProcessor = UnicastProcessor.create();

        var streamObserver = new StreamObserver<CheckResponse>() {
            @Override
            public void onNext(CheckResponse response) {
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
        check(request, streamObserver);

        return uni;
    }

    public CheckResponse check(CheckRequest request) {
        return blockingStub.check(request);
    }
}