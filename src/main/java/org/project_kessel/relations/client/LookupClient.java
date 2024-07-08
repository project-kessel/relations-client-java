package org.project_kessel.relations.client;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor;
import build.buf.gen.kessel.relations.v1beta1.*;

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

    public void lookupResources(LookupResourcesRequest request, StreamObserver<LookupResourcesResponse> responseObserver) {
        asyncStub.lookupResources(request, responseObserver);
    }

    public Iterator<LookupResourcesResponse> lookupResources(LookupResourcesRequest request) {
        return blockingStub.lookupResources(request);
    }

    public Multi<LookupResourcesResponse> lookupResourcesMulti(LookupResourcesRequest request) {
        final UnicastProcessor<LookupResourcesResponse> responseProcessor = UnicastProcessor.create();

        var streamObserver = new StreamObserver<LookupResourcesResponse>() {
            @Override
            public void onNext(LookupResourcesResponse response) {
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
        lookupResources(request, streamObserver);

        return multi;
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
