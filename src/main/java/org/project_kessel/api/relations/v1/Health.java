// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kessel/relations/v1/health.proto

// Protobuf Java Version: 3.25.1
package org.project_kessel.api.relations.v1;

public final class Health {
  private Health() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1_GetLivezRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1_GetLivezRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1_GetLivezResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1_GetLivezResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1_GetReadyzRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1_GetReadyzRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1_GetReadyzResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1_GetReadyzResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n kessel/relations/v1/health.proto\022\023kess" +
      "el.relations.v1\032\034google/api/annotations." +
      "proto\"\021\n\017GetLivezRequest\">\n\020GetLivezResp" +
      "onse\022\026\n\006status\030\001 \001(\tR\006status\022\022\n\004code\030\002 \001" +
      "(\rR\004code\"\022\n\020GetReadyzRequest\"?\n\021GetReady" +
      "zResponse\022\026\n\006status\030\001 \001(\tR\006status\022\022\n\004cod" +
      "e\030\002 \001(\rR\004code2\353\001\n\023KesselHealthService\022g\n" +
      "\010GetLivez\022$.kessel.relations.v1.GetLivez" +
      "Request\032%.kessel.relations.v1.GetLivezRe" +
      "sponse\"\016\202\323\344\223\002\010\022\006/livez\022k\n\tGetReadyz\022%.ke" +
      "ssel.relations.v1.GetReadyzRequest\032&.kes" +
      "sel.relations.v1.GetReadyzResponse\"\017\202\323\344\223" +
      "\002\t\022\007/readyzBh\n#org.project_kessel.api.re" +
      "lations.v1P\001Z?github.com/project-kessel/" +
      "relations-api/api/kessel/relations/v1b\006p" +
      "roto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.api.AnnotationsProto.getDescriptor(),
        });
    internal_static_kessel_relations_v1_GetLivezRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_kessel_relations_v1_GetLivezRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1_GetLivezRequest_descriptor,
        new java.lang.String[] { });
    internal_static_kessel_relations_v1_GetLivezResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_kessel_relations_v1_GetLivezResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1_GetLivezResponse_descriptor,
        new java.lang.String[] { "Status", "Code", });
    internal_static_kessel_relations_v1_GetReadyzRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_kessel_relations_v1_GetReadyzRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1_GetReadyzRequest_descriptor,
        new java.lang.String[] { });
    internal_static_kessel_relations_v1_GetReadyzResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_kessel_relations_v1_GetReadyzResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1_GetReadyzResponse_descriptor,
        new java.lang.String[] { "Status", "Code", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.api.AnnotationsProto.http);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.api.AnnotationsProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}