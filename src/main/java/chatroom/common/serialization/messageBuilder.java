package chatroom.common.serialization;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class messageBuilder {
    public static Message getMessageInstance(){
        return Message.newBuilder().setMsg("随机信息" + Math.random()).setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).setUid(UUID.randomUUID().toString()).build();
    }
}
