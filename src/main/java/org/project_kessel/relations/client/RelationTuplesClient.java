package org.project_kessel.relations.client;

import org.project_kessel.api.relations.v1beta1.*;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor;

import java.util.Iterator;

public class RelationTuplesClient {
    private final KesselTupleServiceGrpc.KesselTupleServiceStub asyncStub;
    private final KesselTupleServiceGrpc.KesselTupleServiceBlockingStub blockingStub;

    RelationTuplesClient(Channel channel) {
        asyncStub = KesselTupleServiceGrpc.newStub(channel);
        blockingStub = KesselTupleServiceGrpc.newBlockingStub(channel);
    }

    /**
     */
    public void createTuples(CreateTuplesRequest request,
                             StreamObserver<CreateTuplesResponse> responseObserver) {
        asyncStub.createTuples(request, responseObserver);
    }

    /**
     */
    public void readTuples(ReadTuplesRequest request,
                           StreamObserver<ReadTuplesResponse> responseObserver) {
        asyncStub.readTuples(request, responseObserver);
    }

    /**
     */
    public void deleteTuples(DeleteTuplesRequest request,
                             StreamObserver<DeleteTuplesResponse> responseObserver) {
        asyncStub.deleteTuples(request, responseObserver);
    }

    /**
     */
    public CreateTuplesResponse createTuples(CreateTuplesRequest request) {
        return blockingStub.createTuples(request);
    }

    /**
     */
    public Iterator<ReadTuplesResponse> readTuples(ReadTuplesRequest request) {
        return blockingStub.readTuples(request);
    }

    /**
     */
    public DeleteTuplesResponse deleteTuples(DeleteTuplesRequest request) {
        return blockingStub.deleteTuples(request);
    }

    public Multi<ReadTuplesResponse> readTuplesMulti(ReadTuplesRequest request) {
        final UnicastProcessor<ReadTuplesResponse> responseProcessor = UnicastProcessor.create();

        var streamObserver = new StreamObserver<ReadTuplesResponse>() {
            @Override
            public void onNext(ReadTuplesResponse response) {
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
        readTuples(request, streamObserver);

        return multi;
    }
}
