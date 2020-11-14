package com.netty.lecture11;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用传统的IO操作进行文件传输 计算传统的IO操作，发送大文件的耗时中位数
 */
public class OioFileServer {

    public static void main(String[] args) throws IOException {
        // 接收来自客户端的文件字节序列，计算总长度后丢弃
        ServerSocket serverSocket = new ServerSocket();

        serverSocket.bind(new InetSocketAddress(8999));

        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println("客户端连接");
            InputStream inputStream = socket.getInputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            try {
                byte[] bytes = new byte[4096];
                long fileSize = 0;
                while (true) {
                    int read = dataInputStream.read(bytes, 0, bytes.length);
                    fileSize += read;
                    if (read < 0) {
                        //       g      M     kb
                        long g = 1024 * 1024 * 1024;
                        long m = 1024 * 1024;
                        long kb = 1024;

                        System.out.println("接收文件大小:" + String.format("%d G %d M %d Kb %d b", fileSize / g, fileSize % g / m, fileSize % m / kb, fileSize % kb));
                        dataInputStream.close();
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }
}
