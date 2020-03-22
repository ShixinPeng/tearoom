package com.netty.lecture04;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class MyIdleServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 时间触发
     * @param ctx 上下文
     * @param evt 时间对象
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

        String eventType = null;
        switch (idleStateEvent.state()){
            case READER_IDLE:
                eventType = "读空闲";
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                break;
        }

        System.out.println(ctx.channel().remoteAddress() + "超时事件： " + eventType);
        ctx.channel().close();
    }
}
