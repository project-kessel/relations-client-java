// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kessel/relations/v1beta1/relation_tuples.proto

// Protobuf Java Version: 3.25.1
package org.project_kessel.api.relations.v1beta1;

public interface ReadTuplesResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:kessel.relations.v1beta1.ReadTuplesResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
   * @return Whether the tuple field is set.
   */
  boolean hasTuple();
  /**
   * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
   * @return The tuple.
   */
  org.project_kessel.api.relations.v1beta1.Relationship getTuple();
  /**
   * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
   */
  org.project_kessel.api.relations.v1beta1.RelationshipOrBuilder getTupleOrBuilder();

  /**
   * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
   * @return Whether the pagination field is set.
   */
  boolean hasPagination();
  /**
   * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
   * @return The pagination.
   */
  org.project_kessel.api.relations.v1beta1.ResponsePagination getPagination();
  /**
   * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
   */
  org.project_kessel.api.relations.v1beta1.ResponsePaginationOrBuilder getPaginationOrBuilder();
}