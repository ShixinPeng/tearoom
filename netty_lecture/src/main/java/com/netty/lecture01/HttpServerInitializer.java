package com.netty.lecture01;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author shixinpeng
 * @description 初始化器 channel 注入
 * @ClassName: HttpServerInitializer
 * @date 2020/3/22
 *
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();
        // http的编解码组合组件
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 增加自定义的handler
        pipeline.addLast( "httpServerHandler", new HttpServerHandler());

    }
}
