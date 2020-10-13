package com.netty.lecture08;

import com.google.protobuf.ByteString;

import com.netty.protobuf.ProtobufService;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PbHelloServerHandler extends SimpleChannelInboundHandler<ProtobufService.RpcWrapper> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtobufService.RpcWrapper msg) throws Exception {
        System.out.println(String.format("服务端收到调用%s的%s方法",msg.getService(),msg.getMethod()));
        String msgService = msg.getService();
        // 通过spring的bean管理，或反射的手段，找到service的实现类
        if (msgService.contentEquals(PbHelloServiceRemoteImpl.getDescriptor().getName())){
            PbHelloServiceRemoteImpl helloServiceRemote = new PbHelloServiceRemoteImpl();

            helloServiceRemote.search(new PbRpcControllerImpl(), ProtobufService.HelloRequest.parseFrom(msg.getRequest()), helloResponse -> {
                // 远程实现的回调 同样使用封装对象
                ProtobufService.RpcWrapper rpcWrapper = msg.toBuilder().setResponse(ByteString.copyFrom(helloResponse.toByteArray())).build();
                ctx.writeAndFlush(rpcWrapper);
            });
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端连接");
    }
}
