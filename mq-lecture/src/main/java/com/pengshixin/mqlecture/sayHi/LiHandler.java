package com.pengshixin.mqlecture.sayHi;

import com.pengshixin.mqlecture.sayHi.serializer.TalkMessageJavaSerializer;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class LiHandler extends ChannelInboundHandlerAdapter {

    /**
     * 编解码器
     */
    private TalkMessageSerializable talkMessageSerializable;

    {
        this.talkMessageSerializable = new TalkMessageJavaSerializer();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("接收到消息");
        TalkMessage talkMessage = talkMessageSerializable.decode(byteBuf);
        System.out.println("内容："+talkMessage.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        System.out.println("channelActive " + ctx.channel().toString());
    }
}
