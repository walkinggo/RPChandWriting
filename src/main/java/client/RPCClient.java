package client;

import common.User;
import lombok.extern.slf4j.Slf4j;
import service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

@Slf4j
public class RPCClient {


    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);
        User userById = proxy.getUserById(10);
        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = proxy.insertUserId(user);
        log.info("user:{}",userById);
        log.info("插入成功,{}",integer);
    }
}
