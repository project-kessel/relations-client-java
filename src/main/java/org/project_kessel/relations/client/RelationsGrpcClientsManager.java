package org.project_kessel.relations.client;

import io.grpc.*;
import org.project_kessel.relations.client.authn.oidc.client.OIDCClientCredentialsCallCredentials;

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

    public static synchronized RelationsGrpcClientsManager forInsecureClients(String targetUrl, Config.AuthenticationConfig authnConfig) throws RuntimeException {
        if (!insecureManagers.containsKey(targetUrl)) {
            try {
                // For now, the only client authn scheme supported is OIDC client credentials
                var manager = new RelationsGrpcClientsManager(targetUrl,
                        InsecureChannelCredentials.create(),
                        new OIDCClientCredentialsCallCredentials(authnConfig));
                insecureManagers.put(targetUrl, manager);
            } catch (OIDCClientCredentialsCallCredentials.OIDCClientCredentialsCallCredentialsException e) {
                throw new RuntimeException(e);
            }
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

    public static synchronized RelationsGrpcClientsManager forSecureClients(String targetUrl, Config.AuthenticationConfig authnConfig) {
        if (!secureManagers.containsKey(targetUrl)) {
            var tlsChannelCredentials = TlsChannelCredentials.create();
            try {
                // For now, the only client authn scheme supported is OIDC client credentials
                var manager = new RelationsGrpcClientsManager(targetUrl,
                        tlsChannelCredentials,
                        new OIDCClientCredentialsCallCredentials(authnConfig));
                secureManagers.put(targetUrl, manager);
            } catch (OIDCClientCredentialsCallCredentials.OIDCClientCredentialsCallCredentialsException e) {
                throw new RuntimeException(e);
            }
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
     * Create a manager for a grpc channel with server credentials.
     * @param targetUrl
     * @param serverCredentials authenticates the server for TLS or are InsecureChannelCredentials
     */
    private RelationsGrpcClientsManager(String targetUrl, ChannelCredentials serverCredentials) {
        this.channel = Grpc.newChannelBuilder(targetUrl, serverCredentials).build();
    }

    /**
     * Create a manager for a grpc channel with server credentials and credentials for per-rpc client authentication.
     * @param targetUrl
     * @param serverCredentials authenticates the server for TLS or are InsecureChannelCredentials
     * @param authnCredentials authenticates the client on each rpc
     */
    private RelationsGrpcClientsManager(String targetUrl, ChannelCredentials serverCredentials, CallCredentials authnCredentials) {
        this.channel = Grpc.newChannelBuilder(targetUrl,
                CompositeChannelCredentials.create(serverCredentials, authnCredentials)).build();
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

    public HealthClient getHealthClient() {
        return new HealthClient(channel);
    }

}
