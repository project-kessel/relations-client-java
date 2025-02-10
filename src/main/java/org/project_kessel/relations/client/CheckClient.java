package org.project_kessel.relations.client;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor;
import java.util.logging.Logger;
import org.project_kessel.api.relations.v1beta1.CheckForUpdateRequest;
import org.project_kessel.api.relations.v1beta1.CheckForUpdateResponse;
import org.project_kessel.api.relations.v1beta1.CheckRequest;
import org.project_kessel.api.relations.v1beta1.CheckResponse;
import org.project_kessel.api.relations.v1beta1.KesselCheckServiceGrpc;
import org.project_kessel.clients.KesselClient;

public class CheckClient extends KesselClient<KesselCheckServiceGrpc.KesselCheckServiceStub,
        KesselCheckServiceGrpc.KesselCheckServiceBlockingStub> {
    private static final Logger logger = Logger.getLogger(CheckClient.class.getName());

    CheckClient(Channel channel) {
        super(KesselCheckServiceGrpc.newStub(channel), KesselCheckServiceGrpc.newBlockingStub(channel));
    }

    public void check(CheckRequest request,
            StreamObserver<CheckResponse> responseObserver) {
        asyncStub.check(request, responseObserver);
    }

    public CheckResponse check(CheckRequest request) {
        return blockingStub.check(request);
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

    public void checkForUpdate(CheckForUpdateRequest request,
            StreamObserver<CheckForUpdateResponse> responseObserver) {
        asyncStub.checkForUpdate(request, responseObserver);
    }

    public CheckForUpdateResponse checkForUpdate(CheckForUpdateRequest request) {
        return blockingStub.checkForUpdate(request);
    }

    public Uni<CheckForUpdateResponse> checkForUpdateUni(CheckForUpdateRequest request) {
        final UnicastProcessor<CheckForUpdateResponse> responseProcessor = UnicastProcessor.create();

        var streamObserver = new StreamObserver<CheckForUpdateResponse>() {
            @Override
            public void onNext(CheckForUpdateResponse response) {
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
        checkForUpdate(request, streamObserver);

        return uni;
    }
}