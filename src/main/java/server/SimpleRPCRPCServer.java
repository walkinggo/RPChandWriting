package server;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class SimpleRPCRPCServer implements RPCServer{

    private ServiceProvide serviceProvide;



    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            log.info("服务开始启动于端口：{}",port);
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket,serviceProvide)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("服务启动失败");
        }
    }

    @Override
    public void stop() {

    }
}
