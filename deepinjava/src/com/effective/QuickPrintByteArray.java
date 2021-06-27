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
 */
public class QuickPrintByteArray {

    /**
     * 空格字符
     */
    public final static char WHITE_CHAR = ' ';
    /**
     * 每个byte都对应一个char数组，两位的字符表示
     */
    public final static char[][] BYTE_CHARS =
            {{'0', '0'}, {'0', '1'}, {'0', '2'}, {'0', '3'}, {'0', '4'}, {'0', '5'}, {'0', '6'}, {'0', '7'}, {'0', '8'}, {'0', '9'}, {'0', 'a'}, {'0', 'b'}, {'0', 'c'}, {'0', 'd'}, {'0', 'e'}, {'0', 'f'}, {'1', '0'}, {'1', '1'}, {'1', '2'}, {'1', '3'}, {'1', '4'}, {'1', '5'}, {'1', '6'}, {'1', '7'}, {'1', '8'}, {'1', '9'}, {'1', 'a'}, {'1', 'b'}, {'1', 'c'}, {'1', 'd'}, {'1', 'e'}, {'1', 'f'}, {'2', '0'}, {'2', '1'}, {'2', '2'}, {'2', '3'}, {'2', '4'}, {'2', '5'}, {'2', '6'}, {'2', '7'}, {'2', '8'}, {'2', '9'}, {'2', 'a'}, {'2', 'b'}, {'2', 'c'}, {'2', 'd'}, {'2', 'e'}, {'2', 'f'}, {'3', '0'}, {'3', '1'}, {'3', '2'}, {'3', '3'}, {'3', '4'}, {'3', '5'}, {'3', '6'}, {'3', '7'}, {'3', '8'}, {'3', '9'}, {'3', 'a'}, {'3', 'b'}, {'3', 'c'}, {'3', 'd'}, {'3', 'e'}, {'3', 'f'}, {'4', '0'}, {'4', '1'}, {'4', '2'}, {'4', '3'}, {'4', '4'}, {'4', '5'}, {'4', '6'}, {'4', '7'}, {'4', '8'}, {'4', '9'}, {'4', 'a'}, {'4', 'b'}, {'4', 'c'}, {'4', 'd'}, {'4', 'e'}, {'4', 'f'}, {'5', '0'}, {'5', '1'}, {'5', '2'}, {'5', '3'}, {'5', '4'}, {'5', '5'}, {'5', '6'}, {'5', '7'}, {'5', '8'}, {'5', '9'}, {'5', 'a'}, {'5', 'b'}, {'5', 'c'}, {'5', 'd'}, {'5', 'e'}, {'5', 'f'}, {'6', '0'}, {'6', '1'}, {'6', '2'}, {'6', '3'}, {'6', '4'}, {'6', '5'}, {'6', '6'}, {'6', '7'}, {'6', '8'}, {'6', '9'}, {'6', 'a'}, {'6', 'b'}, {'6', 'c'}, {'6', 'd'}, {'6', 'e'}, {'6', 'f'}, {'7', '0'}, {'7', '1'}, {'7', '2'}, {'7', '3'}, {'7', '4'}, {'7', '5'}, {'7', '6'}, {'7', '7'}, {'7', '8'}, {'7', '9'}, {'7', 'a'}, {'7', 'b'}, {'7', 'c'}, {'7', 'd'}, {'7', 'e'}, {'7', 'f'}, {'8', '0'}, {'8', '1'}, {'8', '2'}, {'8', '3'}, {'8', '4'}, {'8', '5'}, {'8', '6'}, {'8', '7'}, {'8', '8'}, {'8', '9'}, {'8', 'a'}, {'8', 'b'}, {'8', 'c'}, {'8', 'd'}, {'8', 'e'}, {'8', 'f'}, {'9', '0'}, {'9', '1'}, {'9', '2'}, {'9', '3'}, {'9', '4'}, {'9', '5'}, {'9', '6'}, {'9', '7'}, {'9', '8'}, {'9', '9'}, {'9', 'a'}, {'9', 'b'}, {'9', 'c'}, {'9', 'd'}, {'9', 'e'}, {'9', 'f'}, {'a', '0'}, {'a', '1'}, {'a', '2'}, {'a', '3'}, {'a', '4'}, {'a', '5'}, {'a', '6'}, {'a', '7'}, {'a', '8'}, {'a', '9'}, {'a', 'a'}, {'a', 'b'}, {'a', 'c'}, {'a', 'd'}, {'a', 'e'}, {'a', 'f'}, {'b', '0'}, {'b', '1'}, {'b', '2'}, {'b', '3'}, {'b', '4'}, {'b', '5'}, {'b', '6'}, {'b', '7'}, {'b', '8'}, {'b', '9'}, {'b', 'a'}, {'b', 'b'}, {'b', 'c'}, {'b', 'd'}, {'b', 'e'}, {'b', 'f'}, {'c', '0'}, {'c', '1'}, {'c', '2'}, {'c', '3'}, {'c', '4'}, {'c', '5'}, {'c', '6'}, {'c', '7'}, {'c', '8'}, {'c', '9'}, {'c', 'a'}, {'c', 'b'}, {'c', 'c'}, {'c', 'd'}, {'c', 'e'}, {'c', 'f'}, {'d', '0'}, {'d', '1'}, {'d', '2'}, {'d', '3'}, {'d', '4'}, {'d', '5'}, {'d', '6'}, {'d', '7'}, {'d', '8'}, {'d', '9'}, {'d', 'a'}, {'d', 'b'}, {'d', 'c'}, {'d', 'd'}, {'d', 'e'}, {'d', 'f'}, {'e', '0'}, {'e', '1'}, {'e', '2'}, {'e', '3'}, {'e', '4'}, {'e', '5'}, {'e', '6'}, {'e', '7'}, {'e', '8'}, {'e', '9'}, {'e', 'a'}, {'e', 'b'}, {'e', 'c'}, {'e', 'd'}, {'e', 'e'}, {'e', 'f'}, {'f', '0'}, {'f', '1'}, {'f', '2'}, {'f', '3'}, {'f', '4'}, {'f', '5'}, {'f', '6'}, {'f', '7'}, {'f', '8'}, {'f', '9'}, {'f', 'a'}, {'f', 'b'}, {'f', 'c'}, {'f', 'd'}, {'f', 'e'}, {'f', 'f'}};

    public static void main(String[] args) {


        compareTest();

    }

    private static void compareTest() {

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
        String bytes3 = printBytesByCharPool(bytes);
        System.out.println("bytes3=" + bytes3);

        // 预热
        for (int i = 0; i < count; i++) {
            printBytesByListStreamString(bBytes);
        }

        for (int i = 0; i < count; i++) {
            printBytesByStringBuilder(bytes);
        }

        for (int i = 0; i < count; i++) {
            printBytesByCharPool(bytes);
        }
        // =======================
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

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            printBytesByCharPool(bytes);
        }
        long end3 = System.currentTimeMillis();

        System.out.println("printBytesByCharPool useTime=" + (end3 - start3));

    }


    /**
     * 使用字符数组进行byte字节信息的输出 如果默认进制标识的话，不打印'0x',一个byte只需要两个char 例如： 0x9  = '0' + '9' 0xAF = 'A' + 'F'
     * 0x9 0xAF = > '0'+'9'+' '+'A'+'F'
     * <p>
     * 一个byte需要2个字符标识，外加一个空格字符
     *
     * @param bytes 需要格式化的byte
     * @return 字节数组 字符串
     */
    public static String printBytesByCharPool(byte[] bytes) {
        int byteLength = bytes.length;
        int charLength = byteLength * 3;


        char[] content = new char[charLength];
        int unsignedByte = 0;
        int startIndex = 0;
        for (int i = 0; i < byteLength; i++) {
            // 使byte变为无符号的byte
            // b2u
            unsignedByte = ((int) bytes[i]) & 0xFF;

            char[] chars = BYTE_CHARS[unsignedByte];
            startIndex = i * 3;
            // byte的第一位字符
            content[startIndex] = chars[0];
            // byte格式化的第二位字符
            content[startIndex + 1] = chars[1];
            // 尾随的空格字符
            content[startIndex + 2] = WHITE_CHAR;
        }

        return new String(content);
    }

    /**
     * 根据字节数组，输出对应的格式化字符串
     *
     * @param bytes 字节数组
     * @return 字节数组字符串
     */
    public static String printBytesByStringBuilder(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte aByte : bytes) {
            stringBuilder.append(byte2String(aByte));
        }

        return stringBuilder.toString();

    }


    /**
     * 使用并行流加速转换
     *
     * @param bytes 字节数组
     * @return 字符串
     */
    public static String printBytesByListStreamString(Byte[] bytes) {

        List<Byte> list = Arrays.asList(bytes);

        return list.parallelStream().map(QuickPrintByteArray::byte2String).collect(Collectors.joining());

    }

    public static String byte2String(byte b) {

        return String.format("%02x ", b);
    }


    private static byte[] mockByteArray(int size) {
        byte[] bytes = new byte[size];

        Random random = new Random();
        random.nextBytes(bytes);
        return bytes;
    }


    /**
     * 生成byte对应的字符数组
     */
    public static void mockByteCharString() {
        // ex:
        char[][] chars = {{'0', '0'}, {'0', '1'}};


        for (byte i = 0; i <= 0xF; i++) {
            for (int j = 0; j <= 0xF; j++) {
                String byteForm = String.format("{'%x','%x'}, ", i, j);
                System.out.print(byteForm);
            }

        }
    }
}
