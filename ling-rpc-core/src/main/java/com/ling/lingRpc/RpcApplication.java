package com.ling.lingRpc;

import com.ling.lingRpc.config.RegistryConfig;
import com.ling.lingRpc.config.RpcConfig;
import com.ling.lingRpc.constant.RpcConstant;
import com.ling.lingRpc.registry.Registry;
import com.ling.lingRpc.registry.RegistryFactory;
import com.ling.lingRpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;
/**
 * RPC框架应用
 * 懒加载，双检索单例设计模式实现
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

    /**
     * 框架初始化，传入自定义配置
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig){
        rpcConfig=newRpcConfig;
      log.info("rpc init, config = {}",newRpcConfig.toString());
        // 注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        // 创建并注册 Shutdown Hook，JVM 退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    /**
     * 初始化
     */
    public static void init(){
        RpcConfig newRpcConfig ;

      try {
          newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
      }catch (Exception e){
          //失败使用默认值
          newRpcConfig = new RpcConfig();
      }

      init(newRpcConfig);
    }

    /**
     * 获取配置
     * @return
     */
   public static RpcConfig getRpcConfig(){
            if(rpcConfig==null){
                synchronized (RpcApplication.class) {
                    if (rpcConfig == null){
                        init();
                }
                }
            }
            return rpcConfig;
   }


}


