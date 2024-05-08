package api.check.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: rebac/v1/check.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CheckGrpc {

  private CheckGrpc() {}

  public static final java.lang.String SERVICE_NAME = "api.rebac.v1.Check";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<api.check.v1.CheckRequest,
      api.check.v1.CheckResponse> getCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Check",
      requestType = api.check.v1.CheckRequest.class,
      responseType = api.check.v1.CheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<api.check.v1.CheckRequest,
      api.check.v1.CheckResponse> getCheckMethod() {
    io.grpc.MethodDescriptor<api.check.v1.CheckRequest, api.check.v1.CheckResponse> getCheckMethod;
    if ((getCheckMethod = CheckGrpc.getCheckMethod) == null) {
      synchronized (CheckGrpc.class) {
        if ((getCheckMethod = CheckGrpc.getCheckMethod) == null) {
          CheckGrpc.getCheckMethod = getCheckMethod =
              io.grpc.MethodDescriptor.<api.check.v1.CheckRequest, api.check.v1.CheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Check"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.check.v1.CheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.check.v1.CheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CheckMethodDescriptorSupplier("Check"))
              .build();
        }
      }
    }
    return getCheckMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CheckStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckStub>() {
        @java.lang.Override
        public CheckStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckStub(channel, callOptions);
        }
      };
    return CheckStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CheckBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckBlockingStub>() {
        @java.lang.Override
        public CheckBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckBlockingStub(channel, callOptions);
        }
      };
    return CheckBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CheckFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckFutureStub>() {
        @java.lang.Override
        public CheckFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckFutureStub(channel, callOptions);
        }
      };
    return CheckFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void check(api.check.v1.CheckRequest request,
        io.grpc.stub.StreamObserver<api.check.v1.CheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Check.
   */
  public static abstract class CheckImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CheckGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Check.
   */
  public static final class CheckStub
      extends io.grpc.stub.AbstractAsyncStub<CheckStub> {
    private CheckStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckStub(channel, callOptions);
    }

    /**
     */
    public void check(api.check.v1.CheckRequest request,
        io.grpc.stub.StreamObserver<api.check.v1.CheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Check.
   */
  public static final class CheckBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CheckBlockingStub> {
    private CheckBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckBlockingStub(channel, callOptions);
    }

    /**
     */
    public api.check.v1.CheckResponse check(api.check.v1.CheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Check.
   */
  public static final class CheckFutureStub
      extends io.grpc.stub.AbstractFutureStub<CheckFutureStub> {
    private CheckFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<api.check.v1.CheckResponse> check(
        api.check.v1.CheckRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK = 0;

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
        case METHODID_CHECK:
          serviceImpl.check((api.check.v1.CheckRequest) request,
              (io.grpc.stub.StreamObserver<api.check.v1.CheckResponse>) responseObserver);
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
          getCheckMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              api.check.v1.CheckRequest,
              api.check.v1.CheckResponse>(
                service, METHODID_CHECK)))
        .build();
  }

  private static abstract class CheckBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CheckBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return api.check.v1.CheckOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Check");
    }
  }

  private static final class CheckFileDescriptorSupplier
      extends CheckBaseDescriptorSupplier {
    CheckFileDescriptorSupplier() {}
  }

  private static final class CheckMethodDescriptorSupplier
      extends CheckBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CheckMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CheckGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CheckFileDescriptorSupplier())
              .addMethod(getCheckMethod())
              .build();
        }
      }
    }
    return result;
  }
}
