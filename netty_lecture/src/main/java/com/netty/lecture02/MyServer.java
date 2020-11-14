package com.netty.lecture02;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author shixinpeng
 * @description socket服务器
 * @ClassName: MyServer
 * @date 2020/3/22
 *
 */
public class MyServer {
    public static void main(String[] args) throws Exception {

        /*
        EventLoopGroup 是EventExecutorGroup 的子接口
        EventExecutorGroup
        负责 1：通过next(),提供给EventExecutor可以执行的方法
        负责 2：循环生命周期的管理

        EventLoop 管理事件循环
        EventExecutor 管理循环中事件的执行

        实现类：NioEventLoopGroup 继承自  MultithreadEventLoopGroup 被用作Nio中的Selector基础使用
        MultithreadEventLoopGroup 继承了 MultithreadEventExecutorGroup抽象类  实现了EventLoopGroup接口
        使用多线程完成任务

         */

        // 主事件循环组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 工作循环组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(new MyServerHandlerInitIal());
            ChannelFuture channelFuture = bootstrap.bind(8999).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
