package client;

import common.Blog;
import common.User;
import lombok.extern.slf4j.Slf4j;
import service.BlogService;
import service.UserService;

@Slf4j
public class RPCClient {


    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);
        User userById = proxy.getUserById(10);
        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = proxy.insertUserId(user);
        log.info("user:{}", userById);
        log.info("插入成功,{}", integer);

        // 客户中添加新的测试用例
        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(1000);
        log.info("blog业务测试结果：{}",blogById);

    }
}
