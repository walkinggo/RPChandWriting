package server;

import service.BlogService;
import service.BlogServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvide serviceProvide = new ServiceProvide("127.0.0.1",8899);
        serviceProvide.provideServiceInterface(userService);
        serviceProvide.provideServiceInterface(blogService);
        RPCServer RPCServer = new NettyRPCServer(serviceProvide);
        RPCServer.start(8899);
    }
}
