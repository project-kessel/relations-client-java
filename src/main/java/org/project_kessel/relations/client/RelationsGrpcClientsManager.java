package org.project_kessel.relations.client;

import io.grpc.Channel;
import org.project_kessel.clients.ChannelManager;
import org.project_kessel.clients.KesselClientsManager;

public class RelationsGrpcClientsManager extends KesselClientsManager {
    private static final String CHANNEL_MANAGER_KEY = RelationsGrpcClientsManager.class.getName();

    private RelationsGrpcClientsManager(Channel channel) {
        super(channel);
    }

    public static RelationsGrpcClientsManager forInsecureClients(String targetUrl) {
        return new RelationsGrpcClientsManager(
                ChannelManager.getInstance(CHANNEL_MANAGER_KEY).forInsecureClients(targetUrl));
    }

    public static RelationsGrpcClientsManager forInsecureClients(String targetUrl,
                                                                 Config.AuthenticationConfig authnConfig)
            throws RuntimeException {
        return new RelationsGrpcClientsManager(ChannelManager.getInstance(CHANNEL_MANAGER_KEY)
                .forInsecureClients(targetUrl, AuthnConfigConverter.convert(authnConfig)));
    }

    public static RelationsGrpcClientsManager forSecureClients(String targetUrl) {
        return new RelationsGrpcClientsManager(
                ChannelManager.getInstance(CHANNEL_MANAGER_KEY).forSecureClients(targetUrl));
    }

    public static RelationsGrpcClientsManager forSecureClients(String targetUrl,
                                                               Config.AuthenticationConfig authnConfig) {
        return new RelationsGrpcClientsManager(ChannelManager.getInstance(CHANNEL_MANAGER_KEY)
                .forSecureClients(targetUrl, AuthnConfigConverter.convert(authnConfig)));
    }

    public static void shutdownAll() {
        ChannelManager.getInstance(CHANNEL_MANAGER_KEY).shutdownAll();
    }

    public static void shutdownManager(RelationsGrpcClientsManager managerToShutdown) {
        ChannelManager.getInstance(CHANNEL_MANAGER_KEY).shutdownChannel(managerToShutdown.channel);
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
