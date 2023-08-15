package server;

import common.RPCRequest;
import common.RPCResponse;
import common.User;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class RPCServer {
    public static void main(String[] args) throws IOException {
        log.info("开始启动RPC服务器...");
        UserServiceImpl userService = new UserServiceImpl();
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                    RPCRequest rpcRequest = (RPCRequest) inputStream.readObject();
                    Method method = userService.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
                    Object invoke = method.invoke(userService, rpcRequest.getParams());
                    outputStream.writeObject(RPCResponse.success(invoke));
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.warn("服务出错");
                }
            }).start();
        }
    }
}