package com.netty.lecture06;

import com.netty.protobuf.DataInfo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PbServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
        System.out.println("服务端接收：name="+msg.getName()+" phone="+msg.getPhone()+" age="+msg.getAge());
    }
}
