package server;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ServiceProvide {
    private Map<String,Object> interfaceProvide;

    public ServiceProvide() {
        this.interfaceProvide = new HashMap<>();
    }

    public void provideServiceInterface(Object service){
//        String serviceName = service.getClass().getName();
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> clazz : interfaces) {
            interfaceProvide.put(clazz.getName(),service);
        }
    }

    public Object getService(String interfaceName){
        return interfaceProvide.get(interfaceName);
    }

}
