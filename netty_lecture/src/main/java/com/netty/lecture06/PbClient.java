package com.netty.lecture06;


import com.netty.protobuf.DataInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PbClient  {

    public static File file = new File("/Users/shixinpeng/tearoom/netty_lecture/src/main/java/com/netty/lecture06/student.code");
    public static void main(String[] args) throws IOException {


    }

    public static void pbEncode() throws IOException {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("小红").setPhone("123456789").setAge(23).build();

        System.out.println("student"+student.toString());

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bytes = student.toByteArray();
        System.out.println("bytes长度="+bytes.length);
        fileOutputStream.write(bytes);
        fileOutputStream.close();


    }

    public static void pbDecode() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file);
        int available = fileInputStream.available();
        byte[] fileBytes = new byte[available];
        fileInputStream.read(fileBytes,0,available);
        DataInfo.Student student2 = DataInfo.Student.parseFrom(fileBytes);

        System.out.println("student2" + student2.toString());
        fileInputStream.close();


    }

}
