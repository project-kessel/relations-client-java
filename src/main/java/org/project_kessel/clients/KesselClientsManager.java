package org.project_kessel.clients;

import io.grpc.Channel;

public abstract class KesselClientsManager {
    protected final Channel channel;

    protected KesselClientsManager(Channel channel) {
        this.channel = channel;
    }

    public static void shutdownAll() {
        ChannelManager.instance().shutdownAll();
    }

    public static void shutdownManager(KesselClientsManager managerToShutdown) {
        ChannelManager.instance().shutdownChannel(managerToShutdown.channel);
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
