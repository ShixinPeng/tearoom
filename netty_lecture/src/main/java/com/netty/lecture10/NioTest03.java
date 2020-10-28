package com.netty.lecture10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NioTest03 从一个文件中读取写入另外一个文件中
 */
public class NioTest03 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {

            byteBuffer.clear();
            // position 对buffer读取的影响
//            byteBuffer.position(256);
            int read = inputStreamChannel.read(byteBuffer);
            System.out.println("read-length="+read);
            if (-1==read){
                break;
            }
            // 翻转写入
            byteBuffer.flip();

            outputStreamChannel.write(byteBuffer);
        }

        inputStreamChannel.close();
        outputStreamChannel.close();
    }
}
