package com.netty.lecture08;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

import com.netty.protobuf.ProtobufService;

public class PbHelloService extends ProtobufService.HelloService {

    @Override
    public void search(RpcController controller, ProtobufService.HelloRequest request, RpcCallback<ProtobufService.HelloResponse> done) {
        // 实现PB定义的方法
        System.out.println("HelloService=>search方法执行");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String arg = request.getArg();

        done.run(ProtobufService.HelloResponse.newBuilder().setResult(arg+" => 不忘初心，方得始终。").build());


    }

}
