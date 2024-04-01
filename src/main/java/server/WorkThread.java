package server;

import common.RPCRequest;
import common.RPCResponse;
import common.builder.RPCResponseBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class WorkThread implements Runnable {
    private Socket socket;
    private ServiceProvide serviceProvide;


    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            RPCRequest request = (RPCRequest) objectInputStream.readObject();
            RPCResponse response = getResponse(request);
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private RPCResponse getResponse(RPCRequest request) {
        try {
            String interfaceName = request.getInterfaceName();
            Object service = serviceProvide.getService(interfaceName);
            Method method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invoke = method.invoke(service, request.getParams());
            return RPCResponseBuilder.success(invoke);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            log.warn("没有找到对应的服务");
            return RPCResponseBuilder.fail();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.warn("方法参数异常");
            return RPCResponseBuilder.fail();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            log.warn("方法调用异常");
            return RPCResponseBuilder.fail();
        }
    }
}
