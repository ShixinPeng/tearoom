package com.netty.lecture11;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 本demo 可以关注两点
 * 1.文件体积对#transferTo（）使用 有什么现实?
 * 2.java Nio是如何判断系统是否支持sendFile()的?
 *
 */
public class NioFileClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8999));
        socketChannel.configureBlocking(true);

        FileInputStream fileInputStream = new FileInputStream(OioFileClient.fileName);
        FileChannel fileChannel = fileInputStream.getChannel();

        long startTime = System.currentTimeMillis();
        // 这里有一个注意点，#transferTo 传输中数据尺寸的控制
        long transfer = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("文件大小：" + fileChannel.size() + " 用时：" + (System.currentTimeMillis() - startTime) + "ms");

        //2147480400
        //2147483647
        //16038131875 用时：9417ms



    }
}
