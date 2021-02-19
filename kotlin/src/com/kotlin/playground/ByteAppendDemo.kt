package com.kotlin.playground

import java.nio.ByteBuffer

fun main() {
    val raw = ByteArray(8)
    for (i in 0..7){
        raw.set(i,i.toByte())
    }

    for (i in 1 until 100000){
        val byteBuffer = ByteBuffer.allocate(10).putShort(2);

        for (j in raw.indices){
            byteBuffer.put(2+j,raw[j])
        }
        val array = byteBuffer.array()
    }

    for (i in 1 until 100000){
        val array = ByteBuffer.allocate(10).putShort(2).put(raw, 0, 8).array()
    }
    System.out.println("start")
    val t1 = System.currentTimeMillis()
    for (i in 1 until 100000){
        val byteBuffer = ByteBuffer.allocate(10).putShort(2);

        for (j in raw.indices){
            byteBuffer.put(2+j,raw[j])
        }
        val array = byteBuffer.array()
    }

    val t2 = System.currentTimeMillis()

    System.out.println("\nend t = ${t2-t1}" )


    for (i in 1 until 100000){
        val array = ByteBuffer.allocate(10).putShort(2).put(raw, 0, 8).array()
    }

    val t3 = System.currentTimeMillis()

    System.out.println("\nend t = ${t3-t2}" )
    // val toString = ByteBuffer.allocate(10).putShort(2).put(raw, 2, 2).toString();


}