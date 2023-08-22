package client;

import common.User;
import service.UserService;

public class TestClient {
    public static void main(String[] args) {
        NettyRPCClient rpcClient = new NettyRPCClient("127.0.0.1", 8899);
        RPCClientProxy rpcClientProxy = new RPCClientProxy(rpcClient);
        UserService userService = rpcClientProxy.getProxy(UserService.class);
        System.out.println("userService.getUserById(10) = " + userService.getUserById(10));
        System.out.println("userService.insertUserId(User.builder().userName(\"张三\").id(100).sex(true).build()) = "
                + userService
                .insertUserId(User.builder()
                        .userName("张三").id(100)
                        .sex(true)
                        .build()));


    }
}
