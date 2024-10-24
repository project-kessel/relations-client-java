package org.project_kessel.relations.client;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_kessel.api.relations.v1beta1.*;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RelationTuplesClientServerTest {
    static final int SERVER_PORT = 9000;

    static RelationTuplesClient client;

    @BeforeAll
    static void setupAll() {
        var manager = RelationsGrpcClientsManager.forInsecureClients("0.0.0.0:" + SERVER_PORT);
        client = manager.getRelationTuplesClient();
    }

    @AfterAll
    static void tearDownAll() {
        RelationsGrpcClientsManager.shutdownAll();
    }

    @BeforeEach
    void setUp() {
        deleteRelationships();
    }

    @Test
    void testImportBulkTuplesUni() {
        List<Relationship> batch1 = relationshipListMaker(0, 10);
        List<Relationship> batch2 = relationshipListMaker(15, 20);
        ImportBulkTuplesRequest req1 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch1).build();
        ImportBulkTuplesRequest req2 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch2).build();
        Multi<ImportBulkTuplesRequest> bulkTuplesRequests = Multi.createFrom().items(req1, req2);
        Uni<ImportBulkTuplesResponse> importBulkTuplesResponseUni = client.importBulkTuplesUni(bulkTuplesRequests);

        var failure = new AtomicReference<Throwable>();
        var response = importBulkTuplesResponseUni
                .onFailure().invoke(failure::set)
                .onFailure().recoverWithNull()
                .await().indefinitely();

        assertEquals(15, response.getNumImported());
        assertNull(failure.get());
        // Without read after write consistency, we try to wait for relations-api to give an updated read view
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        assertEquals(15, countStoredRelationsips());
    }

    @Test
    void testCommonRelationshipInBatchesFails() {
        List<Relationship> batch1 = relationshipListMaker(0, 10);
        List<Relationship> batch2 = relationshipListMaker(9, 10); // repeat rel from batch 1 -- induce processing failure (but only at the end)
        List<Relationship> batch3 = relationshipListMaker(15, 20);
        ImportBulkTuplesRequest req1 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch1).build();
        ImportBulkTuplesRequest req2 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch2).build();
        ImportBulkTuplesRequest req3 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch3).build();
        Multi<ImportBulkTuplesRequest> bulkTuplesRequests = Multi.createFrom().items(req1, req2, req3);
        Uni<ImportBulkTuplesResponse> importBulkTuplesResponseUni = client.importBulkTuplesUni(bulkTuplesRequests);

        var failure = new AtomicReference<Throwable>();

        /*
         * debugger verifies that responseObserver onComplete() not called in error scenarios
         */

        // First option -- catch Exception
//        ImportBulkTuplesResponse response = null;
//        try {
//            response = importBulkTuplesResponseUni.await().indefinitely();
//        } catch (Throwable t) {
//            failure.set(t);
//            // - debugger verifies that onComplete() in not called
//        }

        // Second option -- store and suppress exception and complete Uni (not responseObserver which does not complete)
        var response = importBulkTuplesResponseUni
                .onFailure().invoke(failure::set)
                .onFailure().recoverWithNull()
                .await().indefinitely();

        assertNull(response);
        assertEquals(io.grpc.StatusRuntimeException.class, failure.get().getClass());
        assertTrue(failure.get().getMessage().startsWith("ALREADY_EXISTS: error import bulk tuples: error receiving "
                + "response from Spicedb for bulkimport request"));
        // Without read after write consistency, we try to wait for relations-api to give an updated read view
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        assertEquals(0, countStoredRelationsips());
    }

    @Test
    void testRelationshipsWithInvalidSchemaInBatchFailure() {
        List<Relationship> batch1 = relationshipListMaker(0, 10);
        List<Relationship> batch2 = badRelationshipListMaker(15, 20); // create relationships for non-existent type
        ImportBulkTuplesRequest req1 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch1).build();
        ImportBulkTuplesRequest req2 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch2).build();
        Multi<ImportBulkTuplesRequest> bulkTuplesRequests = Multi.createFrom().items(req1, req2);
        Uni<ImportBulkTuplesResponse> importBulkTuplesResponseUni = client.importBulkTuplesUni(bulkTuplesRequests);

        var failure = new AtomicReference<Throwable>();
        var response = importBulkTuplesResponseUni
                .onFailure().invoke(failure::set)
                .onFailure().recoverWithNull()
                .await().indefinitely();

        assertNull(response);
        assertEquals(io.grpc.StatusRuntimeException.class, failure.get().getClass());
        assertTrue(failure.get().getMessage().startsWith("UNKNOWN: error import bulk tuples: error receiving response"
                + " from Spicedb for bulkimport request: rpc error: code = Unknown desc = ERROR: COPY from stdin "
                + "failed: rpc error: code = InvalidArgument desc"));
    }

    @Test
    void testRequestMultiFailsWithError() {
        List<Relationship> batch1 = relationshipListMaker(0, 10);
        ImportBulkTuplesRequest req1 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch1).build();
        Multi<ImportBulkTuplesRequest> bulkTuplesRequests = Multi.createFrom().items(
                () -> IntStream.range(0, 2).mapToObj(
                        i -> {
                            if (i == 0) {
                                return req1;
                            } else {
                                throw new RuntimeException("AAAHHHH!");
                            }
                        }));
        Uni<ImportBulkTuplesResponse> importBulkTuplesResponseUni = client.importBulkTuplesUni(bulkTuplesRequests);

        var failure = new AtomicReference<Throwable>();
        var response = importBulkTuplesResponseUni
                .onFailure().invoke(failure::set)
                .onFailure().recoverWithNull()
                .await().indefinitely();

        assertNull(response);
        assertEquals(io.grpc.StatusRuntimeException.class, failure.get().getClass());
        assertTrue(failure.get().getMessage().startsWith(
                "CANCELLED: Cancelled by client with StreamObserver.onError()"));
    }

    List<Relationship> relationshipListMaker(int startPostfix, int endPostfix) {
        return IntStream.range(startPostfix, endPostfix).mapToObj(i ->
                relationshipMaker("thing_" + i, "workspace_" + i)
        ).collect(Collectors.toList());
    }

    List<Relationship> badRelationshipListMaker(int startPostfix, int endPostfix) {
        return IntStream.range(startPostfix, endPostfix).mapToObj(i ->
                badRelationshipMaker("thing_" + i, "workspace_" + i)
        ).collect(Collectors.toList());
    }

    void deleteRelationships() {
        DeleteTuplesRequest req = DeleteTuplesRequest.newBuilder()
                .setFilter(RelationTupleFilter.newBuilder()
                        .setResourceNamespace("rbac")
                        .setResourceType("thing")
                        .build())
                .build();
        client.deleteTuples(req);
    }

    Relationship relationshipMaker(String thingId, String workspaceId) {
        return Relationship.newBuilder()
                .setSubject(SubjectReference.newBuilder()
                        .setSubject(ObjectReference.newBuilder()
                                .setType(ObjectType.newBuilder()
                                        .setNamespace("rbac")
                                        .setName("workspace").build())
                                .setId(workspaceId).build())
                        .build())
                .setRelation("workspace")
                .setResource(ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder()
                                .setNamespace("rbac")
                                .setName("thing").build())
                        .setId(thingId)
                        .build())
                .build();
    }

    Relationship badRelationshipMaker(String thingId, String workspaceId) {
        return Relationship.newBuilder()
                .setSubject(SubjectReference.newBuilder()
                        .setSubject(ObjectReference.newBuilder()
                                .setType(ObjectType.newBuilder()
                                        .setNamespace("rbac")
                                        .setName("nOtInSChema").build())
                                .setId(workspaceId).build())
                        .build())
                .setRelation("workspace")
                .setResource(ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder()
                                .setNamespace("rbac")
                                .setName("thing").build())
                        .setId(thingId)
                        .build())
                .build();
    }

    long countStoredRelationsips() {
        ReadTuplesRequest request = ReadTuplesRequest.newBuilder()
                .setFilter(RelationTupleFilter.newBuilder()
                        .setResourceNamespace("rbac")
                        .setResourceType("thing")
                        .build())
                .build();
        var relResponses = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(client.readTuples(request), Spliterator.ORDERED), false);
        return relResponses.count();
    }
}
