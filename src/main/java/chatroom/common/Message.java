package chatroom.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class Message implements Serializable {
    private static final long serialVersionUID = 5697531295789412L;
    private String uid;
    private String msg;
    private String date;
}
