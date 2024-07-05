// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kessel/relations/v1beta1/relation_tuples.proto

// Protobuf Java Version: 3.25.1
package org.project_kessel.api.relations.v1beta1;

public final class RelationTuples {
  private RelationTuples() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_CreateTuplesRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_CreateTuplesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_CreateTuplesResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_CreateTuplesResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_ReadTuplesRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_ReadTuplesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_ReadTuplesResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_ReadTuplesResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_DeleteTuplesRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_DeleteTuplesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_DeleteTuplesResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_DeleteTuplesResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_RelationTupleFilter_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_RelationTupleFilter_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_kessel_relations_v1beta1_SubjectFilter_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_kessel_relations_v1beta1_SubjectFilter_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n.kessel/relations/v1beta1/relation_tupl" +
      "es.proto\022\030kessel.relations.v1beta1\032\034goog" +
      "le/api/annotations.proto\032%kessel/relatio" +
      "ns/v1beta1/common.proto\032\027validate/valida" +
      "te.proto\"m\n\023CreateTuplesRequest\022\026\n\006upser" +
      "t\030\001 \001(\010R\006upsert\022>\n\006tuples\030\002 \003(\0132&.kessel" +
      ".relations.v1beta1.RelationshipR\006tuples\"" +
      "\026\n\024CreateTuplesResponse\"\305\001\n\021ReadTuplesRe" +
      "quest\022O\n\006filter\030\001 \001(\0132-.kessel.relations" +
      ".v1beta1.RelationTupleFilterB\010\372B\005\242\001\002\010\001R\006" +
      "filter\022P\n\npagination\030\002 \001(\0132+.kessel.rela" +
      "tions.v1beta1.RequestPaginationH\000R\npagin" +
      "ation\210\001\001B\r\n\013_pagination\"\240\001\n\022ReadTuplesRe" +
      "sponse\022<\n\005tuple\030\001 \001(\0132&.kessel.relations" +
      ".v1beta1.RelationshipR\005tuple\022L\n\npaginati" +
      "on\030\002 \001(\0132,.kessel.relations.v1beta1.Resp" +
      "onsePaginationR\npagination\"f\n\023DeleteTupl" +
      "esRequest\022O\n\006filter\030\001 \001(\0132-.kessel.relat" +
      "ions.v1beta1.RelationTupleFilterB\010\372B\005\242\001\002" +
      "\010\001R\006filter\"\026\n\024DeleteTuplesResponse\"\350\002\n\023R" +
      "elationTupleFilter\0222\n\022resource_namespace" +
      "\030\001 \001(\tH\000R\021resourceNamespace\210\001\001\022(\n\rresour" +
      "ce_type\030\002 \001(\tH\001R\014resourceType\210\001\001\022$\n\013reso" +
      "urce_id\030\003 \001(\tH\002R\nresourceId\210\001\001\022\037\n\010relati" +
      "on\030\004 \001(\tH\003R\010relation\210\001\001\022S\n\016subject_filte" +
      "r\030\005 \001(\0132\'.kessel.relations.v1beta1.Subje" +
      "ctFilterH\004R\rsubjectFilter\210\001\001B\025\n\023_resourc" +
      "e_namespaceB\020\n\016_resource_typeB\016\n\014_resour" +
      "ce_idB\013\n\t_relationB\021\n\017_subject_filter\"\361\001" +
      "\n\rSubjectFilter\0220\n\021subject_namespace\030\001 \001" +
      "(\tH\000R\020subjectNamespace\210\001\001\022&\n\014subject_typ" +
      "e\030\002 \001(\tH\001R\013subjectType\210\001\001\022\"\n\nsubject_id\030" +
      "\003 \001(\tH\002R\tsubjectId\210\001\001\022\037\n\010relation\030\004 \001(\tH" +
      "\003R\010relation\210\001\001B\024\n\022_subject_namespaceB\017\n\r" +
      "_subject_typeB\r\n\013_subject_idB\013\n\t_relatio" +
      "n2\256\003\n\022KesselTupleService\022\211\001\n\014CreateTuple" +
      "s\022-.kessel.relations.v1beta1.CreateTuple" +
      "sRequest\032..kessel.relations.v1beta1.Crea" +
      "teTuplesResponse\"\032\202\323\344\223\002\024\"\017/v1beta1/tuple" +
      "s:\001*\022\202\001\n\nReadTuples\022+.kessel.relations.v" +
      "1beta1.ReadTuplesRequest\032,.kessel.relati" +
      "ons.v1beta1.ReadTuplesResponse\"\027\202\323\344\223\002\021\022\017" +
      "/v1beta1/tuples0\001\022\206\001\n\014DeleteTuples\022-.kes" +
      "sel.relations.v1beta1.DeleteTuplesReques" +
      "t\032..kessel.relations.v1beta1.DeleteTuple" +
      "sResponse\"\027\202\323\344\223\002\021*\017/v1beta1/tuplesBr\n(or" +
      "g.project_kessel.api.relations.v1beta1P\001" +
      "ZDgithub.com/project-kessel/relations-ap" +
      "i/api/kessel/relations/v1beta1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.api.AnnotationsProto.getDescriptor(),
          org.project_kessel.api.relations.v1beta1.Common.getDescriptor(),
          io.envoyproxy.pgv.validate.Validate.getDescriptor(),
        });
    internal_static_kessel_relations_v1beta1_CreateTuplesRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_kessel_relations_v1beta1_CreateTuplesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_CreateTuplesRequest_descriptor,
        new java.lang.String[] { "Upsert", "Tuples", });
    internal_static_kessel_relations_v1beta1_CreateTuplesResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_kessel_relations_v1beta1_CreateTuplesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_CreateTuplesResponse_descriptor,
        new java.lang.String[] { });
    internal_static_kessel_relations_v1beta1_ReadTuplesRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_kessel_relations_v1beta1_ReadTuplesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_ReadTuplesRequest_descriptor,
        new java.lang.String[] { "Filter", "Pagination", });
    internal_static_kessel_relations_v1beta1_ReadTuplesResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_kessel_relations_v1beta1_ReadTuplesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_ReadTuplesResponse_descriptor,
        new java.lang.String[] { "Tuple", "Pagination", });
    internal_static_kessel_relations_v1beta1_DeleteTuplesRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_kessel_relations_v1beta1_DeleteTuplesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_DeleteTuplesRequest_descriptor,
        new java.lang.String[] { "Filter", });
    internal_static_kessel_relations_v1beta1_DeleteTuplesResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_kessel_relations_v1beta1_DeleteTuplesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_DeleteTuplesResponse_descriptor,
        new java.lang.String[] { });
    internal_static_kessel_relations_v1beta1_RelationTupleFilter_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_kessel_relations_v1beta1_RelationTupleFilter_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_RelationTupleFilter_descriptor,
        new java.lang.String[] { "ResourceNamespace", "ResourceType", "ResourceId", "Relation", "SubjectFilter", });
    internal_static_kessel_relations_v1beta1_SubjectFilter_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_kessel_relations_v1beta1_SubjectFilter_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_kessel_relations_v1beta1_SubjectFilter_descriptor,
        new java.lang.String[] { "SubjectNamespace", "SubjectType", "SubjectId", "Relation", });
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