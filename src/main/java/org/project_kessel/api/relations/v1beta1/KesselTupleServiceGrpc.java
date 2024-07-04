package org.project_kessel.api.relations.v1beta1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * KesselTupleServices manages the persisted _Tuples_ stored in the system..
 * 
 * A Tuple is an explicitly stated, persistent relation 
 * between a Resource and a Subject or Subject Set. 
 * It has the same _shape_ as a Relationship but is not the same thing as a Relationship.
 * 
 * A single Tuple may result in zero-to-many Relationships.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: kessel/relations/v1beta1/relation_tuples.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselTupleServiceGrpc {

  private KesselTupleServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.relations.v1beta1.KesselTupleService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.CreateTuplesRequest,
      org.project_kessel.api.relations.v1beta1.CreateTuplesResponse> getCreateTuplesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTuples",
      requestType = org.project_kessel.api.relations.v1beta1.CreateTuplesRequest.class,
      responseType = org.project_kessel.api.relations.v1beta1.CreateTuplesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.CreateTuplesRequest,
      org.project_kessel.api.relations.v1beta1.CreateTuplesResponse> getCreateTuplesMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.CreateTuplesRequest, org.project_kessel.api.relations.v1beta1.CreateTuplesResponse> getCreateTuplesMethod;
    if ((getCreateTuplesMethod = KesselTupleServiceGrpc.getCreateTuplesMethod) == null) {
      synchronized (KesselTupleServiceGrpc.class) {
        if ((getCreateTuplesMethod = KesselTupleServiceGrpc.getCreateTuplesMethod) == null) {
          KesselTupleServiceGrpc.getCreateTuplesMethod = getCreateTuplesMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.relations.v1beta1.CreateTuplesRequest, org.project_kessel.api.relations.v1beta1.CreateTuplesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTuples"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.CreateTuplesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.CreateTuplesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselTupleServiceMethodDescriptorSupplier("CreateTuples"))
              .build();
        }
      }
    }
    return getCreateTuplesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.ReadTuplesRequest,
      org.project_kessel.api.relations.v1beta1.ReadTuplesResponse> getReadTuplesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReadTuples",
      requestType = org.project_kessel.api.relations.v1beta1.ReadTuplesRequest.class,
      responseType = org.project_kessel.api.relations.v1beta1.ReadTuplesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.ReadTuplesRequest,
      org.project_kessel.api.relations.v1beta1.ReadTuplesResponse> getReadTuplesMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.ReadTuplesRequest, org.project_kessel.api.relations.v1beta1.ReadTuplesResponse> getReadTuplesMethod;
    if ((getReadTuplesMethod = KesselTupleServiceGrpc.getReadTuplesMethod) == null) {
      synchronized (KesselTupleServiceGrpc.class) {
        if ((getReadTuplesMethod = KesselTupleServiceGrpc.getReadTuplesMethod) == null) {
          KesselTupleServiceGrpc.getReadTuplesMethod = getReadTuplesMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.relations.v1beta1.ReadTuplesRequest, org.project_kessel.api.relations.v1beta1.ReadTuplesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReadTuples"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.ReadTuplesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.ReadTuplesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselTupleServiceMethodDescriptorSupplier("ReadTuples"))
              .build();
        }
      }
    }
    return getReadTuplesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest,
      org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse> getDeleteTuplesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteTuples",
      requestType = org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest.class,
      responseType = org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest,
      org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse> getDeleteTuplesMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest, org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse> getDeleteTuplesMethod;
    if ((getDeleteTuplesMethod = KesselTupleServiceGrpc.getDeleteTuplesMethod) == null) {
      synchronized (KesselTupleServiceGrpc.class) {
        if ((getDeleteTuplesMethod = KesselTupleServiceGrpc.getDeleteTuplesMethod) == null) {
          KesselTupleServiceGrpc.getDeleteTuplesMethod = getDeleteTuplesMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest, org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteTuples"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselTupleServiceMethodDescriptorSupplier("DeleteTuples"))
              .build();
        }
      }
    }
    return getDeleteTuplesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselTupleServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceStub>() {
        @java.lang.Override
        public KesselTupleServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselTupleServiceStub(channel, callOptions);
        }
      };
    return KesselTupleServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselTupleServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceBlockingStub>() {
        @java.lang.Override
        public KesselTupleServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselTupleServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselTupleServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselTupleServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceFutureStub>() {
        @java.lang.Override
        public KesselTupleServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselTupleServiceFutureStub(channel, callOptions);
        }
      };
    return KesselTupleServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * KesselTupleServices manages the persisted _Tuples_ stored in the system..
   * 
   * A Tuple is an explicitly stated, persistent relation 
   * between a Resource and a Subject or Subject Set. 
   * It has the same _shape_ as a Relationship but is not the same thing as a Relationship.
   * 
   * A single Tuple may result in zero-to-many Relationships.
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void createTuples(org.project_kessel.api.relations.v1beta1.CreateTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.CreateTuplesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateTuplesMethod(), responseObserver);
    }

    /**
     */
    default void readTuples(org.project_kessel.api.relations.v1beta1.ReadTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.ReadTuplesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReadTuplesMethod(), responseObserver);
    }

    /**
     */
    default void deleteTuples(org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteTuplesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselTupleService.
   * <pre>
   * KesselTupleServices manages the persisted _Tuples_ stored in the system..
   * 
   * A Tuple is an explicitly stated, persistent relation 
   * between a Resource and a Subject or Subject Set. 
   * It has the same _shape_ as a Relationship but is not the same thing as a Relationship.
   * 
   * A single Tuple may result in zero-to-many Relationships.
   * </pre>
   */
  public static abstract class KesselTupleServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselTupleServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselTupleService.
   * <pre>
   * KesselTupleServices manages the persisted _Tuples_ stored in the system..
   * 
   * A Tuple is an explicitly stated, persistent relation 
   * between a Resource and a Subject or Subject Set. 
   * It has the same _shape_ as a Relationship but is not the same thing as a Relationship.
   * 
   * A single Tuple may result in zero-to-many Relationships.
   * </pre>
   */
  public static final class KesselTupleServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselTupleServiceStub> {
    private KesselTupleServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselTupleServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselTupleServiceStub(channel, callOptions);
    }

    /**
     */
    public void createTuples(org.project_kessel.api.relations.v1beta1.CreateTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.CreateTuplesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateTuplesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void readTuples(org.project_kessel.api.relations.v1beta1.ReadTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.ReadTuplesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getReadTuplesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteTuples(org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteTuplesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselTupleService.
   * <pre>
   * KesselTupleServices manages the persisted _Tuples_ stored in the system..
   * 
   * A Tuple is an explicitly stated, persistent relation 
   * between a Resource and a Subject or Subject Set. 
   * It has the same _shape_ as a Relationship but is not the same thing as a Relationship.
   * 
   * A single Tuple may result in zero-to-many Relationships.
   * </pre>
   */
  public static final class KesselTupleServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselTupleServiceBlockingStub> {
    private KesselTupleServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselTupleServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselTupleServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.relations.v1beta1.CreateTuplesResponse createTuples(org.project_kessel.api.relations.v1beta1.CreateTuplesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateTuplesMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<org.project_kessel.api.relations.v1beta1.ReadTuplesResponse> readTuples(
        org.project_kessel.api.relations.v1beta1.ReadTuplesRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getReadTuplesMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse deleteTuples(org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteTuplesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselTupleService.
   * <pre>
   * KesselTupleServices manages the persisted _Tuples_ stored in the system..
   * 
   * A Tuple is an explicitly stated, persistent relation 
   * between a Resource and a Subject or Subject Set. 
   * It has the same _shape_ as a Relationship but is not the same thing as a Relationship.
   * 
   * A single Tuple may result in zero-to-many Relationships.
   * </pre>
   */
  public static final class KesselTupleServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselTupleServiceFutureStub> {
    private KesselTupleServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselTupleServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselTupleServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.relations.v1beta1.CreateTuplesResponse> createTuples(
        org.project_kessel.api.relations.v1beta1.CreateTuplesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateTuplesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse> deleteTuples(
        org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteTuplesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_TUPLES = 0;
  private static final int METHODID_READ_TUPLES = 1;
  private static final int METHODID_DELETE_TUPLES = 2;

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
        case METHODID_CREATE_TUPLES:
          serviceImpl.createTuples((org.project_kessel.api.relations.v1beta1.CreateTuplesRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.CreateTuplesResponse>) responseObserver);
          break;
        case METHODID_READ_TUPLES:
          serviceImpl.readTuples((org.project_kessel.api.relations.v1beta1.ReadTuplesRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.ReadTuplesResponse>) responseObserver);
          break;
        case METHODID_DELETE_TUPLES:
          serviceImpl.deleteTuples((org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse>) responseObserver);
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
          getCreateTuplesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.relations.v1beta1.CreateTuplesRequest,
              org.project_kessel.api.relations.v1beta1.CreateTuplesResponse>(
                service, METHODID_CREATE_TUPLES)))
        .addMethod(
          getReadTuplesMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.project_kessel.api.relations.v1beta1.ReadTuplesRequest,
              org.project_kessel.api.relations.v1beta1.ReadTuplesResponse>(
                service, METHODID_READ_TUPLES)))
        .addMethod(
          getDeleteTuplesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.relations.v1beta1.DeleteTuplesRequest,
              org.project_kessel.api.relations.v1beta1.DeleteTuplesResponse>(
                service, METHODID_DELETE_TUPLES)))
        .build();
  }

  private static abstract class KesselTupleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselTupleServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.relations.v1beta1.RelationTuples.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselTupleService");
    }
  }

  private static final class KesselTupleServiceFileDescriptorSupplier
      extends KesselTupleServiceBaseDescriptorSupplier {
    KesselTupleServiceFileDescriptorSupplier() {}
  }

  private static final class KesselTupleServiceMethodDescriptorSupplier
      extends KesselTupleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselTupleServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KesselTupleServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselTupleServiceFileDescriptorSupplier())
              .addMethod(getCreateTuplesMethod())
              .addMethod(getReadTuplesMethod())
              .addMethod(getDeleteTuplesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
