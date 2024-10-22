package org.project_kessel.relations.client;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.project_kessel.api.relations.v1beta1.*;
import org.project_kessel.clients.KesselClient;

public class RelationTuplesClient extends KesselClient<KesselTupleServiceGrpc.KesselTupleServiceStub,
        KesselTupleServiceGrpc.KesselTupleServiceBlockingStub> {
    RelationTuplesClient(Channel channel) {
        super(KesselTupleServiceGrpc.newStub(channel), KesselTupleServiceGrpc.newBlockingStub(channel));
    }

    /**
     *
     */
    public void createTuples(CreateTuplesRequest request,
                             StreamObserver<CreateTuplesResponse> responseObserver) {
        asyncStub.createTuples(request, responseObserver);
    }

    /**
     *
     */
    public CreateTuplesResponse createTuples(CreateTuplesRequest request) {
        return blockingStub.createTuples(request);
    }

    /**
     *
     */
    public void readTuples(ReadTuplesRequest request,
                           StreamObserver<ReadTuplesResponse> responseObserver) {
        asyncStub.readTuples(request, responseObserver);
    }

    /**
     *
     */
    public Iterator<ReadTuplesResponse> readTuples(ReadTuplesRequest request) {
        return blockingStub.readTuples(request);
    }

    /**
     *
     */
    public void deleteTuples(DeleteTuplesRequest request,
                             StreamObserver<DeleteTuplesResponse> responseObserver) {
        asyncStub.deleteTuples(request, responseObserver);
    }

    /**
     *
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

    /**
     * Enables client streaming of ImportBulkTuplesRequests and ImportBulkTuplesResponse.
     * See https://grpc.io/docs/languages/java/basics/#client-side-streaming-rpc-1 for how to use observers in clients.
     * We may simplify the client implementation with mutiny reactive calls in future once requirements become clearer.
     * @param responseObserver
     * @return
     */
    public StreamObserver<ImportBulkTuplesRequest> importBulkTuples(
            StreamObserver<ImportBulkTuplesResponse> responseObserver) {
        return asyncStub.importBulkTuples(responseObserver);
    }

    /*
     * Looks ok if I feel like I get nothing from async.
     */
    public ImportBulkTuplesResponse importBulkTuples(ImportBulkTuplesRequest bulkTuplesRequest) {
        return importBulkTuplesUni(Uni.createFrom().item(bulkTuplesRequest)).await().indefinitely(); // TODO: add timeout
    }

    /*
     * Looks good
     */
    public Uni<ImportBulkTuplesResponse> importBulkTuples2(ImportBulkTuplesRequest bulkTuplesRequest) {
        return importBulkTuplesUni(Uni.createFrom().item(bulkTuplesRequest));
    }

    /*
     * Not sure the multi gives us much, see importBulkTuplesUni(Multi<Relationship> relationshipsStream), below
     */
    public ImportBulkTuplesResponse importBulkTuples3(Multi<Relationship> relationshipsStream) {
        Uni<ImportBulkTuplesResponse> responseUni = importBulkTuplesUni(relationshipsStream);

        return responseUni.await().indefinitely();
    }

    /*
     * Looks ok.
     */
    public Uni<ImportBulkTuplesResponse> importBulkTuplesUni(Uni<ImportBulkTuplesRequest> bulkTuplesRequest) {
        final UnicastProcessor<ImportBulkTuplesResponse> responseProcessor = UnicastProcessor.create();

        var responseObserver = new StreamObserver<ImportBulkTuplesResponse>() {
            @Override
            public void onNext(ImportBulkTuplesResponse response) {
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

        StreamObserver<ImportBulkTuplesRequest> requestObserver = importBulkTuples(responseObserver);
        bulkTuplesRequest.onItem()
                .invoke(requestObserver::onNext)
                .invoke(requestObserver::onCompleted) // we're expecting just one request
                .onFailure().invoke(responseObserver::onError)
                .await().indefinitely(); // TODO: think about timeout

        return Uni.createFrom().publisher(responseProcessor);
    }

    /**
     * I thought we could stream in the relationships, but we are streaming the requests really... so I'm not sure
     * if we get much
     */
    public Uni<ImportBulkTuplesResponse> importBulkTuplesUni(Multi<Relationship> relationshipsStream) {
        final UnicastProcessor<ImportBulkTuplesResponse> responseProcessor = UnicastProcessor.create();

        var responseObserver = new StreamObserver<ImportBulkTuplesResponse>() {
            @Override
            public void onNext(ImportBulkTuplesResponse response) {
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

        StreamObserver<ImportBulkTuplesRequest> requestObserver = importBulkTuples(responseObserver);
        ImportBulkTuplesRequest importBulkTuplesRequest = ImportBulkTuplesRequest.newBuilder()
                // I thought we could somehow stream relationships here, but we have to provide a list
                // since we have to block here to build the list, what do we really get from multi?
                // maybe we avoid having to traverse the list twice...
                .addAllTuples(relationshipsStream.collect().asList().await().indefinitely())
                .build();
        requestObserver.onNext(importBulkTuplesRequest);
        requestObserver.onCompleted();

        return Uni.createFrom().publisher(responseProcessor);
    }
}
