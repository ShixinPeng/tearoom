package com.netty.lecture11;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 使用传统IO的方式进行文件的发送
 */
public class OioFileClient {

    // 发送完毕 ，文件大小：16038131874 用时：87 333ms
    public static String fileName = "/Users/shixinpeng/Downloads/lzt/lzyt.mkv";

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8999));

        OutputStream outputStream = socket.getOutputStream();

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        FileInputStream fileInputStream = new FileInputStream(fileName);


        long startTime = System.currentTimeMillis();
        long fileSize = 0;
        while (true) {
            byte[] buffer = new byte[4096];

            int read = fileInputStream.read(buffer, 0, buffer.length);

            fileSize += read;

            if (read < 0) {

                System.out.println("发送完毕 ，文件大小：" + fileSize + " 用时：" + (System.currentTimeMillis() - startTime) + "ms");
                fileInputStream.close();
                dataOutputStream.close();
                break;
            }
            dataOutputStream.write(buffer);
        }
        socket.close();
    }
}
