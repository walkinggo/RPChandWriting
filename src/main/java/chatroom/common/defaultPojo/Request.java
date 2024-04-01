package chatroom.common.defaultPojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class Request implements Serializable {
    private static final long serialVersionUID = 4856789546132L;
    private Message message;
    private int code;
}
