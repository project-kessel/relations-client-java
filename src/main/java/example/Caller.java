package example;

import api.check.v1.CheckRequest;
import api.check.v1.CheckResponse;
import api.relations.v1.*;
import client.RelationsGrpcClientsManager;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Caller {

    public static void main(String[] argv) {
        var url = "localhost:9000";

        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var checkClient = clientsManager.getCheckClient();

        var userName = "joe";
        var permission = "view";
        var thing = "resource";

        var checkRequest = CheckRequest.newBuilder()
                .setSubject(SubjectReference.newBuilder()
                                .setObject(ObjectReference.newBuilder()
                                        .setType("user")
                                        .setId(userName).build())
                                .build())
                .setRelation(permission)
                .setObject(ObjectReference.newBuilder()
                        .setType("thing")
                        .setId(thing)
                        .build())
                .build();

        /*
         * Choice of blocking v async is made by method signature.
         * - Just CheckRequest in args and CheckResponse returned indicates blocking.
         * - StreamObserver<CheckResponse> in args indicates async.
         */

        /* Blocking */
        var checkResponse = checkClient.check(checkRequest);
        var permitted = checkResponse.getAllowed() == CheckResponse.Allowed.ALLOWED_TRUE;

        if(permitted) {
            System.out.println("Blocking: Permitted");
        } else {
            System.out.println("Blocking: Denied");
        }

        /*
         * Non-blocking
         */

        final CountDownLatch conditionLatch = new CountDownLatch(1);
        var streamObserver = new StreamObserver<CheckResponse>() {
            @Override
            public void onNext(CheckResponse response) {
                /* Because we don't return a stream, but a response object with all the relationships inside,
                 * we get no benefit from an async/non-blocking call right now. It all returns at once.
                 */
                var permitted = response.getAllowed() == CheckResponse.Allowed.ALLOWED_TRUE;

                if(permitted) {
                    System.out.println("Non-blocking: Permitted");
                } else {
                    System.out.println("Non-blocking: Denied");
                }
            }

            @Override
            public void onError(Throwable t) {
                // TODO:
            }

            @Override
            public void onCompleted() {
                conditionLatch.countDown();
            }
        };

        checkClient.check(checkRequest, streamObserver);

        /* Use a passed-in countdownlatch to wait for the result async on the main thread */
        try {
            conditionLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
         * Non-blocking reactive style
         */

        Uni<CheckResponse> uni = checkClient.checkUni(checkRequest);

        /* Pattern where we may want collect all the responses, but still operate on each as it comes in. */
        CheckResponse cr = uni.onItem()
                .invoke(() -> {
                    if(permitted) {
                        System.out.println("Reactive non-blocking: Permitted");
                    } else {
                        System.out.println("Reactive non-blocking: Denied");
                    }
                })
                .await().indefinitely();

        getRelationshipsExample();
    }

    public static void getRelationshipsExample() {
        var url = "localhost:9000";

        /* Make a secure connection with grpc TLS this time */
        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var relationTuplesClient = clientsManager.getRelationTuplesClient();

        var roleBindingsOnWorkspaceFilter = RelationshipFilter.newBuilder()
                .setObjectType("role_binding").build();
        var readRelationshipsRequest = ReadRelationshipsRequest.newBuilder()
                .setFilter(roleBindingsOnWorkspaceFilter).build();

        /*
         * Blocking
         */

        var response = relationTuplesClient.readRelationships(readRelationshipsRequest);

        /* We could make this a stream rather than a list -- which is implied/inferred from
         * the proto -- but for the blocking call there is obviously no concurrency benefit.
         * List may also change, because I think we need a proto spec change here to specify a stream.
         * */
        var relationshipTuples = response.getRelationshipsList();
        System.out.println("Blocking relationship tuples: " + relationshipTuples);

        /*
         * Non-blocking
         */

        final CountDownLatch conditionLatch = new CountDownLatch(1);
        var streamObserver = new StreamObserver<ReadRelationshipsResponse>() {
            @Override
            public void onNext(ReadRelationshipsResponse response) {
                /* Because we don't return a stream, but a response object with all the relationships inside,
                 * we get no benefit from an async/non-blocking call right now. It all returns at once.
                 */
                var relationshipTuples = response.getRelationshipsList();
                System.out.println("Non-blocking relationship tuples: " + relationshipTuples);
            }

            @Override
            public void onError(Throwable t) {
                // TODO:
            }

            @Override
            public void onCompleted() {
                conditionLatch.countDown();
            }
        };
        relationTuplesClient.readRelationships(readRelationshipsRequest, streamObserver);

        /* Use a passed-in countdownlatch to wait for the result async on the main thread */
        try {
            conditionLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
         * Non-blocking reactive style
         */

        Multi<ReadRelationshipsResponse> multi = relationTuplesClient.readRelationshipsMulti(readRelationshipsRequest);

        /* Pattern where we may want collect all the responses, but still operate on each as it comes in. */
        List<ReadRelationshipsResponse> list = multi.onItem()
                .invoke(() -> {
                    var tuples = response.getRelationshipsList();
                    System.out.println("Reactive non-blocking relationship tuples: " + tuples);
                })
                .collect().asList().await().indefinitely();

    }

}
