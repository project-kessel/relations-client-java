package org.project_kessel.api.relations.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: kessel/relations/v1beta1/lookup.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselLookupServiceGrpc {

  private KesselLookupServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.relations.v1beta1.KesselLookupService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest,
      org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse> getLookupSubjectsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LookupSubjects",
      requestType = org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest.class,
      responseType = org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest,
      org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse> getLookupSubjectsMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest, org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse> getLookupSubjectsMethod;
    if ((getLookupSubjectsMethod = KesselLookupServiceGrpc.getLookupSubjectsMethod) == null) {
      synchronized (KesselLookupServiceGrpc.class) {
        if ((getLookupSubjectsMethod = KesselLookupServiceGrpc.getLookupSubjectsMethod) == null) {
          KesselLookupServiceGrpc.getLookupSubjectsMethod = getLookupSubjectsMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest, org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LookupSubjects"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselLookupServiceMethodDescriptorSupplier("LookupSubjects"))
              .build();
        }
      }
    }
    return getLookupSubjectsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.LookupResourcesRequest,
      org.project_kessel.api.relations.v1beta1.LookupResourcesResponse> getLookupResourcesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LookupResources",
      requestType = org.project_kessel.api.relations.v1beta1.LookupResourcesRequest.class,
      responseType = org.project_kessel.api.relations.v1beta1.LookupResourcesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.LookupResourcesRequest,
      org.project_kessel.api.relations.v1beta1.LookupResourcesResponse> getLookupResourcesMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.LookupResourcesRequest, org.project_kessel.api.relations.v1beta1.LookupResourcesResponse> getLookupResourcesMethod;
    if ((getLookupResourcesMethod = KesselLookupServiceGrpc.getLookupResourcesMethod) == null) {
      synchronized (KesselLookupServiceGrpc.class) {
        if ((getLookupResourcesMethod = KesselLookupServiceGrpc.getLookupResourcesMethod) == null) {
          KesselLookupServiceGrpc.getLookupResourcesMethod = getLookupResourcesMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.relations.v1beta1.LookupResourcesRequest, org.project_kessel.api.relations.v1beta1.LookupResourcesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LookupResources"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.LookupResourcesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.LookupResourcesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselLookupServiceMethodDescriptorSupplier("LookupResources"))
              .build();
        }
      }
    }
    return getLookupResourcesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselLookupServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselLookupServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselLookupServiceStub>() {
        @java.lang.Override
        public KesselLookupServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselLookupServiceStub(channel, callOptions);
        }
      };
    return KesselLookupServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselLookupServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselLookupServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselLookupServiceBlockingStub>() {
        @java.lang.Override
        public KesselLookupServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselLookupServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselLookupServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselLookupServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselLookupServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselLookupServiceFutureStub>() {
        @java.lang.Override
        public KesselLookupServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselLookupServiceFutureStub(channel, callOptions);
        }
      };
    return KesselLookupServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void lookupSubjects(org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLookupSubjectsMethod(), responseObserver);
    }

    /**
     */
    default void lookupResources(org.project_kessel.api.relations.v1beta1.LookupResourcesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.LookupResourcesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLookupResourcesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselLookupService.
   */
  public static abstract class KesselLookupServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselLookupServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselLookupService.
   */
  public static final class KesselLookupServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselLookupServiceStub> {
    private KesselLookupServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselLookupServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselLookupServiceStub(channel, callOptions);
    }

    /**
     */
    public void lookupSubjects(org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getLookupSubjectsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lookupResources(org.project_kessel.api.relations.v1beta1.LookupResourcesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.LookupResourcesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getLookupResourcesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselLookupService.
   */
  public static final class KesselLookupServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselLookupServiceBlockingStub> {
    private KesselLookupServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselLookupServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselLookupServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse> lookupSubjects(
        org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getLookupSubjectsMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<org.project_kessel.api.relations.v1beta1.LookupResourcesResponse> lookupResources(
        org.project_kessel.api.relations.v1beta1.LookupResourcesRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getLookupResourcesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselLookupService.
   */
  public static final class KesselLookupServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselLookupServiceFutureStub> {
    private KesselLookupServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselLookupServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselLookupServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_LOOKUP_SUBJECTS = 0;
  private static final int METHODID_LOOKUP_RESOURCES = 1;

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
        case METHODID_LOOKUP_SUBJECTS:
          serviceImpl.lookupSubjects((org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse>) responseObserver);
          break;
        case METHODID_LOOKUP_RESOURCES:
          serviceImpl.lookupResources((org.project_kessel.api.relations.v1beta1.LookupResourcesRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.LookupResourcesResponse>) responseObserver);
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
          getLookupSubjectsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest,
              org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse>(
                service, METHODID_LOOKUP_SUBJECTS)))
        .addMethod(
          getLookupResourcesMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.project_kessel.api.relations.v1beta1.LookupResourcesRequest,
              org.project_kessel.api.relations.v1beta1.LookupResourcesResponse>(
                service, METHODID_LOOKUP_RESOURCES)))
        .build();
  }

  private static abstract class KesselLookupServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselLookupServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.relations.v1beta1.Lookup.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselLookupService");
    }
  }

  private static final class KesselLookupServiceFileDescriptorSupplier
      extends KesselLookupServiceBaseDescriptorSupplier {
    KesselLookupServiceFileDescriptorSupplier() {}
  }

  private static final class KesselLookupServiceMethodDescriptorSupplier
      extends KesselLookupServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselLookupServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KesselLookupServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselLookupServiceFileDescriptorSupplier())
              .addMethod(getLookupSubjectsMethod())
              .addMethod(getLookupResourcesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
