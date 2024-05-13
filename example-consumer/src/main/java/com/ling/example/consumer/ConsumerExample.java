package com.ling.example.consumer;

import com.ling.lingRpc.RpcApplication;
import com.ling.lingRpc.config.RpcConfig;
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
    }
}
