package com.pengshixin.mqlecture.sayHi;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class LiHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到消息");
        ByteBuf buf = (ByteBuf) msg;
        int length = buf.readableBytes();
        String s = buf.getCharSequence(0, length, Charset.defaultCharset()).toString();

        System.out.println("内容："+s);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        System.out.println("channelActive " + ctx.channel().toString());
    }
}
