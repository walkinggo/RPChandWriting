package server;

import lombok.AllArgsConstructor;
import zookeeper.ServiceRegister;
import zookeeper.ZkServiceRegister;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ServiceProvide {
    private Map<String,Object> interfaceProvide;
    private ServiceRegister serviceRegister;
    private String host;
    private int port;

    public ServiceProvide(String host,int port) {
        this.interfaceProvide = new HashMap<>();
        this.host = host;
        this.port = port;
        this.serviceRegister= new ZkServiceRegister();
    }

    public void provideServiceInterface(Object service){
//        String serviceName = service.getClass().getName();
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> clazz : interfaces) {
            interfaceProvide.put(clazz.getName(),service);
            serviceRegister.register(clazz.getName(), new InetSocketAddress(host,port));
        }
    }

    public Object getService(String interfaceName){
        return interfaceProvide.get(interfaceName);
    }

}
