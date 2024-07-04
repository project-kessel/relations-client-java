package org.project_kessel.api.relations.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: kessel/health/v1/health.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselHealthServiceGrpc {

  private KesselHealthServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.health.v1.KesselHealthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1.GetLivezRequest,
      org.project_kessel.api.relations.v1.GetLivezResponse> getGetLivezMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLivez",
      requestType = org.project_kessel.api.relations.v1.GetLivezRequest.class,
      responseType = org.project_kessel.api.relations.v1.GetLivezResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1.GetLivezRequest,
      org.project_kessel.api.relations.v1.GetLivezResponse> getGetLivezMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1.GetLivezRequest, org.project_kessel.api.relations.v1.GetLivezResponse> getGetLivezMethod;
    if ((getGetLivezMethod = KesselHealthServiceGrpc.getGetLivezMethod) == null) {
      synchronized (KesselHealthServiceGrpc.class) {
        if ((getGetLivezMethod = KesselHealthServiceGrpc.getGetLivezMethod) == null) {
          KesselHealthServiceGrpc.getGetLivezMethod = getGetLivezMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.relations.v1.GetLivezRequest, org.project_kessel.api.relations.v1.GetLivezResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLivez"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1.GetLivezRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1.GetLivezResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselHealthServiceMethodDescriptorSupplier("GetLivez"))
              .build();
        }
      }
    }
    return getGetLivezMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1.GetReadyzRequest,
      org.project_kessel.api.relations.v1.GetReadyzResponse> getGetReadyzMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetReadyz",
      requestType = org.project_kessel.api.relations.v1.GetReadyzRequest.class,
      responseType = org.project_kessel.api.relations.v1.GetReadyzResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1.GetReadyzRequest,
      org.project_kessel.api.relations.v1.GetReadyzResponse> getGetReadyzMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1.GetReadyzRequest, org.project_kessel.api.relations.v1.GetReadyzResponse> getGetReadyzMethod;
    if ((getGetReadyzMethod = KesselHealthServiceGrpc.getGetReadyzMethod) == null) {
      synchronized (KesselHealthServiceGrpc.class) {
        if ((getGetReadyzMethod = KesselHealthServiceGrpc.getGetReadyzMethod) == null) {
          KesselHealthServiceGrpc.getGetReadyzMethod = getGetReadyzMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.relations.v1.GetReadyzRequest, org.project_kessel.api.relations.v1.GetReadyzResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetReadyz"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1.GetReadyzRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1.GetReadyzResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselHealthServiceMethodDescriptorSupplier("GetReadyz"))
              .build();
        }
      }
    }
    return getGetReadyzMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselHealthServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselHealthServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselHealthServiceStub>() {
        @java.lang.Override
        public KesselHealthServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselHealthServiceStub(channel, callOptions);
        }
      };
    return KesselHealthServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselHealthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselHealthServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselHealthServiceBlockingStub>() {
        @java.lang.Override
        public KesselHealthServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselHealthServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselHealthServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselHealthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselHealthServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselHealthServiceFutureStub>() {
        @java.lang.Override
        public KesselHealthServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselHealthServiceFutureStub(channel, callOptions);
        }
      };
    return KesselHealthServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getLivez(org.project_kessel.api.relations.v1.GetLivezRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1.GetLivezResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetLivezMethod(), responseObserver);
    }

    /**
     */
    default void getReadyz(org.project_kessel.api.relations.v1.GetReadyzRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1.GetReadyzResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetReadyzMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselHealthService.
   */
  public static abstract class KesselHealthServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselHealthServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselHealthService.
   */
  public static final class KesselHealthServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselHealthServiceStub> {
    private KesselHealthServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselHealthServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselHealthServiceStub(channel, callOptions);
    }

    /**
     */
    public void getLivez(org.project_kessel.api.relations.v1.GetLivezRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1.GetLivezResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetLivezMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getReadyz(org.project_kessel.api.relations.v1.GetReadyzRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1.GetReadyzResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetReadyzMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselHealthService.
   */
  public static final class KesselHealthServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselHealthServiceBlockingStub> {
    private KesselHealthServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselHealthServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselHealthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.relations.v1.GetLivezResponse getLivez(org.project_kessel.api.relations.v1.GetLivezRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetLivezMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.relations.v1.GetReadyzResponse getReadyz(org.project_kessel.api.relations.v1.GetReadyzRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetReadyzMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselHealthService.
   */
  public static final class KesselHealthServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselHealthServiceFutureStub> {
    private KesselHealthServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselHealthServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselHealthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.relations.v1.GetLivezResponse> getLivez(
        org.project_kessel.api.relations.v1.GetLivezRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetLivezMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.relations.v1.GetReadyzResponse> getReadyz(
        org.project_kessel.api.relations.v1.GetReadyzRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetReadyzMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_LIVEZ = 0;
  private static final int METHODID_GET_READYZ = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_LIVEZ:
          serviceImpl.getLivez((org.project_kessel.api.relations.v1.GetLivezRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1.GetLivezResponse>) responseObserver);
          break;
        case METHODID_GET_READYZ:
          serviceImpl.getReadyz((org.project_kessel.api.relations.v1.GetReadyzRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1.GetReadyzResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetLivezMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.relations.v1.GetLivezRequest,
              org.project_kessel.api.relations.v1.GetLivezResponse>(
                service, METHODID_GET_LIVEZ)))
        .addMethod(
          getGetReadyzMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.relations.v1.GetReadyzRequest,
              org.project_kessel.api.relations.v1.GetReadyzResponse>(
                service, METHODID_GET_READYZ)))
        .build();
  }

  private static abstract class KesselHealthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselHealthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.relations.v1.Health.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselHealthService");
    }
  }

  private static final class KesselHealthServiceFileDescriptorSupplier
      extends KesselHealthServiceBaseDescriptorSupplier {
    KesselHealthServiceFileDescriptorSupplier() {}
  }

  private static final class KesselHealthServiceMethodDescriptorSupplier
      extends KesselHealthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselHealthServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (KesselHealthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselHealthServiceFileDescriptorSupplier())
              .addMethod(getGetLivezMethod())
              .addMethod(getGetReadyzMethod())
              .build();
        }
      }
    }
    return result;
  }
}
