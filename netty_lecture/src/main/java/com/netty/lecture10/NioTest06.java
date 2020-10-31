package com.netty.lecture10;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * nio 的Scattering和Gathering
 * {@code Scattering}  分散
 * {@code Gathering} 聚合
 * 监听一个端口，读取数据，当满足拆分要求时进行回写到客户端
 * 类似模拟 [魔数|协议头|负载]协议的拆分，
 * mac中使用{@code nc $host $port} 进行连接测试
 */
public class NioTest06 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        socketChannel.bind(new InetSocketAddress(8999));
        // 定义一个完成的消息的长度为 2 + 3 + 4 = 9，只有当客户端发送完9个byte时才表示一个完整的消息被接收
        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel channel = socketChannel.accept();

        System.out.println("channel:" + channel.getRemoteAddress());


        while (true) {
            int readByteCount = 0;
            // 循环读取客户端内容，直到满足消息长度
            while (readByteCount < messageLength){
                long read = channel.read(buffers);
                readByteCount += read;
                System.out.println("readByteCount="+readByteCount);

                for (int i = 0; i < buffers.length; i++) {
                    System.out.println("buffer" + i + ": position=" + buffers[i].position() + " limit=" + buffers[i].limit());
                }
            }
            // 接收完整信息完毕;准备返回到客户端

            Arrays.stream(buffers).forEach(ByteBuffer::flip);

            int writeByteCount = 0;
            // 循环写入客户端内容，直到满足消息长度
            while (writeByteCount < messageLength){
                long write = channel.write(buffers);
                writeByteCount += write;
                System.out.println("writeByteCount="+writeByteCount);
            }
            // 清空缓存
            Arrays.stream(buffers).forEach(ByteBuffer::clear);

            System.out.println("messageLength="+messageLength+" readByteCount="+readByteCount + " writeByteCount="+writeByteCount);
        }
    }
}
