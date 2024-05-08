package api.health.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: health/v1/health.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HealthGrpc {

  private HealthGrpc() {}

  public static final java.lang.String SERVICE_NAME = "api.health.v1.Health";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<api.health.v1.GetLivezRequest,
      api.health.v1.GetLivezReply> getGetLivezMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLivez",
      requestType = api.health.v1.GetLivezRequest.class,
      responseType = api.health.v1.GetLivezReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<api.health.v1.GetLivezRequest,
      api.health.v1.GetLivezReply> getGetLivezMethod() {
    io.grpc.MethodDescriptor<api.health.v1.GetLivezRequest, api.health.v1.GetLivezReply> getGetLivezMethod;
    if ((getGetLivezMethod = HealthGrpc.getGetLivezMethod) == null) {
      synchronized (HealthGrpc.class) {
        if ((getGetLivezMethod = HealthGrpc.getGetLivezMethod) == null) {
          HealthGrpc.getGetLivezMethod = getGetLivezMethod =
              io.grpc.MethodDescriptor.<api.health.v1.GetLivezRequest, api.health.v1.GetLivezReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLivez"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.health.v1.GetLivezRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.health.v1.GetLivezReply.getDefaultInstance()))
              .setSchemaDescriptor(new HealthMethodDescriptorSupplier("GetLivez"))
              .build();
        }
      }
    }
    return getGetLivezMethod;
  }

  private static volatile io.grpc.MethodDescriptor<api.health.v1.GetReadyzRequest,
      api.health.v1.GetReadyzReply> getGetReadyzMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetReadyz",
      requestType = api.health.v1.GetReadyzRequest.class,
      responseType = api.health.v1.GetReadyzReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<api.health.v1.GetReadyzRequest,
      api.health.v1.GetReadyzReply> getGetReadyzMethod() {
    io.grpc.MethodDescriptor<api.health.v1.GetReadyzRequest, api.health.v1.GetReadyzReply> getGetReadyzMethod;
    if ((getGetReadyzMethod = HealthGrpc.getGetReadyzMethod) == null) {
      synchronized (HealthGrpc.class) {
        if ((getGetReadyzMethod = HealthGrpc.getGetReadyzMethod) == null) {
          HealthGrpc.getGetReadyzMethod = getGetReadyzMethod =
              io.grpc.MethodDescriptor.<api.health.v1.GetReadyzRequest, api.health.v1.GetReadyzReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetReadyz"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.health.v1.GetReadyzRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.health.v1.GetReadyzReply.getDefaultInstance()))
              .setSchemaDescriptor(new HealthMethodDescriptorSupplier("GetReadyz"))
              .build();
        }
      }
    }
    return getGetReadyzMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HealthStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HealthStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HealthStub>() {
        @java.lang.Override
        public HealthStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HealthStub(channel, callOptions);
        }
      };
    return HealthStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HealthBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HealthBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HealthBlockingStub>() {
        @java.lang.Override
        public HealthBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HealthBlockingStub(channel, callOptions);
        }
      };
    return HealthBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HealthFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HealthFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HealthFutureStub>() {
        @java.lang.Override
        public HealthFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HealthFutureStub(channel, callOptions);
        }
      };
    return HealthFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getLivez(api.health.v1.GetLivezRequest request,
        io.grpc.stub.StreamObserver<api.health.v1.GetLivezReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetLivezMethod(), responseObserver);
    }

    /**
     */
    default void getReadyz(api.health.v1.GetReadyzRequest request,
        io.grpc.stub.StreamObserver<api.health.v1.GetReadyzReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetReadyzMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Health.
   */
  public static abstract class HealthImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return HealthGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Health.
   */
  public static final class HealthStub
      extends io.grpc.stub.AbstractAsyncStub<HealthStub> {
    private HealthStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HealthStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HealthStub(channel, callOptions);
    }

    /**
     */
    public void getLivez(api.health.v1.GetLivezRequest request,
        io.grpc.stub.StreamObserver<api.health.v1.GetLivezReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetLivezMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getReadyz(api.health.v1.GetReadyzRequest request,
        io.grpc.stub.StreamObserver<api.health.v1.GetReadyzReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetReadyzMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Health.
   */
  public static final class HealthBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<HealthBlockingStub> {
    private HealthBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HealthBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HealthBlockingStub(channel, callOptions);
    }

    /**
     */
    public api.health.v1.GetLivezReply getLivez(api.health.v1.GetLivezRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetLivezMethod(), getCallOptions(), request);
    }

    /**
     */
    public api.health.v1.GetReadyzReply getReadyz(api.health.v1.GetReadyzRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetReadyzMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Health.
   */
  public static final class HealthFutureStub
      extends io.grpc.stub.AbstractFutureStub<HealthFutureStub> {
    private HealthFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HealthFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HealthFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<api.health.v1.GetLivezReply> getLivez(
        api.health.v1.GetLivezRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetLivezMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<api.health.v1.GetReadyzReply> getReadyz(
        api.health.v1.GetReadyzRequest request) {
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
          serviceImpl.getLivez((api.health.v1.GetLivezRequest) request,
              (io.grpc.stub.StreamObserver<api.health.v1.GetLivezReply>) responseObserver);
          break;
        case METHODID_GET_READYZ:
          serviceImpl.getReadyz((api.health.v1.GetReadyzRequest) request,
              (io.grpc.stub.StreamObserver<api.health.v1.GetReadyzReply>) responseObserver);
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
              api.health.v1.GetLivezRequest,
              api.health.v1.GetLivezReply>(
                service, METHODID_GET_LIVEZ)))
        .addMethod(
          getGetReadyzMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              api.health.v1.GetReadyzRequest,
              api.health.v1.GetReadyzReply>(
                service, METHODID_GET_READYZ)))
        .build();
  }

  private static abstract class HealthBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HealthBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return api.health.v1.HealthOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Health");
    }
  }

  private static final class HealthFileDescriptorSupplier
      extends HealthBaseDescriptorSupplier {
    HealthFileDescriptorSupplier() {}
  }

  private static final class HealthMethodDescriptorSupplier
      extends HealthBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    HealthMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (HealthGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HealthFileDescriptorSupplier())
              .addMethod(getGetLivezMethod())
              .addMethod(getGetReadyzMethod())
              .build();
        }
      }
    }
    return result;
  }
}
