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
 * 了解三个核心的属:
 * {@code position} 当前可以写入或者读取的位置 （相当于文档输入中的光标）
 * {@code limit} 第一个不可以写入或者读取的位置（相当于文本框，受限制，但可以修改）
 * {@code capacity} 容量，不能为负值，和被改变 （相当于纸张大小，不可变）
 * 重要的写入读取控制方法：
 * mark() reset() flip() clear() rewind() slice()
 *
 *
 * 数据访问方式：
 * 相当方式访问=> get()/put() 依次操作position位置，position的位置递增
 * 绝对方式访问=> 指定索引访问get(@{code i})
 *
 * 核心属性变化关系：0 <= position <= limit <= capacity
 * @ClassName: NioTest01
 * @date 2020/10/19
 *
 */
public class NioTest01 {

    public static void main(String[] args) {
        // buffer 基础属性的作用
        bufferTest01();

    }

    public static void bufferTest(){
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

    /**
     *  java nio buffer 中的基础属性理解
     *  {@code position} {@code limit} {@code capacity}
     * @title bufferTest01
     * @author shixin peng
     * @date 2020-10-21 21:14
     * @param
     * @return void
     * @throws
     */
    public static void bufferTest01(){
        // 写入时，position 递增
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );
        for (int i = 0; i <buffer.capacity()-1; i++) {
            // 相对操作
            buffer.put(i);
            System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );

            if (i==buffer.capacity()/2){
                // 当写入一半时标记mark
//                buffer.mark();
//                System.out.println("执行mark()方法");
            }

        }

        buffer.reset();
        System.out.println("执行reset()方法");
        System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );


        // 执行clear()方法 ：position=0 limit=capacity 相当于清除重置了
        buffer.clear();
        System.out.println("执行clear()方法");
        System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );


        // 使用slice 内容是互相透明的，位置维护是独立的
        buffer.slice();
        // 使用 duplicate  内容是互相透明的，位置维护是独立的
        buffer.duplicate();


    }

    public static void bufferTestFlip(){
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );
        for (int i = 0; i <buffer.capacity()-1; i++) {
            // 相对操作
            buffer.put(i);
            System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );
        }

        // 执行flip()方法：limit = position；position=0，翻转，此时变为读模式，limit为可读的中止边界
        buffer.flip();
        System.out.println("执行flip()方法");
        System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );

    }

    public static void bufferTestRewind(){
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );
        for (int i = 0; i <buffer.capacity()-1; i++) {
            // 相对操作
            buffer.put(i);
            System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );
        }


        // 执行rewind方法 倒回 :position=0 mark=无效位
        buffer.rewind();
        System.out.println("执行rewind方法 倒回");
        System.out.println(String.format("position=%d,limit=%d,capacity=%d",buffer.position(),buffer.limit(),buffer.capacity()) );
    }

}
