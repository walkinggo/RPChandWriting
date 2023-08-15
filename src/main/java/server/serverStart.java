package server;

import service.BlogService;
import service.UserService;

import java.util.HashMap;

public class serverStart {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvide serviceProvide = new ServiceProvide();
//        HashMap<String, Object> serviceProvide = new HashMap<>();
//        serviceProvide.put(userService.getClass().getName(),userService);
//        serviceProvide.put(blogService.getClass().getName(),blogService);
        serviceProvide.provideServiceInterface(userService);
        serviceProvide.provideServiceInterface(blogService);
        SimpleRPCRPCServer simpleRPCRPCServer = new SimpleRPCRPCServer(serviceProvide);
        simpleRPCRPCServer.start(8899);

    }
}
