// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Request.proto

package chatroom.common.serialization;

public final class RequestOuterClass {
  private RequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_chatroom_common_serialization_Message_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_chatroom_common_serialization_Message_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_chatroom_common_serialization_Request_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_chatroom_common_serialization_Request_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rRequest.proto\022\035chatroom.common.seriali" +
      "zation\"1\n\007Message\022\013\n\003uid\030\001 \001(\t\022\013\n\003msg\030\002 " +
      "\001(\t\022\014\n\004date\030\003 \001(\t\"P\n\007Request\0227\n\007message\030" +
      "\001 \001(\0132&.chatroom.common.serialization.Me" +
      "ssage\022\014\n\004code\030\002 \001(\005B\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_chatroom_common_serialization_Message_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_chatroom_common_serialization_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_chatroom_common_serialization_Message_descriptor,
        new java.lang.String[] { "Uid", "Msg", "Date", });
    internal_static_chatroom_common_serialization_Request_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_chatroom_common_serialization_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_chatroom_common_serialization_Request_descriptor,
        new java.lang.String[] { "Message", "Code", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}