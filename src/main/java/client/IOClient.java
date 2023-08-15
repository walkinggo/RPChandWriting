package client;

import common.RPCRequest;
import common.RPCResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

@Slf4j
public class IOClient {
    public static RPCResponse sendRequest(String host, int port, RPCRequest request){
        RPCResponse response = null;
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            log.info("{}",request);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            response = (RPCResponse) objectInputStream.readObject();
            return response;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            log.warn("定义了未知的port");
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("出现了IO异常");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.warn("不存在该类型的类");
        }
        return response;
    }
}
