package com.netty.lecture01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author shixinpeng
 * @description Netty http服务
 * @ClassName: HttpServer
 * @date 2020/3/22
 *
 */
public class TestHttpServer {

    public static void main(String[] args) throws Exception {
        // 使用三步走：启动服务；注册器；处理器
        // 都为死循环
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 配置引导服务
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(new HttpServerInitializer());
            // 绑定端口；
            ChannelFuture channelFuture = bootstrap.bind(8999).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
