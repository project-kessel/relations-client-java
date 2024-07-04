package org.project_kessel.api.relations.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: kessel/relations/v1beta1/check.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselCheckServiceGrpc {

  private KesselCheckServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.relations.v1beta1.KesselCheckService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.CheckRequest,
      org.project_kessel.api.relations.v1beta1.CheckResponse> getCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Check",
      requestType = org.project_kessel.api.relations.v1beta1.CheckRequest.class,
      responseType = org.project_kessel.api.relations.v1beta1.CheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.CheckRequest,
      org.project_kessel.api.relations.v1beta1.CheckResponse> getCheckMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.CheckRequest, org.project_kessel.api.relations.v1beta1.CheckResponse> getCheckMethod;
    if ((getCheckMethod = KesselCheckServiceGrpc.getCheckMethod) == null) {
      synchronized (KesselCheckServiceGrpc.class) {
        if ((getCheckMethod = KesselCheckServiceGrpc.getCheckMethod) == null) {
          KesselCheckServiceGrpc.getCheckMethod = getCheckMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.relations.v1beta1.CheckRequest, org.project_kessel.api.relations.v1beta1.CheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Check"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.CheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.CheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselCheckServiceMethodDescriptorSupplier("Check"))
              .build();
        }
      }
    }
    return getCheckMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselCheckServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceStub>() {
        @java.lang.Override
        public KesselCheckServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselCheckServiceStub(channel, callOptions);
        }
      };
    return KesselCheckServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselCheckServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceBlockingStub>() {
        @java.lang.Override
        public KesselCheckServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselCheckServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselCheckServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselCheckServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceFutureStub>() {
        @java.lang.Override
        public KesselCheckServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselCheckServiceFutureStub(channel, callOptions);
        }
      };
    return KesselCheckServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Checks for the existence of a single Relationship 
     * (a Relation between a Resource and a Subject or Subject Set).
     * </pre>
     */
    default void check(org.project_kessel.api.relations.v1beta1.CheckRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.CheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselCheckService.
   */
  public static abstract class KesselCheckServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselCheckServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselCheckService.
   */
  public static final class KesselCheckServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselCheckServiceStub> {
    private KesselCheckServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselCheckServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselCheckServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Checks for the existence of a single Relationship 
     * (a Relation between a Resource and a Subject or Subject Set).
     * </pre>
     */
    public void check(org.project_kessel.api.relations.v1beta1.CheckRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.CheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselCheckService.
   */
  public static final class KesselCheckServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselCheckServiceBlockingStub> {
    private KesselCheckServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselCheckServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselCheckServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Checks for the existence of a single Relationship 
     * (a Relation between a Resource and a Subject or Subject Set).
     * </pre>
     */
    public org.project_kessel.api.relations.v1beta1.CheckResponse check(org.project_kessel.api.relations.v1beta1.CheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselCheckService.
   */
  public static final class KesselCheckServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselCheckServiceFutureStub> {
    private KesselCheckServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselCheckServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselCheckServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Checks for the existence of a single Relationship 
     * (a Relation between a Resource and a Subject or Subject Set).
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.relations.v1beta1.CheckResponse> check(
        org.project_kessel.api.relations.v1beta1.CheckRequest request) {
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
          serviceImpl.check((org.project_kessel.api.relations.v1beta1.CheckRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.CheckResponse>) responseObserver);
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
              org.project_kessel.api.relations.v1beta1.CheckRequest,
              org.project_kessel.api.relations.v1beta1.CheckResponse>(
                service, METHODID_CHECK)))
        .build();
  }

  private static abstract class KesselCheckServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselCheckServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.relations.v1beta1.Check.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselCheckService");
    }
  }

  private static final class KesselCheckServiceFileDescriptorSupplier
      extends KesselCheckServiceBaseDescriptorSupplier {
    KesselCheckServiceFileDescriptorSupplier() {}
  }

  private static final class KesselCheckServiceMethodDescriptorSupplier
      extends KesselCheckServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselCheckServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KesselCheckServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselCheckServiceFileDescriptorSupplier())
              .addMethod(getCheckMethod())
              .build();
        }
      }
    }
    return result;
  }
}
