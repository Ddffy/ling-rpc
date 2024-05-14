package com.ling.lingRpc.config;

import com.ling.lingRpc.registry.RegistryKeys;
import lombok.Data;

/**
 * @author lingcode
 * @version 1.0
 * i
 */
@Data
public class RegistryConfig {

    /**
     * 注册中心类别
     */
    private String registry = RegistryKeys.ETCD;

    /**
     * 注册中心
     */
    private String address="http://localhost:2181";

    private String username;


    private String password;


    private Long timeout=10000L;
}
