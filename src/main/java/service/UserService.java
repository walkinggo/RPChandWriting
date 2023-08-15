package service;

import common.User;

public interface UserService {
    User getUserById(Integer id);
    Integer insertUserId(User user);
}
