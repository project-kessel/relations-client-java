package org.project_kessel.relations.client;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor;
import org.project_kessel.api.relations.v0.KesselLookupServiceGrpc;
import org.project_kessel.api.relations.v0.LookupSubjectsRequest;
import org.project_kessel.api.relations.v0.LookupSubjectsResponse;

import java.util.Iterator;
import java.util.logging.Logger;

public class LookupClient {
    private static final Logger logger = Logger.getLogger(LookupClient.class.getName());

    private final KesselLookupServiceGrpc.KesselLookupServiceStub asyncStub;
    private final KesselLookupServiceGrpc.KesselLookupServiceBlockingStub blockingStub;

    LookupClient(Channel channel) {
        asyncStub = KesselLookupServiceGrpc.newStub(channel);
        blockingStub = KesselLookupServiceGrpc.newBlockingStub(channel);
    }

    public void lookupSubjects(LookupSubjectsRequest request, StreamObserver<LookupSubjectsResponse> responseObserver) {
        asyncStub.lookupSubjects(request, responseObserver);
    }

    public Iterator<LookupSubjectsResponse> lookupSubjects(LookupSubjectsRequest request) {
        return blockingStub.lookupSubjects(request);
    }

    public Multi<LookupSubjectsResponse> lookupSubjectsMulti(LookupSubjectsRequest request) {
        final UnicastProcessor<LookupSubjectsResponse> responseProcessor = UnicastProcessor.create();

        var streamObserver = new StreamObserver<LookupSubjectsResponse>() {
            @Override
            public void onNext(LookupSubjectsResponse response) {
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

        var multi = Multi.createFrom().publisher(responseProcessor);
        lookupSubjects(request, streamObserver);

        return multi;
    }
}
