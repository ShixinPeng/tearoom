package com.netty.lecture10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author shixinpeng
 * @description 了解nio中的另外一个概念：channel
 * javaDoc中，channel是io操作的连接桥梁
 * 分别从文件中读取数据和写入数据到文件
 * @ClassName: NioTest02
 * @date 2020/10/21
 *
 */
public class NioTest02 {
    public static void main(String[] args) throws IOException {
       // Channel A nexus for I/O operations

       channelRead();
       channelWrite();
    }

    public static void channelRead() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("fileChannelRead.txt");

        // 获取channel
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        // 从channel中读取数据
        fileChannel.read(byteBuffer);

        byteBuffer.flip();
        // The number of elements remaining in this buffer
        while (byteBuffer.remaining()>0){
            byte b = byteBuffer.get();
            System.out.println("Character:"+(char)b);

        }

        byteBuffer.rewind();

        byte[] bytes = byteBuffer.array();

        String content = new String(bytes, StandardCharsets.UTF_8);

        System.out.println("content:"+content);
        fileChannel.close();
    }

    public static void channelWrite() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("fileChannelWrite.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        byte[] bytes = "嗨 hello everyone".getBytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byteBuffer.put(bytes);
        // 要进行模式转换！
        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}
