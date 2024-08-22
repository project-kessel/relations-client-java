package org.project_kessel.relations.client;

import io.grpc.Channel;
import org.project_kessel.clients.ChannelManager;
import org.project_kessel.clients.KesselClientsManager;

public final class RelationsGrpcClientsManager extends KesselClientsManager {
    private RelationsGrpcClientsManager(Channel channel) {
        super(channel);
    }

    public static RelationsGrpcClientsManager forInsecureClients(String targetUrl) {
        return new RelationsGrpcClientsManager(ChannelManager.instance().forInsecureClients(targetUrl));
    }

    public static RelationsGrpcClientsManager forInsecureClients(String targetUrl, Config.AuthenticationConfig authnConfig) throws RuntimeException {
        return new RelationsGrpcClientsManager(ChannelManager.instance().forInsecureClients(targetUrl, AuthnConfigConverter.convert(authnConfig)));
    }

    public static RelationsGrpcClientsManager forSecureClients(String targetUrl) {
        return new RelationsGrpcClientsManager(ChannelManager.instance().forSecureClients(targetUrl));
    }

    public static RelationsGrpcClientsManager forSecureClients(String targetUrl, Config.AuthenticationConfig authnConfig) {
        return new RelationsGrpcClientsManager(ChannelManager.instance().forSecureClients(targetUrl, AuthnConfigConverter.convert(authnConfig)));
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
