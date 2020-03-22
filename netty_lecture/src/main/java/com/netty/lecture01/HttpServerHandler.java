package com.netty.lecture01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author shixinpeng
 * @description http服务的handler
 * @ClassName: HttpServerHandler
 * @date 2020/3/22
 *
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 读取客户端的请求，并进行响应
     * @param channelHandlerContext
     * @param httpObject
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

        System.out.println("接收到请求 channelRead0 " + channelHandlerContext.name() + " " + httpObject.toString());

        if (httpObject instanceof HttpRequest) {
            // 判断类型
            ByteBuf content = Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plan");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            // 把响应写回客户端
            channelHandlerContext.writeAndFlush(response);
        }

    }
}
