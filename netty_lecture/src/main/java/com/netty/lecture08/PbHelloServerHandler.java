package com.netty.lecture08;

import com.netty.protobuf.ProtobufService;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PbHelloServerHandler extends SimpleChannelInboundHandler<ProtobufService.RpcWrapper> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtobufService.RpcWrapper msg) throws Exception {
        System.out.println("服务端收到service调用请求："+msg.getMethod());
    }
}
