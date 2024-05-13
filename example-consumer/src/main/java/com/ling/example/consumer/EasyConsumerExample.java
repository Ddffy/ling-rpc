package com.ling.example.consumer;

import com.ling.example.common.model.User;
import com.ling.example.common.service.UserService;
import com.ling.lingRpc.proxy.ServiceProxyFactory;

/**
 * @author lingcode
 * @version 1.0
 * i
 */
public class EasyConsumerExample {
    public static void main(String[] args) {



        UserService userService= ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("ling");
        User newUser = userService.getUser(user);
        if(newUser !=null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("user==null");
        }
    }
}
