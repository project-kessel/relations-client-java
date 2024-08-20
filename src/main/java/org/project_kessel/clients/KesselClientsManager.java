package org.project_kessel.clients;

import io.grpc.Channel;

public abstract class KesselClientsManager {
    protected final Channel channel;

    protected KesselClientsManager(Channel channel) {
        this.channel = channel;
    }
}
