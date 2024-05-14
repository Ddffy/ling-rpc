package com.ling.example.provider;

import com.ling.example.common.service.UserService;
import com.ling.lingRpc.RpcApplication;
import com.ling.lingRpc.config.RegistryConfig;
import com.ling.lingRpc.config.RpcConfig;
import com.ling.lingRpc.model.ServiceMetaInfo;
import com.ling.lingRpc.registry.LocalRegistry;
import com.ling.lingRpc.registry.Registry;
import com.ling.lingRpc.registry.RegistryFactory;
import com.ling.lingRpc.server.HttpServer;
import com.ling.lingRpc.server.VertxHttpServer;

/**
 * 服务提供者
 * @author lingcode
 * @version 1.0
 * i
 */
public class ProviderExample {
    public static void main(String[] args) {
// RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
                //(rpcConfig.getServerHost() + ":" + rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
