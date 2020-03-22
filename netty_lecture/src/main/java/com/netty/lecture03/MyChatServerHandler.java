package com.netty.lecture03;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
/**
 * @author shixinpeng
 * @description 广播中心服务
 * @ClassName: MyChatServerHandler
 * @date 2020/3/22
 *
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup  channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch -> {
            if (channel != ch){
                ch.writeAndFlush(ctx.channel().remoteAddress() + " msg:" + s + " \n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端上线：" + channel.remoteAddress());
        // group 中的每一个channel执行flush
        channelGroup.writeAndFlush("服务器：" + channel.remoteAddress() + " 上线\n");

        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端下线：" + channel.remoteAddress());
        // group 中的每一个channel执行flush
        channelGroup.writeAndFlush("服务器：" + channel.remoteAddress() + " 下线\n");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
