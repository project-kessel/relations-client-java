package org.project_kessel.relations.client;

import org.project_kessel.api.relations.v1beta1.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class RelationsGrpcClientsManagerTest {

    @BeforeAll
    public static void testSetup() {
        /* Make sure all client managers shutdown/removed before tests */
        RelationsGrpcClientsManager.shutdownAll();
    }

    @AfterEach
    public void testTeardown() {
        /* Make sure all client managers shutdown/removed after each test */
        RelationsGrpcClientsManager.shutdownAll();
    }

    @Test
    public void testManagerReusePatterns() {
        var one = RelationsGrpcClientsManager.forInsecureClients("localhost:8080");
        var two = RelationsGrpcClientsManager.forInsecureClients("localhost:8080"); // same as one
        var three = RelationsGrpcClientsManager.forInsecureClients("localhost1:8080");
        var four = RelationsGrpcClientsManager.forSecureClients("localhost:8080");
        var five = RelationsGrpcClientsManager.forSecureClients("localhost1:8080");
        var six = RelationsGrpcClientsManager.forSecureClients("localhost1:8080"); // same as five

        assertNotNull(one);
        assertNotNull(two);
        assertNotNull(three);
        assertNotNull(four);
        assertNotNull(five);
        assertNotNull(six);
        assertEquals(one, two);
        assertNotEquals(two, three);
        assertEquals(five, six);
        assertNotEquals(four, five);
    }

    @Test
    public void testThreadingChaos() {
        /* Basic testing to ensure that we don't get ConcurrentModificationExceptions, or any other exceptions, when
         * creating and destroying managers on different threads. */

        try {
            Hashtable<String,RelationsGrpcClientsManager> managers = new Hashtable<>();

            int numberOfThreads = 100;
            ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
            CountDownLatch latch1 = new CountDownLatch(numberOfThreads / 3);
            CountDownLatch latch2 = new CountDownLatch(numberOfThreads * 2 / 3 - numberOfThreads / 3);
            CountDownLatch latch3 = new CountDownLatch(numberOfThreads - numberOfThreads * 2 / 3);

            /* A: Use 1/3 of threads to request/create managers at the same time. */
            for (int i = 0; i < numberOfThreads / 3; i++) {
                final int j = i;
                service.submit(() -> {
                    RelationsGrpcClientsManager manager;
                    if(j % 2 == 0) {
                        manager = RelationsGrpcClientsManager.forInsecureClients("localhost" + j);
                    } else {
                        manager = RelationsGrpcClientsManager.forSecureClients("localhost" + j);
                    }
                    managers.put("localhost" + j, manager);

                    latch1.countDown();
                });
            }

            latch1.await();
            /* B and C, below, trigger at the same time once A is done. */

            /* B: Use 1/3 of threads to shut down the above created managers. */
            for (int i = numberOfThreads / 3; i < numberOfThreads * 2 / 3; i++) {
                final int j = i - numberOfThreads / 3;
                service.submit(() -> {
                    RelationsGrpcClientsManager.shutdownManager(managers.get("localhost" + j));
                    latch2.countDown();
                });
            }

            /* C: Use 1/3 of the threads to recreate/retrieve the same managers at the same time as B. */
            for (int i = numberOfThreads * 2 / 3; i < numberOfThreads; i++) {
                final int j = i - numberOfThreads * 2 / 3;
                service.submit(() -> {
                    RelationsGrpcClientsManager manager;
                    if(j % 2 == 0) {
                        manager = RelationsGrpcClientsManager.forInsecureClients("localhost" + j);
                    } else {
                        manager = RelationsGrpcClientsManager.forSecureClients("localhost" + j);
                    }
                    managers.put("localhost" + j, manager);

                    latch3.countDown();
                });
            }
            latch2.await();
            latch3.await();
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void testManagerReuseInternal() throws Exception {
        RelationsGrpcClientsManager.forInsecureClients("localhost:8080");
        RelationsGrpcClientsManager.forInsecureClients("localhost:8080"); // same as one
        RelationsGrpcClientsManager.forInsecureClients("localhost1:8080");
        RelationsGrpcClientsManager.forSecureClients("localhost:8080");
        RelationsGrpcClientsManager.forSecureClients("localhost1:8080");
        RelationsGrpcClientsManager.forSecureClients("localhost1:8080"); // same as five

        var insecureField = RelationsGrpcClientsManager.class.getDeclaredField("insecureManagers");
        insecureField.setAccessible(true);
        var secureField = RelationsGrpcClientsManager.class.getDeclaredField("secureManagers");
        secureField.setAccessible(true);
        var insecureManagers = (HashMap<?,?>)insecureField.get(null);
        var secureManagers = (HashMap<?,?>)secureField.get(null);

        assertEquals(2, insecureManagers.size());
        assertEquals(2, secureManagers.size());
    }

    @Test
    public void testSameChannelUsedByClientsInternal() throws Exception {
        var manager = RelationsGrpcClientsManager.forInsecureClients("localhost:8080");
        var checkClient = manager.getCheckClient();
        var relationTuplesClient = manager.getRelationTuplesClient();
        var lookupClient = manager.getLookupClient();

        var checkAsyncStubField = CheckClient.class.getDeclaredField("asyncStub");
        checkAsyncStubField.setAccessible(true);
        var checkChannel = ((KesselCheckServiceGrpc.KesselCheckServiceStub)checkAsyncStubField.get(checkClient)).getChannel();
        var relationTuplesAsyncStubField = RelationTuplesClient.class.getDeclaredField("asyncStub");
        relationTuplesAsyncStubField.setAccessible(true);
        var relationTuplesChannel = ((KesselTupleServiceGrpc.KesselTupleServiceStub)relationTuplesAsyncStubField.get(relationTuplesClient)).getChannel();
        var lookupAsyncStubField = LookupClient.class.getDeclaredField("asyncStub");
        lookupAsyncStubField.setAccessible(true);
        var lookupChannel = ((KesselLookupServiceGrpc.KesselLookupServiceStub)lookupAsyncStubField.get(lookupClient)).getChannel();

        assertEquals(checkChannel, relationTuplesChannel);
        assertEquals(lookupChannel, relationTuplesChannel);
    }

    @Test
    public void testCreateAndShutdownPatternsInternal() throws Exception {
        var insecureField = RelationsGrpcClientsManager.class.getDeclaredField("insecureManagers");
        insecureField.setAccessible(true);
        var insecureManagersSize = ((HashMap<?,?>)insecureField.get(null)).size();

        assertEquals(0, insecureManagersSize);

        var manager = RelationsGrpcClientsManager.forInsecureClients("localhost:8080");
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(null)).size();
        assertEquals(1, insecureManagersSize);

        RelationsGrpcClientsManager.shutdownManager(manager);
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(null)).size();
        assertEquals(0, insecureManagersSize);

        /* Shouldn't throw exception if executed twice */
        RelationsGrpcClientsManager.shutdownManager(manager);
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(null)).size();
        assertEquals(0, insecureManagersSize);

        var manager2 = RelationsGrpcClientsManager.forInsecureClients("localhost:8080");
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(null)).size();
        assertEquals(1, insecureManagersSize);
        assertNotEquals(manager, manager2);

        RelationsGrpcClientsManager.forInsecureClients("localhost:8081");
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(null)).size();
        assertEquals(2, insecureManagersSize);

        RelationsGrpcClientsManager.shutdownAll();
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(null)).size();
        assertEquals(0, insecureManagersSize);
    }
}