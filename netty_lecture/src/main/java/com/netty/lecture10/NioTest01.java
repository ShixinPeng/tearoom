package com.netty.lecture10;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.util.ArrayList;

/**
 * @author shixinpeng
 * @description 简单的使用java nio包中的 buffer
 * @ClassName: NioTest01
 * @date 2020/10/19
 *
 */
public class NioTest01 {

    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < byteBuffer.capacity(); i++) {

            byteBuffer.put((byte) secureRandom.nextInt(10));
        }

        byte[] array = byteBuffer.array();
        System.out.println("\n");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println("\n");
        byteBuffer.flip();
        // position为将要写入或者读取的位置
        //byteBuffer.position(2);
        // limit为第一个不可以写入或者读取的位置（最后一个可写入或读取的下一个位置）
        //byteBuffer.limit(3);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            // 不调用flip()翻转方法，不能从写入变为读取 java.nio.BufferUnderflowException
            byte b = byteBuffer.get();
            System.out.print(b);
//            byteBuffer.flip();
        }
        System.out.println("\n");

    }
}
