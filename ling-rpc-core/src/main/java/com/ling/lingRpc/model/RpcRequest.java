package com.ling.lingRpc.model;

import com.ling.lingRpc.constant.RpcConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RPC请求
 * @author lingcode
 * @version 1.0
 * i
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    /**
     * 服务名称
      */
    private String serviceName;


    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 版本号
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;

    /**
     * 参数类型列表
     */
    private  Class<?>[] parameterTypes;


    /**
     * 参数列表
     */
    private Object[] args;



}
