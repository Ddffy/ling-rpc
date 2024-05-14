package com.ling.example.provider;

import com.ling.example.common.service.UserService;
import com.ling.lingRpc.registry.LocalRegistry;
import com.ling.lingRpc.server.VertxHttpServer;

/**
 * @author lingcode
 * @version 1.0
 * i
 */
public class EasyProviderExample {
    public static void main(String[] args) {

        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        //启动服务
        VertxHttpServer server = new VertxHttpServer();
        //server.doStart(8080);
    }
}
