package common.builder;

import common.RPCResponse;

public class RPCResponseBuilder {

    public static RPCResponse success(Object data){
        return RPCResponse.builder().code(200).data(data).message("服务调用成功").build();
    }
    public static RPCResponse fail(){
        return RPCResponse.builder().code(500).message("服务器发生错误").build();
    }
}
