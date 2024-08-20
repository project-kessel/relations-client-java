package org.project_kessel.clients;

import io.grpc.*;
import org.project_kessel.clients.authn.CallCredentialsFactory;

import java.util.HashMap;

public final class ChannelManager {
    private static ChannelManager channelManager;

    private final HashMap<String, ManagedChannel> insecureManagers = new HashMap<>();
    private final HashMap<String, ManagedChannel> secureManagers = new HashMap<>();

    public static ChannelManager instance() {
        if(channelManager == null) {
             channelManager = new ChannelManager();
        }

        return channelManager;
    }

    public synchronized Channel forInsecureClients(String targetUrl) {
        if (!insecureManagers.containsKey(targetUrl)) {
            var channel = Grpc.newChannelBuilder(targetUrl, InsecureChannelCredentials.create()).build();
            insecureManagers.put(targetUrl, channel);
        }
        return insecureManagers.get(targetUrl);
    }

    public synchronized Channel forInsecureClients(String targetUrl, Config.AuthenticationConfig authnConfig) throws RuntimeException {
        if (!insecureManagers.containsKey(targetUrl)) {
            try {
                var channel = Grpc.newChannelBuilder(targetUrl,
                        CompositeChannelCredentials.create(InsecureChannelCredentials.create(), CallCredentialsFactory.create(authnConfig))).build();
                insecureManagers.put(targetUrl, channel);
            } catch (CallCredentialsFactory.CallCredentialsCreationException e) {
                throw new RuntimeException(e);
            }
        }
        return insecureManagers.get(targetUrl);
    }

    public synchronized Channel forSecureClients(String targetUrl) {
        if (!secureManagers.containsKey(targetUrl)) {
            var tlsChannelCredentials = TlsChannelCredentials.create();
            var channel = Grpc.newChannelBuilder(targetUrl, tlsChannelCredentials).build();
            secureManagers.put(targetUrl, channel);
        }
        return secureManagers.get(targetUrl);
    }

    public synchronized Channel forSecureClients(String targetUrl, Config.AuthenticationConfig authnConfig) {
        if (!secureManagers.containsKey(targetUrl)) {
            var tlsChannelCredentials = TlsChannelCredentials.create();
            try {
                var channel = Grpc.newChannelBuilder(targetUrl,
                        CompositeChannelCredentials.create(tlsChannelCredentials, CallCredentialsFactory.create(authnConfig))).build();
                secureManagers.put(targetUrl, channel);
            } catch (CallCredentialsFactory.CallCredentialsCreationException e) {
                throw new RuntimeException(e);
            }
        }
        return secureManagers.get(targetUrl);
    }

    public synchronized void shutdownAll() {
        for (var channel : insecureManagers.values()) {
            channel.shutdown();
        }
        insecureManagers.clear();
        for (var channel : secureManagers.values()) {
            channel.shutdown();
        }
        secureManagers.clear();
    }

    public synchronized void shutdownChannel(Channel channelToShutdown) {
        var iter = insecureManagers.entrySet().iterator();
        while (iter.hasNext()) {
            var entry = iter.next();
            if(entry.getValue() == channelToShutdown) {
                entry.getValue().shutdown();
                iter.remove();
                return;
            }
        }
        iter = secureManagers.entrySet().iterator();
        while (iter.hasNext()) {
            var entry = iter.next();
            if(entry.getValue() == channelToShutdown) {
                entry.getValue().shutdown();
                iter.remove();
                return;
            }
        }
    }
}
