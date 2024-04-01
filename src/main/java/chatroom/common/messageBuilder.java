package chatroom.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class messageBuilder {
    public static Message getMessageInstance(){
        return Message.builder().msg("随机信息" + Math.random()).date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).uid(UUID.randomUUID().toString()).build();
    }
}
