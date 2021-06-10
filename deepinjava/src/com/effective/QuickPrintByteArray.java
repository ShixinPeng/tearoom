package com.effective;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author shixinpeng
 * @description 快速输出两位16进制的byte数组
 * @ClassName: QuickPrintByteArray
 * @date 2021/6/10
 *
 */
public class QuickPrintByteArray {

    public static void main(String[] args) {

        byte[] bytes = mockByteArray(1024);

        int count = 1200;

        Byte[] bBytes = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            bBytes[i] = bytes[i];
        }


        String bytes1 = printBytesByListStreamString(bBytes);

        System.out.println("bytes1=" + bytes1);
        String bytes2 = printBytesByStringBuilder(bytes);
        System.out.println("bytes2=" + bytes2);

        for (int i = 0; i < count; i++) {
            printBytesByListStreamString(bBytes);
        }

        for (int i = 0; i < count; i++) {
            printBytesByStringBuilder(bytes);
        }



        long start2 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            printBytesByListStreamString(bBytes);
        }
        long end2 = System.currentTimeMillis();

        System.out.println("printBytesByListStreamString useTime=" + (end2 - start2));

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            printBytesByStringBuilder(bytes);
        }
        long end1 = System.currentTimeMillis();

        System.out.println("printBytesByStringBuilder useTime=" + (end1 - start1));


    }

    /**
     * 根据字节数组，输出对应的格式化字符串
     * @param bytes 字节数组
     * @return 字节数组字符串
     */
    public static String printBytesByStringBuilder(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();

        for (byte aByte : bytes) {
            stringBuilder.append(byte2String(aByte));
        }

        return stringBuilder.toString();

    }


    /**
     * 使用并行流加速转换
     * @param bytes 字节数组
     * @return 字符串
     */
    public static String printBytesByListStreamString(Byte[] bytes){

       List<Byte> list = Arrays.asList(bytes);

        return list.parallelStream().map(QuickPrintByteArray::byte2String).collect(Collectors.joining());

    }

    public static String byte2String(byte b){

        return String.format("%02x ",b);
    }


    private static byte[] mockByteArray(int size){
        byte[] bytes = new byte[size];

        Random random = new Random();
        random.nextBytes(bytes);
        return bytes;
    }
}
