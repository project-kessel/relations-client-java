// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kessel/relations/v1beta1/check.proto

// Protobuf Java Version: 3.25.1
package org.project_kessel.api.relations.v1beta1;

public final class Check {
  private Check() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_CheckRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_CheckRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_CheckResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_CheckResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n$kessel/relations/v1beta1/check.proto\022\030" +
      "kessel.relations.v1beta1\032\034google/api/ann" +
      "otations.proto\032%kessel/relations/v1beta1" +
      "/common.proto\032\027validate/validate.proto\"\324" +
      "\001\n\014CheckRequest\022O\n\010resource\030\001 \001(\0132).kess" +
      "el.relations.v1beta1.ObjectReferenceB\010\372B" +
      "\005\242\001\002\010\001R\010resource\022#\n\010relation\030\002 \001(\tB\007\372B\004r" +
      "\002\020\001R\010relation\022N\n\007subject\030\003 \001(\0132*.kessel." +
      "relations.v1beta1.SubjectReferenceB\010\372B\005\242" +
      "\001\002\010\001R\007subject\"\243\001\n\rCheckResponse\022I\n\007allow" +
      "ed\030\001 \001(\0162/.kessel.relations.v1beta1.Chec" +
      "kResponse.AllowedR\007allowed\"G\n\007Allowed\022\027\n" +
      "\023ALLOWED_UNSPECIFIED\020\000\022\020\n\014ALLOWED_TRUE\020\001" +
      "\022\021\n\rALLOWED_FALSE\020\0022\211\001\n\022KesselCheckServi" +
      "ce\022s\n\005Check\022&.kessel.relations.v1beta1.C" +
      "heckRequest\032\'.kessel.relations.v1beta1.C" +
      "heckResponse\"\031\202\323\344\223\002\023\"\016/v1beta1/check:\001*B" +
      "r\n(org.project_kessel.api.relations.v1be" +
      "ta1P\001ZDgithub.com/project-kessel/relatio" +
      "ns-api/api/kessel/relations/v1beta1b\006pro" +
      "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.api.AnnotationsProto.getDescriptor(),
          org.project_kessel.api.relations.v1beta1.Common.getDescriptor(),
          io.envoyproxy.pgv.validate.Validate.getDescriptor(),
        });
    internal_static_kessel_relations_v1beta1_CheckRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_kessel_relations_v1beta1_CheckRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_CheckRequest_descriptor,
        new java.lang.String[] { "Resource", "Relation", "Subject", });
    internal_static_kessel_relations_v1beta1_CheckResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_kessel_relations_v1beta1_CheckResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_CheckResponse_descriptor,
        new java.lang.String[] { "Allowed", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.api.AnnotationsProto.http);
    registry.add(io.envoyproxy.pgv.validate.Validate.rules);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.api.AnnotationsProto.getDescriptor();
    org.project_kessel.api.relations.v1beta1.Common.getDescriptor();
    io.envoyproxy.pgv.validate.Validate.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
