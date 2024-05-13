package com.ling.example.consumer;

import cn.hutool.http.HttpResponse;
import com.ling.example.common.model.User;
import com.ling.example.common.service.UserService;
import com.ling.lingRpc.model.RpcRequest;
import com.ling.lingRpc.model.RpcResponse;
import com.ling.lingRpc.serializer.JdkSerializer;
import com.ling.lingRpc.server.HttpServer;

import java.io.IOException;
import cn.hutool.http.HttpRequest;

/**
 *
 * 静态代理
 * @author lingcode
 * @version 1.0
 * i
 */
public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        /**
         * 指定序列化器
         */
        JdkSerializer serializer = new JdkSerializer();

        //构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();

        try {

            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;

            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                      .body(bodyBytes)
                      .execute()){
              result=httpResponse.bodyBytes();
          };
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
           return (User) rpcResponse.getData();


        } catch (IOException e) {
            e.printStackTrace();
        }
         return null;
    }
}
