package com.netty.lecture10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 模拟对比试验，模拟多个设备同时连接服务器并发送数据，查看传统模式的多线程和Nio可以承载的连接数
 *
 * 可配置参数：客户端数量 、 负载数据量
 */
public class NIoSocketClient {


    private static int clientCount = 3000;

    public static void main(String[] args) throws IOException {

//       startClientBatch();
       startOneClientBatch();
    }

    /**
     * 批量启动客户端
     */
    public static void startClientBatch() throws IOException{

        Selector selector = Selector.open();
        int connectClientCount = 0;
        // 无阻塞设定客户端数量的连接
        while (true) {
            if (clientCount >= connectClientCount){
                connectClientCount++;
                // 客户端同时去连接服务器
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                socketChannel.connect(new InetSocketAddress("127.0.0.1",8999));
                String start = String.format("客户端%03d", connectClientCount);
                socketChannel.register(selector, SelectionKey.OP_CONNECT,  String.format("客户端%03d",connectClientCount));
                System.out.println(String.format("客户端%03d",connectClientCount) +  " 启动");
            }

            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isConnectable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    socketChannel.finishConnect();
                    String tag = (String) selectionKey.attachment();
                    System.out.println(tag +  " 已连接服务器");
                    iterator.remove();

                }
            }


        }
    }

    public static void startOneClientBatch() throws IOException{
        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8999));

        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isConnectable()){

                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    System.out.println("已连接服务端");
                    socketChannel.finishConnect();
                    iterator.remove();
                }
            }

        }
    }
}
