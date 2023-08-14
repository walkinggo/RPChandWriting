package client;

import common.User;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

@Slf4j
public class RPCClient {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8899);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeInt(new Random().nextInt());
            objectOutputStream.flush();
            User user = (User) objectInputStream.readObject();
//            System.out.println("user = " + user);
            log.info("user = " + user);
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            log.warn("客户端启动失败");
        }
    }
}
