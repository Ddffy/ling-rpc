package com.ling.lingRpc.server;

import io.vertx.core.Vertx;

/**
 * @author lingcode
 * @version 1.0
 * i
 */
public class VertxHttpServer implements HttpServer{
    /**
     * 启动服务器
     * @param port
     */
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
      //创建http服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
//        server.requestHandler(request ->{
//            System.out.println("Receive request: "+ request.method()+" "+request.uri());
//
//            request.response()
//                    .putHeader("content-type","text/plain")
//                    .end("Hello from Vert.x HTTP server!");
//        } );
        /**
         * 监听端口处理请求
         */
        server.requestHandler(new HttpServerHandler());
        server.listen(port,result->{
            if(result.succeeded()){
                    System.out.println("Server is now listening on port"+port);
            }else {
                System.err.println("Failed to start server: "+ result.cause());
            }
        });

    }
}
