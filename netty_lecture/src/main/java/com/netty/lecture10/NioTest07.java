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
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

public class NioTest07 {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8999));

        // NoBlocking
        serverSocketChannel.configureBlocking(false);
        // 注册到Selector 第三个参数 ，可以作为附件保存到SelectionKey中
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        Set<SelectionKey> keys = selector.keys();

        Iterator<SelectionKey> iterator = keys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            System.out.println("key=" + key);
        }

        Set<SelectionKey> selectionKeys = selector.selectedKeys();

        Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            System.out.println("selectionKey=" + key);
        }
        while (true) {
            // 会阻塞
            selector.select();

            selectionKeys = selector.selectedKeys();

            keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    System.out.println(key.channel().getClass());
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("validOps=" + serverChannel.validOps());
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    System.out.println("获取客户端的连接：" + socketChannel);
                    keyIterator.remove();
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 获取内容
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    int readBytes = 0;
                    while (true) {
                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);
                        /*
                        * !!! 强烈注意此处的read判断，{@code int read = socketChannel.read(byteBuffer);}
                        * read 不会为-1，当没有新的数据写入buffer的时候，会为0，所以判断应为 {@code read <= 0}
                        * 源码中
                        *   //check if input is shutdown
                        *  if (isInputClosed)
                        *  return IOStatus.EOF;
                        *
                        * 只有在输入流关闭的情况下，才会返回EOF(-1)，应与文件读取区分
                        * 这里一种是，在-1的时候在必须取消注册（-1 连接已经关闭），另外一种处理，读不到数据就取消注册
                        * */
                        if (read <= 0) {
                            break;
                        }
                        readBytes += read;
                        byteBuffer.flip();
                        // 回写
                        socketChannel.write(byteBuffer);
                    }

                    System.out.println("获取客户端bytes=" + readBytes + " channel=" + socketChannel);
//                    key.cancel();
//                    keyIterator.remove();
//                    socketChannel.close();
//                    selector.selectedKeys().remove(key);
                } else if (key.isConnectable()) {
                    SelectableChannel channel = key.channel();

                    System.out.println("isConnectable:" + channel);
                } else if (key.isValid()){
                    SelectableChannel channel = key.channel();

                    System.out.println("isConnectable:" + channel);
                }

            }
        }


//        SelectionKey readSelectionKey = serverSocketChannel.register(selector, SelectionKey.OP_READ);
//
//        int select1 = selector.select();
//        System.out.println("OP_READ:"+select1);


//        System.out.println("before selectNow:"+ selectionKey.toString());
//        selector.selectNow();
//        System.out.println("after selectNow:" + selectionKey.toString());
//
//        while (!selectionKey.isReadable()){
//            System.out.println("isReadable");
//            while (!selectionKey.isConnectable()){
//                System.out.println("isConnectable");
//                System.out.println(serverSocketChannel.socket());
//            }
//
//
//
//        }


    }
}
