package com.ling.lingRpc.server;

import com.ling.lingRpc.model.RpcRequest;
import com.ling.lingRpc.model.RpcResponse;
import com.ling.lingRpc.registry.LocalRegistry;
import com.ling.lingRpc.serializer.JdkSerializer;
import com.ling.lingRpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 *
 * Http请求处理
 * @author lingcode
 * @version 1.0
 * i
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        //指定序列化器
        final Serializer serializer=new JdkSerializer();

        //输出日志
        System.out.println("Received request: "+request.method()+" "+request.uri());

        request.bodyHandler(body->{
           byte[] bytes=body.getBytes();
            RpcRequest rpcRequest=null;


            try {
                rpcRequest=serializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RpcResponse rpcResponse = new RpcResponse();
            if(rpcRequest==null){
                rpcResponse.setMessage("rpcRequest is null");
               doResponse(request,rpcResponse,serializer);
               return;
            }

            try {
                //用反射获取方法调用
                Class<?> implClass= LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.getDeclaredConstructor().newInstance(), rpcRequest.getArgs());
                //封装响应结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("success");

            } catch (Exception e) {
              e.printStackTrace();
              rpcResponse.setMessage(e.getMessage());
              rpcResponse.setException(e);
            }
            doResponse(request,rpcResponse,serializer);
        });




    }

    /**
     * 响应
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    private void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response().putHeader("content-type","application/json");

        try {
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
          e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }

    }


}
