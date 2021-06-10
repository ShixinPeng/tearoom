package com.io.file;

import java.io.File;
import java.util.Arrays;

public class FilePractice {

    public static void main(String[] args) {
        File file = new File("/Users/shixinpeng/tearoom/deepinjava/src/com/io/file/FilePractice.java");

        File file1 = new File(new File("/Users/shixinpeng/tearoom/deepinjava/src/com/io/file/"), "FilePractice.java");

        System.out.println(file.equals(file1));

        System.out.println(Arrays.toString(file.listFiles()));

    }
}
