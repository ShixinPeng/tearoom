package com.netty.lecture10;

import java.nio.ByteBuffer;



/**
 * @author shixinpeng
 * @description Nio中使用堆外内存（直接内存）
 * @ClassName: NioTest08
 * @date 2020/10/19
 *
 */
public class NioTest08 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
        System.out.println(byteBuffer);



    }
}
