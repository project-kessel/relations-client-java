// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kessel/relations/v1beta1/relation_tuples.proto

// Protobuf Java Version: 3.25.1
package org.project_kessel.api.relations.v1beta1;

/**
 * Protobuf type {@code kessel.relations.v1beta1.ReadTuplesResponse}
 */
public final class ReadTuplesResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:kessel.relations.v1beta1.ReadTuplesResponse)
    ReadTuplesResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ReadTuplesResponse.newBuilder() to construct.
  private ReadTuplesResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ReadTuplesResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ReadTuplesResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.project_kessel.api.relations.v1beta1.RelationTuples.internal_static_kessel_relations_v1beta1_ReadTuplesResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.project_kessel.api.relations.v1beta1.RelationTuples.internal_static_kessel_relations_v1beta1_ReadTuplesResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.project_kessel.api.relations.v1beta1.ReadTuplesResponse.class, org.project_kessel.api.relations.v1beta1.ReadTuplesResponse.Builder.class);
  }

  private int bitField0_;
  public static final int TUPLE_FIELD_NUMBER = 1;
  private org.project_kessel.api.relations.v1beta1.Relationship tuple_;
  /**
   * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
   * @return Whether the tuple field is set.
   */
  @java.lang.Override
  public boolean hasTuple() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
   * @return The tuple.
   */
  @java.lang.Override
  public org.project_kessel.api.relations.v1beta1.Relationship getTuple() {
    return tuple_ == null ? org.project_kessel.api.relations.v1beta1.Relationship.getDefaultInstance() : tuple_;
  }
  /**
   * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
   */
  @java.lang.Override
  public org.project_kessel.api.relations.v1beta1.RelationshipOrBuilder getTupleOrBuilder() {
    return tuple_ == null ? org.project_kessel.api.relations.v1beta1.Relationship.getDefaultInstance() : tuple_;
  }

  public static final int PAGINATION_FIELD_NUMBER = 2;
  private org.project_kessel.api.relations.v1beta1.ResponsePagination pagination_;
  /**
   * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
   * @return Whether the pagination field is set.
   */
  @java.lang.Override
  public boolean hasPagination() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
   * @return The pagination.
   */
  @java.lang.Override
  public org.project_kessel.api.relations.v1beta1.ResponsePagination getPagination() {
    return pagination_ == null ? org.project_kessel.api.relations.v1beta1.ResponsePagination.getDefaultInstance() : pagination_;
  }
  /**
   * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
   */
  @java.lang.Override
  public org.project_kessel.api.relations.v1beta1.ResponsePaginationOrBuilder getPaginationOrBuilder() {
    return pagination_ == null ? org.project_kessel.api.relations.v1beta1.ResponsePagination.getDefaultInstance() : pagination_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getTuple());
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeMessage(2, getPagination());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getTuple());
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getPagination());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.project_kessel.api.relations.v1beta1.ReadTuplesResponse)) {
      return super.equals(obj);
    }
    org.project_kessel.api.relations.v1beta1.ReadTuplesResponse other = (org.project_kessel.api.relations.v1beta1.ReadTuplesResponse) obj;

    if (hasTuple() != other.hasTuple()) return false;
    if (hasTuple()) {
      if (!getTuple()
          .equals(other.getTuple())) return false;
    }
    if (hasPagination() != other.hasPagination()) return false;
    if (hasPagination()) {
      if (!getPagination()
          .equals(other.getPagination())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasTuple()) {
      hash = (37 * hash) + TUPLE_FIELD_NUMBER;
      hash = (53 * hash) + getTuple().hashCode();
    }
    if (hasPagination()) {
      hash = (37 * hash) + PAGINATION_FIELD_NUMBER;
      hash = (53 * hash) + getPagination().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.project_kessel.api.relations.v1beta1.ReadTuplesResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code kessel.relations.v1beta1.ReadTuplesResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:kessel.relations.v1beta1.ReadTuplesResponse)
      org.project_kessel.api.relations.v1beta1.ReadTuplesResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.project_kessel.api.relations.v1beta1.RelationTuples.internal_static_kessel_relations_v1beta1_ReadTuplesResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.project_kessel.api.relations.v1beta1.RelationTuples.internal_static_kessel_relations_v1beta1_ReadTuplesResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.project_kessel.api.relations.v1beta1.ReadTuplesResponse.class, org.project_kessel.api.relations.v1beta1.ReadTuplesResponse.Builder.class);
    }

    // Construct using org.project_kessel.api.relations.v1beta1.ReadTuplesResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getTupleFieldBuilder();
        getPaginationFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      tuple_ = null;
      if (tupleBuilder_ != null) {
        tupleBuilder_.dispose();
        tupleBuilder_ = null;
      }
      pagination_ = null;
      if (paginationBuilder_ != null) {
        paginationBuilder_.dispose();
        paginationBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.project_kessel.api.relations.v1beta1.RelationTuples.internal_static_kessel_relations_v1beta1_ReadTuplesResponse_descriptor;
    }

    @java.lang.Override
    public org.project_kessel.api.relations.v1beta1.ReadTuplesResponse getDefaultInstanceForType() {
      return org.project_kessel.api.relations.v1beta1.ReadTuplesResponse.getDefaultInstance();
    }

    @java.lang.Override
    public org.project_kessel.api.relations.v1beta1.ReadTuplesResponse build() {
      org.project_kessel.api.relations.v1beta1.ReadTuplesResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.project_kessel.api.relations.v1beta1.ReadTuplesResponse buildPartial() {
      org.project_kessel.api.relations.v1beta1.ReadTuplesResponse result = new org.project_kessel.api.relations.v1beta1.ReadTuplesResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.project_kessel.api.relations.v1beta1.ReadTuplesResponse result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.tuple_ = tupleBuilder_ == null
            ? tuple_
            : tupleBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.pagination_ = paginationBuilder_ == null
            ? pagination_
            : paginationBuilder_.build();
        to_bitField0_ |= 0x00000002;
      }
      result.bitField0_ |= to_bitField0_;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.project_kessel.api.relations.v1beta1.ReadTuplesResponse) {
        return mergeFrom((org.project_kessel.api.relations.v1beta1.ReadTuplesResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.project_kessel.api.relations.v1beta1.ReadTuplesResponse other) {
      if (other == org.project_kessel.api.relations.v1beta1.ReadTuplesResponse.getDefaultInstance()) return this;
      if (other.hasTuple()) {
        mergeTuple(other.getTuple());
      }
      if (other.hasPagination()) {
        mergePagination(other.getPagination());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              input.readMessage(
                  getTupleFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              input.readMessage(
                  getPaginationFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private org.project_kessel.api.relations.v1beta1.Relationship tuple_;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.project_kessel.api.relations.v1beta1.Relationship, org.project_kessel.api.relations.v1beta1.Relationship.Builder, org.project_kessel.api.relations.v1beta1.RelationshipOrBuilder> tupleBuilder_;
    /**
     * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
     * @return Whether the tuple field is set.
     */
    public boolean hasTuple() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
     * @return The tuple.
     */
    public org.project_kessel.api.relations.v1beta1.Relationship getTuple() {
      if (tupleBuilder_ == null) {
        return tuple_ == null ? org.project_kessel.api.relations.v1beta1.Relationship.getDefaultInstance() : tuple_;
      } else {
        return tupleBuilder_.getMessage();
      }
    }
    /**
     * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
     */
    public Builder setTuple(org.project_kessel.api.relations.v1beta1.Relationship value) {
      if (tupleBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        tuple_ = value;
      } else {
        tupleBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
     */
    public Builder setTuple(
        org.project_kessel.api.relations.v1beta1.Relationship.Builder builderForValue) {
      if (tupleBuilder_ == null) {
        tuple_ = builderForValue.build();
      } else {
        tupleBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
     */
    public Builder mergeTuple(org.project_kessel.api.relations.v1beta1.Relationship value) {
      if (tupleBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          tuple_ != null &&
          tuple_ != org.project_kessel.api.relations.v1beta1.Relationship.getDefaultInstance()) {
          getTupleBuilder().mergeFrom(value);
        } else {
          tuple_ = value;
        }
      } else {
        tupleBuilder_.mergeFrom(value);
      }
      if (tuple_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
     */
    public Builder clearTuple() {
      bitField0_ = (bitField0_ & ~0x00000001);
      tuple_ = null;
      if (tupleBuilder_ != null) {
        tupleBuilder_.dispose();
        tupleBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
     */
    public org.project_kessel.api.relations.v1beta1.Relationship.Builder getTupleBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getTupleFieldBuilder().getBuilder();
    }
    /**
     * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
     */
    public org.project_kessel.api.relations.v1beta1.RelationshipOrBuilder getTupleOrBuilder() {
      if (tupleBuilder_ != null) {
        return tupleBuilder_.getMessageOrBuilder();
      } else {
        return tuple_ == null ?
            org.project_kessel.api.relations.v1beta1.Relationship.getDefaultInstance() : tuple_;
      }
    }
    /**
     * <code>.kessel.relations.v1beta1.Relationship tuple = 1 [json_name = "tuple"];</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.project_kessel.api.relations.v1beta1.Relationship, org.project_kessel.api.relations.v1beta1.Relationship.Builder, org.project_kessel.api.relations.v1beta1.RelationshipOrBuilder> 
        getTupleFieldBuilder() {
      if (tupleBuilder_ == null) {
        tupleBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.project_kessel.api.relations.v1beta1.Relationship, org.project_kessel.api.relations.v1beta1.Relationship.Builder, org.project_kessel.api.relations.v1beta1.RelationshipOrBuilder>(
                getTuple(),
                getParentForChildren(),
                isClean());
        tuple_ = null;
      }
      return tupleBuilder_;
    }

    private org.project_kessel.api.relations.v1beta1.ResponsePagination pagination_;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.project_kessel.api.relations.v1beta1.ResponsePagination, org.project_kessel.api.relations.v1beta1.ResponsePagination.Builder, org.project_kessel.api.relations.v1beta1.ResponsePaginationOrBuilder> paginationBuilder_;
    /**
     * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
     * @return Whether the pagination field is set.
     */
    public boolean hasPagination() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
     * @return The pagination.
     */
    public org.project_kessel.api.relations.v1beta1.ResponsePagination getPagination() {
      if (paginationBuilder_ == null) {
        return pagination_ == null ? org.project_kessel.api.relations.v1beta1.ResponsePagination.getDefaultInstance() : pagination_;
      } else {
        return paginationBuilder_.getMessage();
      }
    }
    /**
     * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
     */
    public Builder setPagination(org.project_kessel.api.relations.v1beta1.ResponsePagination value) {
      if (paginationBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        pagination_ = value;
      } else {
        paginationBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
     */
    public Builder setPagination(
        org.project_kessel.api.relations.v1beta1.ResponsePagination.Builder builderForValue) {
      if (paginationBuilder_ == null) {
        pagination_ = builderForValue.build();
      } else {
        paginationBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
     */
    public Builder mergePagination(org.project_kessel.api.relations.v1beta1.ResponsePagination value) {
      if (paginationBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
          pagination_ != null &&
          pagination_ != org.project_kessel.api.relations.v1beta1.ResponsePagination.getDefaultInstance()) {
          getPaginationBuilder().mergeFrom(value);
        } else {
          pagination_ = value;
        }
      } else {
        paginationBuilder_.mergeFrom(value);
      }
      if (pagination_ != null) {
        bitField0_ |= 0x00000002;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
     */
    public Builder clearPagination() {
      bitField0_ = (bitField0_ & ~0x00000002);
      pagination_ = null;
      if (paginationBuilder_ != null) {
        paginationBuilder_.dispose();
        paginationBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
     */
    public org.project_kessel.api.relations.v1beta1.ResponsePagination.Builder getPaginationBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getPaginationFieldBuilder().getBuilder();
    }
    /**
     * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
     */
    public org.project_kessel.api.relations.v1beta1.ResponsePaginationOrBuilder getPaginationOrBuilder() {
      if (paginationBuilder_ != null) {
        return paginationBuilder_.getMessageOrBuilder();
      } else {
        return pagination_ == null ?
            org.project_kessel.api.relations.v1beta1.ResponsePagination.getDefaultInstance() : pagination_;
      }
    }
    /**
     * <code>.kessel.relations.v1beta1.ResponsePagination pagination = 2 [json_name = "pagination"];</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.project_kessel.api.relations.v1beta1.ResponsePagination, org.project_kessel.api.relations.v1beta1.ResponsePagination.Builder, org.project_kessel.api.relations.v1beta1.ResponsePaginationOrBuilder> 
        getPaginationFieldBuilder() {
      if (paginationBuilder_ == null) {
        paginationBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.project_kessel.api.relations.v1beta1.ResponsePagination, org.project_kessel.api.relations.v1beta1.ResponsePagination.Builder, org.project_kessel.api.relations.v1beta1.ResponsePaginationOrBuilder>(
                getPagination(),
                getParentForChildren(),
                isClean());
        pagination_ = null;
      }
      return paginationBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:kessel.relations.v1beta1.ReadTuplesResponse)
  }

  // @@protoc_insertion_point(class_scope:kessel.relations.v1beta1.ReadTuplesResponse)
  private static final org.project_kessel.api.relations.v1beta1.ReadTuplesResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.project_kessel.api.relations.v1beta1.ReadTuplesResponse();
  }

  public static org.project_kessel.api.relations.v1beta1.ReadTuplesResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ReadTuplesResponse>
      PARSER = new com.google.protobuf.AbstractParser<ReadTuplesResponse>() {
    @java.lang.Override
    public ReadTuplesResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<ReadTuplesResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ReadTuplesResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.project_kessel.api.relations.v1beta1.ReadTuplesResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
