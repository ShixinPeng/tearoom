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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用worker thread 来增强Handler
 * Handler 的Runnable被放置到线程池中执行
 * 代码实现中，Handler with ThreadPool 是把 除precess()处理交给线程池
 */
public class ReactorWorkerThreadServer {

    final ReactorWorkerThreadServer.Reactor reactor;

    public static void main(String[] args) throws IOException {

        ReactorWorkerThreadServer echoServer = new ReactorWorkerThreadServer(8999);
        Thread thread = new Thread(echoServer.reactor);
        thread.setDaemon(true);
        thread.start();
        System.in.read();
        System.out.println("服务端停止");

    }

    ReactorWorkerThreadServer(int port) throws IOException {
      reactor = new ReactorWorkerThreadServer.Reactor(port);
    }
    class Reactor implements Runnable{
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
            selectionKey.attach(new ReactorWorkerThreadServer.Reactor.Acceptor());

        }
        @Override
        public void run() {
            Thread.currentThread().setName("Reactor");
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
                    // 这里创建一个连接后，增加一个对应的handler进行消息处理，并没有停止监听连接事件！！！
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("线程 "+Thread.currentThread().getName() +":接收到连接");
                    if (Objects.nonNull(socketChannel)){
                        // 连接已经建立，给Selector绑定上处理器
                        new ReactorWorkerThreadServer.Handler(selector,socketChannel);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 处理器
     */
     static class Handler implements Runnable {
        /**
         * 处理传递
         */
        synchronized void processAndHandOff(){
            process();
            state = SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);
            sk.selector().wakeup();
         }
         class Processer implements Runnable {

             @Override
             public void run() {
                 // 注意此时的线程
                 Thread.currentThread().setName("Worker");
                 processAndHandOff();
             }
         }


        static ExecutorService pool = Executors.newSingleThreadExecutor();
        final SocketChannel socketChannel;
        final SelectionKey sk;
        final int MAXIN = 4096;
        final int MAXOUT = 4096;
        ByteBuffer input = ByteBuffer.allocate(MAXIN);
        ByteBuffer output = ByteBuffer.allocate(MAXOUT);
        // 新增一个中间状态，PROCESSING
        static final int READING = 0,SENDING = 1,PROCESSING = 3;
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
        // 输入完成条件
        boolean inputIsComplete() {

            // 缓冲区中满足10 byte 才进行回写
            if (input.position()>9){
                // 此时会调用#process进行逻辑处理
                return true;
            } else {
                return false;
            }

        }

        boolean outputIsComplete() {
            if (output.remaining()==0){
                // output中的byte全部已经被写出
                output.clear();
                return true;
            } else {
                // 还有数据可被输出
                return false;
            }
        }
        // 业务处理
        void  process() {
            // 缓冲区满足10 byte,写入到输出缓存区

            input.flip();
            output.put(input);
            // 清空输入缓冲
            input.clear();
            output.flip();

            // 多线程Worker Thread中，如果有耗时操作也不会影响Reactor，只会影响Worker Thread中的任务
//            try {
//                Thread.sleep(10000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
            System.out.println("线程 " + Thread.currentThread().getName() +  " :业务处理完毕");
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

        synchronized void read() throws IOException {
            System.out.println("线程 " + Thread.currentThread().getName() +  ":读数据");
            // 读取数据
            socketChannel.read(input);
            // 判断是否写入完成
            if (inputIsComplete()){
                // 业务处理
//                process();
//                state = SENDING;
//                sk.interestOps(SelectionKey.OP_WRITE);
                state = PROCESSING;
                // !!! 这里把之前直接调用process()，改成了通过线程池来处理
                pool.execute(new Processer());
            }
        }

        void send() throws IOException {
            System.out.println("线程 " + Thread.currentThread().getName() +  ":对外写");
            socketChannel.write(output);
            if (outputIsComplete()){

                // sk.cancel();
                state = READING;
                sk.interestOps(SelectionKey.OP_READ);
            }
        }
    }
}
