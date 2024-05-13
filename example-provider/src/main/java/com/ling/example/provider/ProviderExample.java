package com.ling.example.provider;

import com.ling.example.common.service.UserService;
import com.ling.lingRpc.RpcApplication;
import com.ling.lingRpc.registry.LocalRegistry;
import com.ling.lingRpc.server.VertxHttpServer;

/**
 * @author lingcode
 * @version 1.0
 * i
 */
public class ProviderExample {
    public static void main(String[] args) {
        //框架初始化
        RpcApplication.init();
        //注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
         //启动服务
        VertxHttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());

    }
}
