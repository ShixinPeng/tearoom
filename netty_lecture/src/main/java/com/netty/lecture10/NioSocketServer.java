package com.netty.lecture10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * 通过模拟一个类似于聊天室的demo，了解通过Selector模式进行多客户端的连接
 * 并尝试测试：多线程的方式和Selector方式承接的连接数
 */
public class NioSocketServer {


    private static HashMap<String,SocketChannel> clientMap = new HashMap();

    public static void main(String[] args) throws IOException {
        // 创建服务端连接
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8999));
        System.out.println("服务端启动！");
        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            // 可操作性key
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            if (selectionKeys.isEmpty()){
                continue;
            }
            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    // 建立连接
                    SocketChannel client = channel.accept();
                    String clientId = UUID.randomUUID().toString();
                    clientMap.put(clientId,client);
                    System.out.println("客户端连接 clientId="+clientId + " client="+client);
                    // 注册读操作
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ,clientId);
                    selector.selectedKeys().remove(selectionKey);
                } else if (selectionKey.isReadable()){
                    // 读取单个客户端写入的输出
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    String clientId = (String)selectionKey.attachment();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);

                    while (true){
                        // 先清空
                        byteBuffer.clear();

                        String heard = clientId + "::";
                        byteBuffer.put(heard.getBytes());
                        int read = channel.read(byteBuffer);

                        if (read == 0){
                            // 当前无可读数据
                            break;
                        } else if (read < 0) {
                            // read = -1;通道已经关闭
                            channel.close();

                            clientMap.remove(clientId);
                            System.out.println("客户端离线："+clientId);
                            break;
                        }
                        // 发送给其他设备
                        for (SocketChannel socketChannel : clientMap.values()) {
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                        }
                    }
                    selector.selectedKeys().remove(selectionKey);

                }

            }
        }

    }
}
