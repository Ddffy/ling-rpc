package com.ling.lingRpc.registry;

import com.ling.lingRpc.serializer.JdkSerializer;
import com.ling.lingRpc.serializer.Serializer;
import com.ling.lingRpc.spi.SpiLoader;

/**
 *
 * 注册器工厂
 * @author lingcode
 * @version 1.0
 * i
 */
public class RegistryFactory {

    /**
     * 序列化器工厂
     */
        static {
                    SpiLoader.load(Registry.class);
        }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class,key);
    }

}
