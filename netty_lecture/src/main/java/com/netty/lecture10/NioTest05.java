package com.netty.lecture10;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 只读byteBuffer
 * 堆外buffer
 * scattering与Gathering
 *
 */
public class NioTest05 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        ByteBuffer direct = ByteBuffer.allocateDirect(10);

        Field[] fields = Buffer.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("field="+fields[i].getName());
        }

        Field[] declaredFields = Buffer.class.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println("declaredField="+declaredFields[i].getName());
        }
        Field field = Buffer.class.getDeclaredField("address");

        field.setAccessible(true);

        Object o = field.get(direct);

        System.out.println("address="+o);

    }

    public static void readOnlyBuffer(){
        // HeapByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte)i);
        }

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();

        System.out.println(readOnlyBuffer.getClass());
        // java.nio.HeapByteBufferR
    }
}
