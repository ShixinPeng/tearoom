package com.pengshixin.mqlecture.sayHi;

import com.pengshixin.mqlecture.sayHi.serializer.TalkMessageJavaSerializer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class ZhangHander extends ChannelInboundHandlerAdapter {

    /**
     * 编解码器
     */
    private TalkMessageSerializable talkMessageSerializable;

    {
        this.talkMessageSerializable = new TalkMessageJavaSerializer();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        System.out.println(msg);
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        ctx.channel().writeAndFlush(talkMessageSerializable.encode(TalkMessageEnum.Q1.getMessage()));

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

//        System.out.println("channelActive " + ctx.name());

    }
}
