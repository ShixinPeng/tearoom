package com.netty.lecture11;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Nio版本的文件传输
 */
public class NioFileServer {

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(8999);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(address);

        while (true){

            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);
            System.out.println("客户端连接");

            ByteBuffer buffer = ByteBuffer.allocate(4096);
            long fileSize  = 0;
            while (true){
                buffer.clear();

                int read = socketChannel.read(buffer);
                System.out.println("接收："+fileSize);
                fileSize += read;
                while (read<0){
                    System.out.println("接收文件大小："+fileSize);
                    socketChannel.close();
                    break;
                }
            }
        }

    }
}
