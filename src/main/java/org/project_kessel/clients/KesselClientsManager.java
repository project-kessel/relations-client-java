package org.project_kessel.clients;

import io.grpc.Channel;

public abstract class KesselClientsManager {
    protected final Channel channel;

    protected KesselClientsManager(Channel channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KesselClientsManager that = (KesselClientsManager) o;
        return channel.equals(that.channel);
    }

    @Override
    public int hashCode() {
        return channel.hashCode();
    }
}
