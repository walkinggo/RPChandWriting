package server;

import common.User;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class RPCServer {

    public static void main(String[] args) throws IOException {
        log.info("开始启动RPC服务器...");
        UserServiceImpl userService = new UserServiceImpl();
        ServerSocket serverSocket = new ServerSocket(8899);
        while(true){
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                ObjectInputStream inputStream = null;
                try {
                    inputStream = new ObjectInputStream(socket.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int id = 0;
                try {
                    id = inputStream.readInt();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                User userById = userService.getUserById(id);
                try {
                    outputStream.writeObject(userById);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.flush();
                    log.info("查询内容{}",userById);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
