package com.netty.lecture08;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;

import com.netty.protobuf.ProtobufService;

import java.io.IOException;


/**
 * 使用pb编译器生成的代码进行rpc服务调用
 */
public class PbHelloClient {

    public static void main(String[] args) {

        callRemoteMethod();
//        printMethodDescriptor();
    }

    /**
     * 获取方法的描述 简单了解PB如果标记service
     */
    private static void printMethodDescriptor() {
        // service类
        PbHelloService helloService = new PbHelloService();
        // 获取service描述
        Descriptors.ServiceDescriptor descriptorForType = helloService.getDescriptorForType();

        // Describes a service
        DescriptorProtos.ServiceDescriptorProto descriptorProto = descriptorForType.toProto();
        System.out.println("————————");
        System.out.println("服务：" + descriptorProto);
        System.out.println("————————");
        System.out.println("getFullName=" + descriptorForType.getFullName());
        System.out.println("getName=" + descriptorForType.getName());
        System.out.println("getFile=" + descriptorForType.getFile());
        System.out.println("getMethods=" + descriptorForType.getMethods());
        System.out.println("getOptions=" + descriptorForType.getOptions());
        System.out.println("————————");
        descriptorForType.getMethods().forEach(action -> {
            System.out.println("方法：" + action.toProto());
        });
        // 获取指定方法描述
        Descriptors.MethodDescriptor method = descriptorForType.findMethodByName("Search");
        System.out.println("————————");
        System.out.println("获取指定方法描述=" + method.toProto());
    }

    /**
     * 调用远程的service实现
     */
    private static void callRemoteMethod() {
        PbHelloService helloService = new PbHelloService();
        // 按正常java中service的调用，需要获取service实例，然后调用service中的方法
        // 连接执行service的channel
        RpcChannel rpcChannel = new PbRpcChannelImpl();

        // 客户端stub
        ProtobufService.HelloService.Stub stub = ProtobufService.HelloService.newStub(rpcChannel);
        /*
         * 远程方法调用的参数
         * com.google.protobuf.Descriptors.MethodDescriptor method,方法描述
         * com.google.protobuf.RpcController controller,rpc实现与service之间的中间层，既可以自己去实现rpc也可以负责处理rpc调用层级的异常
         * com.google.protobuf.Message request,请求信息
         * com.google.protobuf.RpcCallback<com.google.protobuf.Message> done 回调信息
         * */
        // 参数1：获取service中待调用方法描述
        Descriptors.ServiceDescriptor serviceDescriptor = helloService.getDescriptorForType();
        Descriptors.MethodDescriptor methodDescriptor = serviceDescriptor.findMethodByName("Search");
        // 参数2：rpc协助控制器
        RpcController rpcController = new PbRpcControllerImpl();

        // 参数3：请求信息
        ProtobufService.HelloRequest helloRequest = ProtobufService.HelloRequest.newBuilder().setArg("hello，2020").build();
        // 参数4：回调
        RpcCallback rpcCallback = new PbRpcCallbackImpl();

        stub.callMethod(methodDescriptor, rpcController, helloRequest, rpcCallback);

        try {
            int read = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }
}
