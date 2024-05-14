package com.ling.lingRpc.model;

import cn.hutool.core.util.StrUtil;
import com.ling.lingRpc.constant.RpcConstant;
import lombok.Data;

/**
 * 服务注册用
 * @author lingcode
 * @version 1.0
 * i
 */
@Data
public class ServiceMetaInfo {

    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 服务版本
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;

    /**
     * 服务地址
     */
    private String  serviceHost;

    /**
     * 服务端口
     */
    private int servicePort;


    /**
     * 服务分组
     */
    private String serviceGroup="default";

    /**
     * 获取服务键名
     *
     * @return
     */
    public String getServiceKey() {
        // 后续可扩展服务分组
//        return String.format("%s:%s:%s", serviceName, serviceVersion, serviceGroup);
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * 获取服务注册节点键名
     *
     * @return
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s:%s", getServiceKey(), serviceHost, servicePort);
    }

    /**
     * 获取完整服务地址
     *
     * @return
     */
    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }
}