package chatroom.common.serialization;

public class requestBuilder {
    public static Request newSuccessRequest(Message message){
        return Request.newBuilder().setCode(200).setMessage(message).build();
    }

    public static Request newFailRequest(Message message){
        return Request.newBuilder().setCode(404).setMessage(message).build();
    }
    public static Request newRequestInstance(){
        return newSuccessRequest(messageBuilder.getMessageInstance());
    }
}
