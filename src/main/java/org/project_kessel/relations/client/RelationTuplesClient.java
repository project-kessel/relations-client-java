package org.project_kessel.relations.client;

import io.grpc.Channel;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.operators.multi.processors.UnicastProcessor;
import java.util.Iterator;
import org.project_kessel.api.relations.v1beta1.CreateTuplesRequest;
import org.project_kessel.api.relations.v1beta1.CreateTuplesResponse;
import org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest;
import org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse;
import org.project_kessel.api.relations.v1beta1.ImportBulkTuplesRequest;
import org.project_kessel.api.relations.v1beta1.ImportBulkTuplesResponse;
import org.project_kessel.api.relations.v1beta1.KesselTupleServiceGrpc;
import org.project_kessel.api.relations.v1beta1.ReadTuplesRequest;
import org.project_kessel.api.relations.v1beta1.ReadTuplesResponse;
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
     * @param responseObserver
     * @return
     */
    public StreamObserver<ImportBulkTuplesRequest> importBulkTuples(
            StreamObserver<ImportBulkTuplesResponse> responseObserver) {
        return asyncStub.importBulkTuples(responseObserver);
    }

    public Uni<ImportBulkTuplesResponse> importBulkTuplesUni(Multi<ImportBulkTuplesRequest> bulkTuplesRequests) {
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
        bulkTuplesRequests.subscribe().with(
                requestObserver::onNext,
                requestObserver::onError,
                requestObserver::onCompleted);

        return Uni.createFrom().publisher(responseProcessor);
    }
}
