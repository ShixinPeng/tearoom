package com.netty.lecture08;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;

import com.netty.protobuf.DataInfo;
import com.netty.protobuf.ProtobufService;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;


/**
 * 使用pb编译器生成的代码进行rpc服务调用
 */
public class PbHelloClient {

    private RpcChannel nettyRpcChannel;

    public static void main(String[] args) throws InterruptedException {
//        callLocalMethod();
        startChannel();
        callRemoteMethod();
//        printMethodDescriptor();
    }

    /**
     * 开启客户端的channel的连接 提供给PB进行服务调用，使用包装Message的方式进行数据传递
     */
    public static void startChannel() throws InterruptedException {

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    // 添加对应的handler
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder(ProtobufService.RpcWrapper.getDefaultInstance()));
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                    pipeline.addLast(new SimpleChannelInboundHandler<ProtobufService.RpcWrapper>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, ProtobufService.RpcWrapper msg) throws Exception {

                        }

                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            System.out.println("客户端已连接");

                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8999).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    /**
     * 获取方法的描述 简单了解PB如果标记service
     */
    private static void printMethodDescriptor() {
        // service类
        PbHelloServiceImpl helloService = new PbHelloServiceImpl();
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


        PbHelloServiceImpl helloService = new PbHelloServiceImpl();
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
        RpcCallback rpcCallback = new RpcCallback<ProtobufService.HelloResponse>() {
            @Override
            public void run(ProtobufService.HelloResponse parameter) {
                System.out.println("Callback:收到返回=>" + parameter.getResult());
            }
        };

        stub.callMethod(methodDescriptor, rpcController, helloRequest, rpcCallback);

        try {
            int read = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    /**
     * 调用本地方法
     */
    private static void callLocalMethod() {

        ProtobufService.HelloService helloService = new PbHelloServiceImpl();

        RpcController rpcController = new PbRpcControllerImpl();

        // 参数：请求信息
        ProtobufService.HelloRequest helloRequest = ProtobufService.HelloRequest.newBuilder().setArg("hello，2020").build();
        // 参数：回调
        RpcCallback rpcCallback = new RpcCallback<ProtobufService.HelloResponse>() {
            @Override
            public void run(ProtobufService.HelloResponse parameter) {
                System.out.println("Callback:收到返回=>" + parameter.getResult());
            }
        };

        helloService.search(rpcController, helloRequest, rpcCallback);
        try {
            int read = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
