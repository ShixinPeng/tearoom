package com.netty.lecture10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 传统io进行socket编程
 * 客户端建立一个socket连接
 *
 */
public class OioSocketClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        InetAddress addr = InetAddress.getByName(null);

        System.out.println("InetAddress:"+addr);

        Socket socket = new Socket(addr,OioSocketServer.PORT);

        try {

            System.out.println("socket："+socket);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

            for (int i = 0; i < 10; i++) {
                out.println("hello:"+i);
                String s = in.readLine();
                System.out.println(s);
                Thread.sleep(5000);
            }
            out.println("END");

        }finally {
            System.out.println("client closing……");
            socket.close();
        }


    }
}
