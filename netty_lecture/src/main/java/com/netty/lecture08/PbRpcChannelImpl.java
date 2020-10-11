package com.netty.lecture08;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;
import com.google.protobuf.RpcUtil;

import com.netty.protobuf.ProtobufService;

/**
 * @author shixinpeng
 * @description RPC基础实现，只满足远程接口的调用
 * @ClassName: RpcChannelImpl
 * @date 2020/10/11
 *
 */
public class PbRpcChannelImpl implements RpcChannel {

    @Override
    public void callMethod(Descriptors.MethodDescriptor method, RpcController controller, Message request, Message responsePrototype, RpcCallback<Message> done) {
        // 根据方法的描述，通过RpcController，发送request，根据需要响应的信息类型，返回响应信息
        System.out.println("RpcChannel=>通道开始执行方法："+method.getName());

        PbHelloServiceImpl helloService = new PbHelloServiceImpl();

        // 向下泛型
        RpcCallback<ProtobufService.HelloResponse> generalizeCallback = RpcUtil.specializeCallback(done);

        // 本地直接执行
        // helloService.search(controller,(ProtobufService.HelloRequest) request,generalizeCallback);

        // 执行远程调用


    }
}
