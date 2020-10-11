package com.netty.lecture08;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

import com.netty.protobuf.ProtobufService;

public class PbHelloService extends ProtobufService.HelloService {

    @Override
    public void search(RpcController controller, ProtobufService.HelloRequest request, RpcCallback<ProtobufService.HelloResponse> done) {

    }

}
