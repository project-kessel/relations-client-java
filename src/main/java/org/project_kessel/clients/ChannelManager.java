package org.project_kessel.clients;

import io.grpc.*;
import org.project_kessel.clients.authn.AuthenticationConfig;
import org.project_kessel.clients.authn.CallCredentialsFactory;

import java.util.HashMap;
import java.util.Hashtable;

public final class ChannelManager {

    /* In the scenario where multiple clients are using this library, we need to support multiple channel managers,
    * so that clients can use separate channels. There may also be scenarios where reusing the same channel manager, and
    * hence channels, may be desirable. */
    private static final Hashtable<String,ChannelManager> channelManagers = new Hashtable<>();

    private final HashMap<String, ManagedChannel> insecureChannels = new HashMap<>();
    private final HashMap<String, ManagedChannel> secureChannels = new HashMap<>();

    public static ChannelManager getInstance(String channelManagerKey) {
        if(!channelManagers.containsKey(channelManagerKey)) {
            channelManagers.put(channelManagerKey, new ChannelManager());
        }

        return channelManagers.get(channelManagerKey);
    }

    public synchronized Channel forInsecureClients(String targetUrl) {
        if (!insecureChannels.containsKey(targetUrl)) {
            var channel = Grpc.newChannelBuilder(targetUrl, InsecureChannelCredentials.create()).build();
            insecureChannels.put(targetUrl, channel);
        }
        return insecureChannels.get(targetUrl);
    }

    public synchronized Channel forInsecureClients(String targetUrl, AuthenticationConfig authnConfig) throws RuntimeException {
        if (!insecureChannels.containsKey(targetUrl)) {
            try {
                var channel = Grpc.newChannelBuilder(targetUrl,
                        CompositeChannelCredentials.create(InsecureChannelCredentials.create(), CallCredentialsFactory.create(authnConfig))).build();
                insecureChannels.put(targetUrl, channel);
            } catch (CallCredentialsFactory.CallCredentialsCreationException e) {
                throw new RuntimeException(e);
            }
        }
        return insecureChannels.get(targetUrl);
    }

    public synchronized Channel forSecureClients(String targetUrl) {
        if (!secureChannels.containsKey(targetUrl)) {
            var tlsChannelCredentials = TlsChannelCredentials.create();
            var channel = Grpc.newChannelBuilder(targetUrl, tlsChannelCredentials).build();
            secureChannels.put(targetUrl, channel);
        }
        return secureChannels.get(targetUrl);
    }

    public synchronized Channel forSecureClients(String targetUrl, AuthenticationConfig authnConfig) {
        if (!secureChannels.containsKey(targetUrl)) {
            var tlsChannelCredentials = TlsChannelCredentials.create();
            try {
                var channel = Grpc.newChannelBuilder(targetUrl,
                        CompositeChannelCredentials.create(tlsChannelCredentials, CallCredentialsFactory.create(authnConfig))).build();
                secureChannels.put(targetUrl, channel);
            } catch (CallCredentialsFactory.CallCredentialsCreationException e) {
                throw new RuntimeException(e);
            }
        }
        return secureChannels.get(targetUrl);
    }

    public synchronized void shutdownAll() {
        for (var channel : insecureChannels.values()) {
            channel.shutdown();
        }
        insecureChannels.clear();
        for (var channel : secureChannels.values()) {
            channel.shutdown();
        }
        secureChannels.clear();
    }

    public synchronized void shutdownChannel(Channel channelToShutdown) {
        var iter = insecureChannels.entrySet().iterator();
        while (iter.hasNext()) {
            var entry = iter.next();
            if(entry.getValue() == channelToShutdown) {
                entry.getValue().shutdown();
                iter.remove();
                return;
            }
        }
        iter = secureChannels.entrySet().iterator();
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
