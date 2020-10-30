package com.netty.lecture10;

import java.nio.ByteBuffer;

/**
 * ByteBuffer读取多种数据类型
 */
public class NioTest04 {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byteBuffer.putChar('a');
        byteBuffer.putDouble(1.23456789);
        byteBuffer.putFloat(9.000001F);
        byteBuffer.putInt(2);
        byteBuffer.putLong(88888888888L);

        byteBuffer.flip();

        char aChar = byteBuffer.getChar();
        System.out.println(aChar);

        double aDouble = byteBuffer.getDouble();
        System.out.println(aDouble);

        float aFloat = byteBuffer.getFloat();
        System.out.println(aFloat);

        int anInt = byteBuffer.getInt();
        System.out.println(anInt);

        long aLong = byteBuffer.getLong();
        System.out.println(aLong);






    }
}
