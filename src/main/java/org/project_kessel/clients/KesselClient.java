package org.project_kessel.clients;

import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractBlockingStub;

public abstract class KesselClient<A extends AbstractAsyncStub<A>, B extends AbstractBlockingStub<B>> {
    protected A asyncStub;
    protected B blockingStub;

    protected KesselClient(A asyncStub, B blockingStub) {
        this.asyncStub = asyncStub;
        this.blockingStub = blockingStub;
    }


}
