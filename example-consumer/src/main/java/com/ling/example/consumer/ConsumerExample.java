package com.ling.example.consumer;

import com.ling.example.common.model.User;
import com.ling.example.common.service.UserService;
import com.ling.lingRpc.RpcApplication;
import com.ling.lingRpc.config.RpcConfig;
import com.ling.lingRpc.proxy.ServiceProxyFactory;
import com.ling.lingRpc.utils.ConfigUtils;

/**
 * @author lingcode
 * @version 1.0
 * i
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class,"rpc");

        System.out.println(rpc);
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
