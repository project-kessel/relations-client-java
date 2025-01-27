package org.project_kessel.relations.example;

import io.grpc.stub.StreamObserver;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse;
import org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest;
import org.project_kessel.api.relations.v1beta1.LookupResourcesResponse;
import org.project_kessel.api.relations.v1beta1.LookupResourcesRequest;
import org.project_kessel.api.relations.v1beta1.ReadTuplesRequest;
import org.project_kessel.api.relations.v1beta1.ReadTuplesResponse;
import org.project_kessel.api.relations.v1beta1.RelationTupleFilter;
import org.project_kessel.api.relations.v1beta1.CheckRequest;
import org.project_kessel.api.relations.v1beta1.CheckResponse;
import org.project_kessel.api.relations.v1beta1.ObjectReference;
import org.project_kessel.api.relations.v1beta1.ObjectType;
import org.project_kessel.api.relations.v1beta1.SubjectReference;
import org.project_kessel.relations.client.RelationsGrpcClientsManager;

public class Caller {

    static final String userName = "joe";
    static final String subjectType = "principal";
    static final String permission = "view";
    static final String namespace = "rbac";
    static final String resourceType = "integration";
    static final String resourceId = "my_thing";

    public static void main(String[] argv) {
        var url = "localhost:9000";

        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var checkClient = clientsManager.getCheckClient();

        var checkRequest = CheckRequest.newBuilder()
                .setSubject(SubjectReference.newBuilder()
                        .setSubject(ObjectReference.newBuilder()
                                .setType(ObjectType.newBuilder()
                                        .setNamespace(namespace)
                                        .setName(subjectType).build())
                                .setId(userName).build())
                        .build())
                .setRelation(permission)
                .setResource(ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder()
                                .setNamespace("notifications")
                                .setName(resourceType).build())
                        .setId(resourceId)
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

        if (permitted) {
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

                if (permitted) {
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
                    if (permitted) {
                        System.out.println("Reactive non-blocking: Permitted");
                    } else {
                        System.out.println("Reactive non-blocking: Denied");
                    }
                })
                .await().indefinitely();

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
                .setResourceNamespace("notifications")
                .setResourceType(resourceType).build();
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

    public static void lookupSubjectsExample() {
        var url = "localhost:9000";

        /* Make a secure connection with grpc TLS this time */
        var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
        var lookupClient = clientsManager.getLookupClient();

        var lookupSubjectsRequest = LookupSubjectsRequest.newBuilder().setResource(
                ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder().setNamespace("notifications").setName(resourceType).build())
                        .setId(resourceId))
                .setRelation(permission)
                .setSubjectType(ObjectType.newBuilder().setNamespace(namespace).setName(subjectType)).build();

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

        var lookupResourcesRequest = LookupResourcesRequest.newBuilder()
                .setResourceType(ObjectType.newBuilder().setNamespace("notifications").setName(resourceType))
                .setRelation(permission).setSubject(SubjectReference.newBuilder()
                        .setSubject(ObjectReference.newBuilder()
                                .setType(ObjectType.newBuilder()
                                        .setNamespace(namespace)
                                        .setName(subjectType).build())
                                .setId(userName).build())
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
