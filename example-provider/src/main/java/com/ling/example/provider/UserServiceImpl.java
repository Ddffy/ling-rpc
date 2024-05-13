package com.ling.example.provider;
import com.ling.example.common.model.User;
import com.ling.example.common.service.UserService;
/**
 * @author lingcode
 * @version 1.0
 * i
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名："+user.getName());
        return user;
    }
}
