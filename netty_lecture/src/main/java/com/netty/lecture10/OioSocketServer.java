package com.netty.lecture10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端进行端口监听
 */
public class OioSocketServer {

    public static int PORT = 8999;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("start:" + serverSocket);

        try {
            Socket socket = serverSocket.accept();

            try {
                System.out.println("Connection acception" + socket);

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

                BufferedReader in = new BufferedReader(inputStreamReader);

                // Output is automatically flushed by PrintWriter:
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                while (true) {
                    String readLine = in.readLine();
                    if (readLine.equals("END")) {
                        break;
                    }

                    System.out.println("Read:" + readLine);

                    out.println(readLine);

                }
            } finally {
                System.out.print("server colsing");
                socket.close();
            }

        } finally {

            serverSocket.close();

        }
    }
}
