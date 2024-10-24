package org.project_kessel.relations.client;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project_kessel.api.relations.v1beta1.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RelationTuplesClientServerTest {
    static final int SERVER_PORT = 9000;

    static RelationTuplesClient client;

    @BeforeAll
    static void setupAll() {
        var manager = RelationsGrpcClientsManager.forInsecureClients("0.0.0.0:" + SERVER_PORT);
        client = manager.getRelationTuplesClient();
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
    }

    @Test
    void testErrorDoesNotCallOnComplete() {
        List<Relationship> batch1 = relationshipListMaker(0, 10);
        List<Relationship> batch2 = relationshipListMaker(9, 10); // repeat rel from batch 1 -- induce batch2 processing failure
        List<Relationship> batch3 = relationshipListMaker(15, 20);
        ImportBulkTuplesRequest req1 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch1).build();
        ImportBulkTuplesRequest req2 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch2).build();
        ImportBulkTuplesRequest req3 = ImportBulkTuplesRequest.newBuilder().addAllTuples(batch3).build();
        Multi<ImportBulkTuplesRequest> bulkTuplesRequests = Multi.createFrom().items(req1, req2, req3);
        Uni<ImportBulkTuplesResponse> importBulkTuplesResponseUni = client.importBulkTuplesUni(bulkTuplesRequests);

        var failure = new AtomicReference<Throwable>();

        /*
         * debugger verifies that responseObserver onComplete() in error scenarios
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
        // TODO: add assert to show no relations are written -- verified manually that by looking at spicedb
    }

    List<Relationship> relationshipListMaker(int startPostfix, int endPostfix) {
        return IntStream.range(startPostfix, endPostfix).mapToObj(i ->
                relationshipMaker("thing_" + i, "workspace_" + i)
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
}
