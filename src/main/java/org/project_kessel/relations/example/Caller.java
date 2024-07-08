package org.project_kessel.relations.example;

import build.buf.gen.kessel.relations.v1beta1.*;
import build.buf.gen.kessel.relations.v1.*;
import org.project_kessel.relations.client.RelationsGrpcClientsManager;
import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
                                .setSubject(ObjectReference.newBuilder()
                                        .setType(ObjectType.newBuilder()
                                                .setName("user").build())
                                        .setId(userName).build())
                                .build())
                .setRelation(permission)
                .setResource(ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder()
                                .setName("thing").build())
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

        getLivezExample();
        getReadyzExample();
        getRelationshipsExample();
        lookupSubjectsExample();
        lookupResourcesExample();
    }

    public static void getRelationshipsExample() {
        var url = "localhost:9000";

        /* Make a secure connection with grpc TLS this time */
        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var relationTuplesClient = clientsManager.getRelationTuplesClient();

        var roleBindingsOnWorkspaceFilter = RelationTupleFilter.newBuilder()
                .setResourceType("role_binding").build();
        var readRelationshipsRequest = ReadTuplesRequest.newBuilder()
                .setFilter(roleBindingsOnWorkspaceFilter).build();

        /*
         * Blocking
         */

        var responseIterator = relationTuplesClient.readTuples(readRelationshipsRequest);
        Iterable<ReadTuplesResponse> iterable = () -> responseIterator;

        var relationshipTuples = StreamSupport.stream(iterable.spliterator(), false)
                .map(ReadTuplesResponse::getTuple)
                .collect(Collectors.toList());
        System.out.println("Blocking relationship tuples: " + relationshipTuples);

        /*
         * Non-blocking
         */

        final CountDownLatch conditionLatch = new CountDownLatch(1);
        final List<ReadTuplesResponse> responses = Collections.synchronizedList(new ArrayList<>());
        var streamObserver = new StreamObserver<ReadTuplesResponse>() {
            @Override
            public void onNext(ReadTuplesResponse response) {
                // do something async
                var tuple = response.getTuple();
                System.out.println("Non-blocking relationship tuple: " + tuple);

                // add response to list to follow-up on main thread
                responses.add(response);
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
        relationTuplesClient.readTuples(readRelationshipsRequest, streamObserver);

        /* Use a passed-in countdownlatch to wait for the result async on the main thread */
        try {
            conditionLatch.await();

            System.out.println("Collected non-blocking responses: " + responses);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
         * Non-blocking reactive style
         */

        Multi<ReadTuplesResponse> multi = relationTuplesClient.readTuplesMulti(readRelationshipsRequest);

        /* Pattern where we may want collect all the responses, but still operate on each as it comes in. */
        List<ReadTuplesResponse> responses2 = multi.onItem()
                .invoke(response -> {
                    var tuple = response.getTuple();
                    System.out.println("Reactive non-blocking relationship tuples: " + tuple);
                })
                .collect().asList().await().indefinitely();

        System.out.println("Collected reactive non-blocking responses: " + responses2);

    }

    public static void getLivezExample() {
        var url = "localhost:9000";

        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var healthClient = clientsManager.getHealthClient();

        var getLivezRequest = GetLivezRequest.newBuilder().build();

        /* Blocking */
        var getLivezResponse = healthClient.livez(getLivezRequest);
        var status = getLivezResponse.getStatus().equals("OK");

        if (status) {
            System.out.println("Blocking: OK");
        } else {
            System.out.println("Blocking: Unavailable");
        }
    }


    public static void getReadyzExample() {
        var url = "localhost:9000";

        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var healthClient = clientsManager.getHealthClient();

        var getReadyzRequest = GetReadyzRequest.newBuilder().build();
    
        /* Blocking */
        var getReadyzResponse = healthClient.readyz(getReadyzRequest);
        var status = getReadyzResponse.getStatus().equals("OK");

        if (status) {
            System.out.println("Blocking: OK");
        } else {
            System.out.println("Blocking: Unavailable");
        }
    }

    public static void lookupSubjectsExample() {
        var url = "localhost:9000";

        /* Make a secure connection with grpc TLS this time */
        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var lookupClient = clientsManager.getLookupClient();

        var lookupSubjectsRequest = LookupSubjectsRequest.newBuilder().setResource(
                ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder().setName("thing").build())
                        .setId("resource")
        ).setRelation("view").setSubjectType(ObjectType.newBuilder().setName("user")).build();

        /*
         * Blocking
         */

        var subjectsIterator = lookupClient.lookupSubjects(lookupSubjectsRequest);
        Iterable<LookupSubjectsResponse> iterable = () -> subjectsIterator;

        var subjects = StreamSupport.stream(iterable.spliterator(), false)
                .map(LookupSubjectsResponse::getSubject)
                .collect(Collectors.toList());
        System.out.println("Blocking lookup subjects: " + subjects);

        /*
         * Non-blocking reactive style
         */

        Multi<LookupSubjectsResponse> multi = lookupClient.lookupSubjectsMulti(lookupSubjectsRequest);

        /* Pattern where we may want collect all the responses, but still operate on each as it comes in. */
        List<LookupSubjectsResponse> responses2 = multi.onItem()
                .invoke(response -> {
                    var tuple = response.getSubject();
                    System.out.println("Reactive non-blocking lookup subjects: " + tuple);
                })
                .collect().asList().await().indefinitely();

        System.out.println("Collected reactive non-blocking responses: " + responses2);
    }

    public static void lookupResourcesExample() {
        var url = "localhost:9000";

        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var lookupClient = clientsManager.getLookupClient();

        var lookupResourcesRequest = LookupResourcesRequest.newBuilder().
                setResourceType(ObjectType.newBuilder().setName("thing"))
              .setRelation("view").setSubject(SubjectReference.newBuilder()
                .setSubject(ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder()
                                .setName("user").build())
                        .setId("bob").build())
                .build()).build();

        var resourceIterator = lookupClient.lookupResources(lookupResourcesRequest);
        Iterable<LookupResourcesResponse> iterable = () -> resourceIterator;

        var resources = StreamSupport.stream(iterable.spliterator(), false)
                .map(LookupResourcesResponse::getResource)
                .collect(Collectors.toList());
        System.out.println("Blocking lookup resources: " + resources);

        /*
         * Non-blocking reactive style
         */

        Multi<LookupResourcesResponse> multi = lookupClient.lookupResourcesMulti(lookupResourcesRequest);

        /* Pattern where we may want collect all the responses, but still operate on each as it comes in. */
        List<LookupResourcesResponse> responses2 = multi.onItem()
                .invoke(response -> {
                    var tuple = response.getResource();
                    System.out.println("Reactive non-blocking lookup resources: " + tuple);
                })
                .collect().asList().await().indefinitely();

        System.out.println("Collected reactive non-blocking responses: " + responses2);
    }

}
