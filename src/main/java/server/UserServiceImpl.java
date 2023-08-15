package server;

import common.User;
import service.UserService;

import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;


public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(Thread.currentThread().getName());

    @Override
    public User getUserById(Integer id) {
        logger.info("客户端查询了 " + id + " 用户");
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString()).id(id).sex(random.nextBoolean()).build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("数据插入成功：" + user);
        return 1;
    }
}
