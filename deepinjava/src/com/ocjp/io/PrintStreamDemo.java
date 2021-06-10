package com.ocjp.io;

import com.sun.corba.se.impl.orbutil.ORBConstants;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.sql.SQLOutput;
import java.util.Properties;

public class PrintStreamDemo {

    public static void main(String[] args) {

        FileDescriptor outFD = FileDescriptor.out;

        FileOutputStream fdOut = new FileOutputStream(outFD);

        System.out.println(outFD);
    }
}
