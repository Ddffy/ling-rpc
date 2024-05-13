package com.ling.lingRpc;

import com.ling.lingRpc.config.RpcConfig;
import lombok.extern.slf4j.Slf4j;
/**
 * RPC框架应用
 * 利用双检索单例设计模式实现
 * 获取配置，存储配置
 * @author lingcode
 * @version 1.0
 * i
 */
@Slf4j
public class RpcApplication {
    /**
     * 线程安全
     */
    private  static  volatile RpcConfig rpcConfig;


    public static void init(RpcConfig newRpcConfig){
        rpcConfig=newRpcConfig;
      log.info("rpc init, config ={}",newRpcConfig.toString());
    }
}
