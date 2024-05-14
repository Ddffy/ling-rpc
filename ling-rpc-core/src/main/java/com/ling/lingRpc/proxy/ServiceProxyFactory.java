package com.ling.lingRpc.proxy;

import com.ling.lingRpc.RpcApplication;

import java.lang.reflect.Proxy;

/**
 * 使用了工厂设计模式
 * 服务代理工厂
 * @author lingcode
 * @version 1.0
 * i
 */
public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static  <T> T getProxy(Class<T> serviceClass){
        if(RpcApplication.getRpcConfig().isMock()){
            return getMockProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }

    private static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy());
    }
}
