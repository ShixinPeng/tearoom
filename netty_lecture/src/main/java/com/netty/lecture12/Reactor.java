package com.netty.lecture12;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author shixinpeng
 * @description 反应器 创建一个单线程的反应器，对事件进行分发处理
 * Reactor -> Acceptor -> Handler
 * @ClassName: Reactor
 * @date 2020/12/1
 */
public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        // 进行绑定
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 注册连接的监听
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 配置适配器
        selectionKey.attach(new Acceptor());

    }

    @Override
    public void run() {
        try {

            //  Reactor2: Dispatch Loop
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    // 分发处理
                    dispatch(selectionKey);
                }
                // 清除分发过的key
                selectionKeys.clear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理需响应的事件
     * @param selectionKey
     */
    void dispatch(SelectionKey selectionKey){
        Runnable runnable = (Runnable) selectionKey.attachment();
        if (Objects.nonNull(runnable)){
            runnable.run();
        }
    }

    class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (Objects.nonNull(socketChannel)){
                    // 连接已经建立，给Selector绑定上处理器
                    new Handler(selector,socketChannel);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理器
     */
    final class  Handler implements Runnable {
        final SocketChannel socketChannel;
        final SelectionKey sk;
        final int MAXIN = 4096;
        final int MAXOUT = 4096;
        ByteBuffer input = ByteBuffer.allocate(MAXIN);
        ByteBuffer output = ByteBuffer.allocate(MAXOUT);

        static final int READING = 0,SENDING = 1;
        int state = READING;

        Handler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            this.socketChannel.configureBlocking(false);
            // Optionally try first read now
            this.sk = socketChannel.register(selector,0);
            this.sk.attach(this);
            this.sk.interestOps(SelectionKey.OP_READ);
            /*
              为什么要调用 wakeup()?

             */
            selector.wakeup();
        }
        boolean inputIsComplete() {

            return true;
        }
        boolean outputIsComplete() {
            return true;
        }
        // 业务处理
        void  process() {

        }
        // Reactor5: Request handling
        @Override
        public void run() {

            try {
                if      (state == READING) read();
                else if (state == SENDING) send();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        void read() throws IOException {
            // 读取数据
            socketChannel.read(input);
            // 判断是否写入完成
            if (inputIsComplete()){
                // 业务处理
                process();
                state = SENDING;
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }

        void send() throws IOException {
            socketChannel.write(output);
            if (outputIsComplete()){
                sk.cancel();
            }
        }
    }
}
