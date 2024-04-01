package chatroom.common;

public class requestBuilder {
    public static Request newSuccessRequest(Message message){
        return Request.builder().code(200).message(message).build();
    }

    public static Request newFailRequest(Message message){
        return Request.builder().code(404).message(message).build();
    }
    public static Request newRequestInstance(){
        return newSuccessRequest(messageBuilder.getMessageInstance());
    }
}
