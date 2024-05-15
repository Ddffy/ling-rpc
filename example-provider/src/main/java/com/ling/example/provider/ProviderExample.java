package com.ling.example.provider;

import com.ling.example.common.service.UserService;
import com.ling.lingRpc.RpcApplication;
import com.ling.lingRpc.bootstrap.ProviderBootstrap;
import com.ling.lingRpc.config.RegistryConfig;
import com.ling.lingRpc.config.RpcConfig;
import com.ling.lingRpc.model.ServiceMetaInfo;
import com.ling.lingRpc.model.ServiceRegisterInfo;
import com.ling.lingRpc.registry.LocalRegistry;
import com.ling.lingRpc.registry.Registry;
import com.ling.lingRpc.registry.RegistryFactory;
import com.ling.lingRpc.server.HttpServer;
import com.ling.lingRpc.server.VertxHttpServer;
import com.ling.lingRpc.server.tcp.VertxTcpServer;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务提供者
 * @author lingcode
 * @version 1.0
 * i
 */
public class ProviderExample {

    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<UserService> serviceRegisterInfo = new ServiceRegisterInfo<>(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}