package org.project_kessel.clients.fake;

import io.grpc.*;
import io.grpc.stub.StreamObserver;
import org.project_kessel.api.relations.v1beta1.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GrpcServerSpy extends Server {
    private final Server server;

    public GrpcServerSpy(int port, boolean tlsEnabled, ServerInterceptor interceptor, BindableService... services) {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        if (tlsEnabled) {
            URL certsUrl = Thread.currentThread().getContextClassLoader().getResource("certs/test.crt");
            URL keyUrl = Thread.currentThread().getContextClassLoader().getResource("certs/test.key");
            File certFile = new File(Objects.requireNonNull(certsUrl).getPath());
            File keyFile = new File(Objects.requireNonNull(keyUrl).getPath());
            serverBuilder.useTransportSecurity(certFile, keyFile);
        }
        if (interceptor != null) {
            serverBuilder.intercept(interceptor);
        }
        for (BindableService service : services) {
            serverBuilder.addService(service);
        }
        server = serverBuilder.build();
    }

    public static ServerCallDetails runAgainstTemporaryServerWithDummyServices(int port, Call grpcCallFunction) {
        return runAgainstTemporaryServerWithDummyServicesTlsSelect(port, false, grpcCallFunction);
    }

    public static ServerCallDetails runAgainstTemporaryTlsServerWithDummyServices(int port, Call grpcCallFunction) {
        return runAgainstTemporaryServerWithDummyServicesTlsSelect(port, true, grpcCallFunction);
    }

    private static ServerCallDetails runAgainstTemporaryServerWithDummyServicesTlsSelect(int port, boolean tlsEnabled, Call grpcCallFunction) {
        var dummyCheckService = new KesselCheckServiceGrpc.KesselCheckServiceImplBase() {
            @Override
            public void check(CheckRequest request, StreamObserver<CheckResponse> responseObserver) {
                responseObserver.onNext(CheckResponse.getDefaultInstance());
                responseObserver.onCompleted();
            }
        };
        var dummyTupleService = new KesselTupleServiceGrpc.KesselTupleServiceImplBase() {
            @Override
            public void readTuples(ReadTuplesRequest request, StreamObserver<ReadTuplesResponse> responseObserver) {
                responseObserver.onNext(ReadTuplesResponse.getDefaultInstance());
                responseObserver.onCompleted();
            }
        };
        var dummyLookupService = new KesselLookupServiceGrpc.KesselLookupServiceImplBase() {
            @Override
            public void lookupSubjects(LookupSubjectsRequest request, StreamObserver<LookupSubjectsResponse> responseObserver) {
                responseObserver.onNext(LookupSubjectsResponse.getDefaultInstance());
                responseObserver.onCompleted();
            }
        };

        return runAgainstTemporaryServerTlsSelect(port, tlsEnabled, grpcCallFunction, dummyCheckService, dummyTupleService, dummyLookupService);
    }

    public static ServerCallDetails runAgainstTemporaryServer(int port, Call grpcCallFunction, BindableService... services) {
        return runAgainstTemporaryServerTlsSelect(port, false, grpcCallFunction, services);
    }

    public static ServerCallDetails runAgainstTemporaryTlsServer(int port, Call grpcCallFunction, BindableService... services) {
        return runAgainstTemporaryServerTlsSelect(port, true, grpcCallFunction, services);
    }

    private static ServerCallDetails runAgainstTemporaryServerTlsSelect(int port, boolean tlsEnabled, Call grpcCallFunction, BindableService... services) {
        final ServerCallDetails serverCallDetails = new ServerCallDetails();

        var spyInterceptor = new ServerInterceptor() {
            @Override
            public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
                serverCallDetails.setCall(call);
                serverCallDetails.setMetadata(headers);
                return next.startCall(call, headers);
            }
        };

        FakeIdp fakeIdp = new FakeIdp(8090);
        var serverSpy = new GrpcServerSpy(port, tlsEnabled, spyInterceptor, services);

        try {
            fakeIdp.start();
            serverSpy.start();
            grpcCallFunction.call();
            serverSpy.shutdown();
            fakeIdp.stop();

            return serverCallDetails;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            serverSpy.shutdown();
            fakeIdp.stop();
        }
    }

    @Override
    public Server start() throws IOException {
        server.start();
        return this;
    }

    @Override
    public Server shutdown() {
        server.shutdown();
        return this;
    }

    @Override
    public Server shutdownNow() {
        server.shutdownNow();
        return this;
    }

    @Override
    public boolean isShutdown() {
        return server.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return server.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return server.awaitTermination(timeout, unit);
    }

    @Override
    public void awaitTermination() throws InterruptedException {
        server.awaitTermination();
    }

    public interface Call {
        void call();
    }

    public static class ServerCallDetails {
        private ServerCall<?,?> call;
        private Metadata metadata;

        public ServerCallDetails() {
        }

        public ServerCall<?,?> getCall() {
            return call;
        }

        public Metadata getMetadata() {
            return metadata;
        }

        public void setCall(ServerCall<?,?> call) {
            this.call = call;
        }

        public void setMetadata(Metadata metadata) {
            this.metadata = metadata;
        }
    }
}
