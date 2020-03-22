package com.netty.lecture01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

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

        System.out.println("接收到请求 channelRead0 " + httpObject.toString());
        // 判断类型
        if (httpObject instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest)httpObject;
            System.out.println("请求方法名：" + httpRequest.method().name());
            URI uri = new URI(httpRequest.uri());
            System.out.println("请求path：" + uri.getPath());
            if (uri.getPath().equals("/hello")){

                ByteBuf content = Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8);
                DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plan");
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
                // 把响应写回客户端
                channelHandlerContext.writeAndFlush(response);
            }

        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel handlerAdded");
        super.handlerAdded(ctx);
    }
    /**
     * 连接活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel channelActive");
        super.channelActive(ctx);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel channelInactive");
        super.channelInactive(ctx);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel channelRegistered");
        super.channelRegistered(ctx);
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel handlerRemoved");
        super.handlerRemoved(ctx);
    }
}
