package client;

import io.grpc.*;

import java.util.HashMap;

public class RelationsGrpcClientsManager {
    private static final HashMap<String, RelationsGrpcClientsManager> insecureManagers = new HashMap<>();
    private static final HashMap<String, RelationsGrpcClientsManager> secureManagers = new HashMap<>();

    private final ManagedChannel channel;

    public static RelationsGrpcClientsManager forInsecureClients(String targetUrl) {
        if (!insecureManagers.containsKey(targetUrl)) {
            var manager = new RelationsGrpcClientsManager(targetUrl, InsecureChannelCredentials.create());
            insecureManagers.put(targetUrl, manager);
        }
        return insecureManagers.get(targetUrl);
    }

    public static RelationsGrpcClientsManager forSecureClients(String targetUrl) {
        if (!secureManagers.containsKey(targetUrl)) {
            var tlsChannelCredentials = TlsChannelCredentials.create();
            var manager = new RelationsGrpcClientsManager(targetUrl, tlsChannelCredentials);
            secureManagers.put(targetUrl, manager);
        }
        return secureManagers.get(targetUrl);
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

    /**
     * TBD: lifecycle of RelationsGrpcClientsManager and channel needs a little thought.
     */



    public CheckClient getCheckClient() {
        return new CheckClient(channel);
    }

    public RelationTuplesClient getRelationTuplesClient() {
        return new RelationTuplesClient(channel);
    }

}
