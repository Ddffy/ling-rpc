package com.ling.lingRpc.config;

import lombok.Data;

/**
 *
 * Rpc框架配置
 * @author lingcode
 * @version 1.0
 * i
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
   private String name="ling-rpc";

    /**
     * 版本号
     */
   private String version="1.0";

    /**
     * 服务器地址
     */
   private String serverHost="localhost";

    /**
     * 服务器端口
     */
   private Integer serverPort=8080;

}
