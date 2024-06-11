package org.project_kessel.relations.client;

import io.grpc.*;

import java.util.HashMap;

public class RelationsGrpcClientsManager {
    private static final HashMap<String, RelationsGrpcClientsManager> insecureManagers = new HashMap<>();
    private static final HashMap<String, RelationsGrpcClientsManager> secureManagers = new HashMap<>();

    private final ManagedChannel channel;

    public static synchronized RelationsGrpcClientsManager forInsecureClients(String targetUrl) {
        if (!insecureManagers.containsKey(targetUrl)) {
            var manager = new RelationsGrpcClientsManager(targetUrl, InsecureChannelCredentials.create());
            insecureManagers.put(targetUrl, manager);
        }
        return insecureManagers.get(targetUrl);
    }

    public static synchronized RelationsGrpcClientsManager forSecureClients(String targetUrl) {
        if (!secureManagers.containsKey(targetUrl)) {
            var tlsChannelCredentials = TlsChannelCredentials.create();
            var manager = new RelationsGrpcClientsManager(targetUrl, tlsChannelCredentials);
            secureManagers.put(targetUrl, manager);
        }
        return secureManagers.get(targetUrl);
    }

    public static synchronized void shutdownAll() {
        for (var manager : insecureManagers.values()) {
            manager.closeClientChannel();
        }
        insecureManagers.clear();
        for (var manager : secureManagers.values()) {
            manager.closeClientChannel();
        }
        secureManagers.clear();
    }

    public static synchronized void shutdownManager(RelationsGrpcClientsManager managerToShutdown) {
        var iter = insecureManagers.entrySet().iterator();
        while (iter.hasNext()) {
            var entry = iter.next();
            if(entry.getValue().channel == managerToShutdown.channel) {
                entry.getValue().closeClientChannel();
                iter.remove();
                return;
            }
        }
        iter = secureManagers.entrySet().iterator();
        while (iter.hasNext()) {
            var entry = iter.next();
            if(entry.getValue().channel == managerToShutdown.channel) {
                entry.getValue().closeClientChannel();
                iter.remove();
                return;
            }
        }
    }

    /**
     *
     * Bearer token and other things can be added to ChannelCredentials. New static factory methods can be added.
     * Config management also required.
     * @param targetUrl
     * @param credentials
     */
    private RelationsGrpcClientsManager(String targetUrl, ChannelCredentials credentials) {
        this.channel = Grpc.newChannelBuilder(targetUrl, credentials).build();
    }

    private void closeClientChannel() {
        channel.shutdown();
    }

    public CheckClient getCheckClient() {
        return new CheckClient(channel);
    }

    public RelationTuplesClient getRelationTuplesClient() {
        return new RelationTuplesClient(channel);
    }

    public LookupClient getLookupClient() {
        return new LookupClient(channel);
    }

}
