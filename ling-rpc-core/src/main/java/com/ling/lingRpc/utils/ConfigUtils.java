package com.ling.lingRpc.utils;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
/**
 * @author lingcode
 * @version 1.0
 * i
 */
public class ConfigUtils {

    /**
     * 加载配置对象
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public  static <T> T loadConfig(Class<T> tClass,String prefix){
        return loadConfig(tClass,prefix," ");

    }

    /**
     * 加载带环境的配置对象
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     * @param <T>
     */
    public  static <T> T loadConfig(Class<T> tClass,String prefix,String environment){

        StringBuilder confIgFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)){
            confIgFileBuilder.append("-").append(environment);
        }
        confIgFileBuilder.append(".properties");
        Props props = new Props(confIgFileBuilder.toString());
        return props.toBean(tClass,prefix);

    }
}
