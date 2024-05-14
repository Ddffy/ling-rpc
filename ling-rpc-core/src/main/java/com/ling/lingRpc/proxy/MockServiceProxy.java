package com.ling.lingRpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * Mock服务代理（动态代理）
 * @author lingcode
 * @version 1.0
 * i
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {


    /**
     *调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        log.info("method invoke {}",method.getName());
        return  getDefaultObject(returnType);
    }

    /**
     *
     * @param returnType
     * @return
     */
    private Object getDefaultObject(Class<?> returnType) {

        if (returnType.isPrimitive()) {
            if (returnType == boolean.class) {
                return false;

            } else if (returnType == short.class) {
                return (short) 0;
            } else if (returnType == int.class) {
                return 0;
            } else if (returnType == long.class) {
                return 0L;
            }
        }
            return null;
        }


}
