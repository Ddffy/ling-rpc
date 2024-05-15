package com.ling.example.consumer;

import cn.hutool.http.HttpResponse;
import com.ling.example.common.model.User;
import com.ling.example.common.service.UserService;
import com.ling.lingRpc.model.RpcRequest;
import com.ling.lingRpc.model.RpcResponse;
import com.ling.lingRpc.serializer.JdkSerializer;
import com.ling.lingRpc.serializer.Serializer;
import com.ling.lingRpc.server.HttpServer;

import java.io.IOException;
import cn.hutool.http.HttpRequest;

/**
 *
 * 静态代理（测试用）
 * @author lingcode
 * @version 1.0
 * i
 */
public class UserServiceProxy implements UserService {

    public User getUser(User user) {
        // 指定序列化器
        final Serializer serializer = new JdkSerializer();

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            // 序列化（Java 对象 => 字节数组）
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 发送请求
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化（字节数组 => Java 对象）
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return (User) rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
