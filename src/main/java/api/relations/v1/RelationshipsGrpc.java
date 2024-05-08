package api.relations.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: rebac/v1/relationships.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RelationshipsGrpc {

  private RelationshipsGrpc() {}

  public static final java.lang.String SERVICE_NAME = "api.rebac.v1.Relationships";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<api.relations.v1.CreateRelationshipsRequest,
      api.relations.v1.CreateRelationshipsResponse> getCreateRelationshipsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateRelationships",
      requestType = api.relations.v1.CreateRelationshipsRequest.class,
      responseType = api.relations.v1.CreateRelationshipsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<api.relations.v1.CreateRelationshipsRequest,
      api.relations.v1.CreateRelationshipsResponse> getCreateRelationshipsMethod() {
    io.grpc.MethodDescriptor<api.relations.v1.CreateRelationshipsRequest, api.relations.v1.CreateRelationshipsResponse> getCreateRelationshipsMethod;
    if ((getCreateRelationshipsMethod = RelationshipsGrpc.getCreateRelationshipsMethod) == null) {
      synchronized (RelationshipsGrpc.class) {
        if ((getCreateRelationshipsMethod = RelationshipsGrpc.getCreateRelationshipsMethod) == null) {
          RelationshipsGrpc.getCreateRelationshipsMethod = getCreateRelationshipsMethod =
              io.grpc.MethodDescriptor.<api.relations.v1.CreateRelationshipsRequest, api.relations.v1.CreateRelationshipsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateRelationships"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.relations.v1.CreateRelationshipsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.relations.v1.CreateRelationshipsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RelationshipsMethodDescriptorSupplier("CreateRelationships"))
              .build();
        }
      }
    }
    return getCreateRelationshipsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<api.relations.v1.ReadRelationshipsRequest,
      api.relations.v1.ReadRelationshipsResponse> getReadRelationshipsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReadRelationships",
      requestType = api.relations.v1.ReadRelationshipsRequest.class,
      responseType = api.relations.v1.ReadRelationshipsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<api.relations.v1.ReadRelationshipsRequest,
      api.relations.v1.ReadRelationshipsResponse> getReadRelationshipsMethod() {
    io.grpc.MethodDescriptor<api.relations.v1.ReadRelationshipsRequest, api.relations.v1.ReadRelationshipsResponse> getReadRelationshipsMethod;
    if ((getReadRelationshipsMethod = RelationshipsGrpc.getReadRelationshipsMethod) == null) {
      synchronized (RelationshipsGrpc.class) {
        if ((getReadRelationshipsMethod = RelationshipsGrpc.getReadRelationshipsMethod) == null) {
          RelationshipsGrpc.getReadRelationshipsMethod = getReadRelationshipsMethod =
              io.grpc.MethodDescriptor.<api.relations.v1.ReadRelationshipsRequest, api.relations.v1.ReadRelationshipsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReadRelationships"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.relations.v1.ReadRelationshipsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.relations.v1.ReadRelationshipsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RelationshipsMethodDescriptorSupplier("ReadRelationships"))
              .build();
        }
      }
    }
    return getReadRelationshipsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<api.relations.v1.DeleteRelationshipsRequest,
      api.relations.v1.DeleteRelationshipsResponse> getDeleteRelationshipsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteRelationships",
      requestType = api.relations.v1.DeleteRelationshipsRequest.class,
      responseType = api.relations.v1.DeleteRelationshipsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<api.relations.v1.DeleteRelationshipsRequest,
      api.relations.v1.DeleteRelationshipsResponse> getDeleteRelationshipsMethod() {
    io.grpc.MethodDescriptor<api.relations.v1.DeleteRelationshipsRequest, api.relations.v1.DeleteRelationshipsResponse> getDeleteRelationshipsMethod;
    if ((getDeleteRelationshipsMethod = RelationshipsGrpc.getDeleteRelationshipsMethod) == null) {
      synchronized (RelationshipsGrpc.class) {
        if ((getDeleteRelationshipsMethod = RelationshipsGrpc.getDeleteRelationshipsMethod) == null) {
          RelationshipsGrpc.getDeleteRelationshipsMethod = getDeleteRelationshipsMethod =
              io.grpc.MethodDescriptor.<api.relations.v1.DeleteRelationshipsRequest, api.relations.v1.DeleteRelationshipsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteRelationships"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.relations.v1.DeleteRelationshipsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  api.relations.v1.DeleteRelationshipsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RelationshipsMethodDescriptorSupplier("DeleteRelationships"))
              .build();
        }
      }
    }
    return getDeleteRelationshipsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RelationshipsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RelationshipsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RelationshipsStub>() {
        @java.lang.Override
        public RelationshipsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RelationshipsStub(channel, callOptions);
        }
      };
    return RelationshipsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RelationshipsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RelationshipsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RelationshipsBlockingStub>() {
        @java.lang.Override
        public RelationshipsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RelationshipsBlockingStub(channel, callOptions);
        }
      };
    return RelationshipsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RelationshipsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RelationshipsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RelationshipsFutureStub>() {
        @java.lang.Override
        public RelationshipsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RelationshipsFutureStub(channel, callOptions);
        }
      };
    return RelationshipsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createRelationships(api.relations.v1.CreateRelationshipsRequest request,
        io.grpc.stub.StreamObserver<api.relations.v1.CreateRelationshipsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateRelationshipsMethod(), responseObserver);
    }

    /**
     */
    default void readRelationships(api.relations.v1.ReadRelationshipsRequest request,
        io.grpc.stub.StreamObserver<api.relations.v1.ReadRelationshipsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReadRelationshipsMethod(), responseObserver);
    }

    /**
     */
    default void deleteRelationships(api.relations.v1.DeleteRelationshipsRequest request,
        io.grpc.stub.StreamObserver<api.relations.v1.DeleteRelationshipsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteRelationshipsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Relationships.
   */
  public static abstract class RelationshipsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return RelationshipsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Relationships.
   */
  public static final class RelationshipsStub
      extends io.grpc.stub.AbstractAsyncStub<RelationshipsStub> {
    private RelationshipsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RelationshipsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RelationshipsStub(channel, callOptions);
    }

    /**
     */
    public void createRelationships(api.relations.v1.CreateRelationshipsRequest request,
        io.grpc.stub.StreamObserver<api.relations.v1.CreateRelationshipsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateRelationshipsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void readRelationships(api.relations.v1.ReadRelationshipsRequest request,
        io.grpc.stub.StreamObserver<api.relations.v1.ReadRelationshipsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReadRelationshipsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteRelationships(api.relations.v1.DeleteRelationshipsRequest request,
        io.grpc.stub.StreamObserver<api.relations.v1.DeleteRelationshipsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteRelationshipsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Relationships.
   */
  public static final class RelationshipsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RelationshipsBlockingStub> {
    private RelationshipsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RelationshipsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RelationshipsBlockingStub(channel, callOptions);
    }

    /**
     */
    public api.relations.v1.CreateRelationshipsResponse createRelationships(api.relations.v1.CreateRelationshipsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateRelationshipsMethod(), getCallOptions(), request);
    }

    /**
     */
    public api.relations.v1.ReadRelationshipsResponse readRelationships(api.relations.v1.ReadRelationshipsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReadRelationshipsMethod(), getCallOptions(), request);
    }

    /**
     */
    public api.relations.v1.DeleteRelationshipsResponse deleteRelationships(api.relations.v1.DeleteRelationshipsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteRelationshipsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Relationships.
   */
  public static final class RelationshipsFutureStub
      extends io.grpc.stub.AbstractFutureStub<RelationshipsFutureStub> {
    private RelationshipsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RelationshipsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RelationshipsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<api.relations.v1.CreateRelationshipsResponse> createRelationships(
        api.relations.v1.CreateRelationshipsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateRelationshipsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<api.relations.v1.ReadRelationshipsResponse> readRelationships(
        api.relations.v1.ReadRelationshipsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReadRelationshipsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<api.relations.v1.DeleteRelationshipsResponse> deleteRelationships(
        api.relations.v1.DeleteRelationshipsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteRelationshipsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_RELATIONSHIPS = 0;
  private static final int METHODID_READ_RELATIONSHIPS = 1;
  private static final int METHODID_DELETE_RELATIONSHIPS = 2;

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
        case METHODID_CREATE_RELATIONSHIPS:
          serviceImpl.createRelationships((api.relations.v1.CreateRelationshipsRequest) request,
              (io.grpc.stub.StreamObserver<api.relations.v1.CreateRelationshipsResponse>) responseObserver);
          break;
        case METHODID_READ_RELATIONSHIPS:
          serviceImpl.readRelationships((api.relations.v1.ReadRelationshipsRequest) request,
              (io.grpc.stub.StreamObserver<api.relations.v1.ReadRelationshipsResponse>) responseObserver);
          break;
        case METHODID_DELETE_RELATIONSHIPS:
          serviceImpl.deleteRelationships((api.relations.v1.DeleteRelationshipsRequest) request,
              (io.grpc.stub.StreamObserver<api.relations.v1.DeleteRelationshipsResponse>) responseObserver);
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
          getCreateRelationshipsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              api.relations.v1.CreateRelationshipsRequest,
              api.relations.v1.CreateRelationshipsResponse>(
                service, METHODID_CREATE_RELATIONSHIPS)))
        .addMethod(
          getReadRelationshipsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              api.relations.v1.ReadRelationshipsRequest,
              api.relations.v1.ReadRelationshipsResponse>(
                service, METHODID_READ_RELATIONSHIPS)))
        .addMethod(
          getDeleteRelationshipsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              api.relations.v1.DeleteRelationshipsRequest,
              api.relations.v1.DeleteRelationshipsResponse>(
                service, METHODID_DELETE_RELATIONSHIPS)))
        .build();
  }

  private static abstract class RelationshipsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RelationshipsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return api.relations.v1.RelationshipsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Relationships");
    }
  }

  private static final class RelationshipsFileDescriptorSupplier
      extends RelationshipsBaseDescriptorSupplier {
    RelationshipsFileDescriptorSupplier() {}
  }

  private static final class RelationshipsMethodDescriptorSupplier
      extends RelationshipsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    RelationshipsMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (RelationshipsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RelationshipsFileDescriptorSupplier())
              .addMethod(getCreateRelationshipsMethod())
              .addMethod(getReadRelationshipsMethod())
              .addMethod(getDeleteRelationshipsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
