package org.project_kessel.clients;

import io.grpc.Channel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class ChannelManagerTest {
    ChannelManager defaultChannelManager = ChannelManager.getInstance("defaultChannelManager");

    @AfterEach
    void testTeardown() {
        /* Make sure all client managers shutdown/removed after each test */
        defaultChannelManager.shutdownAll();
    }

    @Test
    void shouldSelectSameManager() {
        ChannelManager channelManager = ChannelManager.getInstance("myChannelManangerKey");
        ChannelManager channelManager2 = ChannelManager.getInstance("myChannelManangerKey");

        assertEquals(channelManager, channelManager2);
    }

    @Test
    void shouldNotSelectSameManager() {
        ChannelManager channelManager = ChannelManager.getInstance("myChannelManangerKey");
        ChannelManager channelManager2 = ChannelManager.getInstance("anotherChannelManagerKey");

        assertNotEquals(channelManager, channelManager2);
    }

    @Test
    void testManagerChannelReusePatterns() {
        var one = defaultChannelManager.forInsecureClients("localhost:8080");
        var two = defaultChannelManager.forInsecureClients("localhost:8080"); // same as one
        var three = defaultChannelManager.forInsecureClients("localhost1:8080");
        var four = defaultChannelManager.forSecureClients("localhost:8080");
        var five = defaultChannelManager.forSecureClients("localhost1:8080");
        var six = defaultChannelManager.forSecureClients("localhost1:8080"); // same as five

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
    void testThreadingChaos() {
        /* Basic testing to ensure that we don't get ConcurrentModificationExceptions, or any other exceptions, when
         * creating and destroying managers on different threads. */

        try {
            Hashtable<String, Channel> channels = new Hashtable<>();

            int numberOfThreads = 100;
            ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
            CountDownLatch latch1 = new CountDownLatch(numberOfThreads / 3);
            CountDownLatch latch2 = new CountDownLatch(numberOfThreads * 2 / 3 - numberOfThreads / 3);
            CountDownLatch latch3 = new CountDownLatch(numberOfThreads - numberOfThreads * 2 / 3);

            /* A: Use 1/3 of threads to request/create channels at the same time. */
            for (int i = 0; i < numberOfThreads / 3; i++) {
                final int j = i;
                service.submit(() -> {
                    Channel manager;
                    if(j % 2 == 0) {
                        manager = defaultChannelManager.forInsecureClients("localhost" + j);
                    } else {
                        manager = defaultChannelManager.forSecureClients("localhost" + j);
                    }
                    channels.put("localhost" + j, manager);

                    latch1.countDown();
                });
            }

            latch1.await();
            /* B and C, below, trigger at the same time once A is done. */

            /* B: Use 1/3 of threads to shut down the above created channels. */
            for (int i = numberOfThreads / 3; i < numberOfThreads * 2 / 3; i++) {
                final int j = i - numberOfThreads / 3;
                service.submit(() -> {
                    defaultChannelManager.shutdownChannel(channels.get("localhost" + j));
                    latch2.countDown();
                });
            }

            /* C: Use 1/3 of the threads to recreate/retrieve the same channels at the same time as B. */
            for (int i = numberOfThreads * 2 / 3; i < numberOfThreads; i++) {
                final int j = i - numberOfThreads * 2 / 3;
                service.submit(() -> {
                    Channel manager;
                    if(j % 2 == 0) {
                        manager = defaultChannelManager.forInsecureClients("localhost" + j);
                    } else {
                        manager = defaultChannelManager.forSecureClients("localhost" + j);
                    }
                    channels.put("localhost" + j, manager);

                    latch3.countDown();
                });
            }
            latch2.await();
            latch3.await();
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    /*
     Tests relying on reflection. Brittle and could be removed in future.
     */

    @Test
    void testManagerReuseInternal() throws Exception {
        defaultChannelManager.forInsecureClients("localhost:8080");
        defaultChannelManager.forInsecureClients("localhost:8080"); // same as one
        defaultChannelManager.forInsecureClients("localhost1:8080");
        defaultChannelManager.forSecureClients("localhost:8080");
        defaultChannelManager.forSecureClients("localhost1:8080");
        defaultChannelManager.forSecureClients("localhost1:8080"); // same as five

        var insecureField = ChannelManager.class.getDeclaredField("insecureManagers");
        insecureField.setAccessible(true);
        var secureField = ChannelManager.class.getDeclaredField("secureManagers");
        secureField.setAccessible(true);
        var insecureManagers = (HashMap<?,?>)insecureField.get(defaultChannelManager);
        var secureManagers = (HashMap<?,?>)secureField.get(defaultChannelManager);

        assertEquals(2, insecureManagers.size());
        assertEquals(2, secureManagers.size());
    }

    @Test
    void testCreateAndShutdownPatternsInternal() throws Exception {
        var insecureField = ChannelManager.class.getDeclaredField("insecureManagers");
        insecureField.setAccessible(true);
        var insecureManagersSize = ((HashMap<?,?>)insecureField.get(defaultChannelManager)).size();

        assertEquals(0, insecureManagersSize);

        var channel = defaultChannelManager.forInsecureClients("localhost:8080");
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(defaultChannelManager)).size();
        assertEquals(1, insecureManagersSize);

        defaultChannelManager.shutdownChannel(channel);
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(defaultChannelManager)).size();
        assertEquals(0, insecureManagersSize);

        /* Shouldn't throw exception if executed twice */
        defaultChannelManager.shutdownChannel(channel);
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(defaultChannelManager)).size();
        assertEquals(0, insecureManagersSize);

        var manager2 = defaultChannelManager.forInsecureClients("localhost:8080");
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(defaultChannelManager)).size();
        assertEquals(1, insecureManagersSize);
        assertNotEquals(channel, manager2);

        defaultChannelManager.forInsecureClients("localhost:8081");
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(defaultChannelManager)).size();
        assertEquals(2, insecureManagersSize);

        defaultChannelManager.shutdownAll();
        insecureManagersSize = ((HashMap<?,?>)insecureField.get(defaultChannelManager)).size();
        assertEquals(0, insecureManagersSize);
    }
}
