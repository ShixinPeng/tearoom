package com.netty.lecture10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端进行端口监听
 * serverSocket不断监听端口
 * 当有连接时，会创建一个线程单独处理该连接和端口的通信，并继续监听该线程
 */
public class OioSocketMultiServer {

    public static int PORT = 8999;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("start:" + serverSocket);
        int clientNum = 0;
        try {

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection acceptation" + socket + " clientNum:" + clientNum++);
                try {

                    new ServeOneJabber(socket);

                } catch (Exception e){
                    System.out.print("server colsing");
                    socket.close();
                }

            }

        } finally {

            serverSocket.close();

        }
    }
}


class ServeOneJabber extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServeOneJabber(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String s = in.readLine();
                if (s.equals("END")) {

                    break;
                }
                System.out.println("Echoing:" + s);

                out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}