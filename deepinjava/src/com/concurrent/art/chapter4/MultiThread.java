package com.concurrent.art.chapter4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author shixinpeng
 * @description 查看普通一个main函数的java程序，会使用多少线程
 * @ClassName: MultiThread
 * @date 2020/1/10
 *
 */
public class MultiThread {

    public static void main(String[] args) {

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        for (ThreadInfo info : threadInfos){
            System.out.printf("ThreadId:%d ThreadName:%s \n",info.getThreadId(),info.getThreadName());
        }

        /**
         *
         ThreadId:7 ThreadName:JDWP Command Reader
         ThreadId:6 ThreadName:JDWP Event Helper Thread
         ThreadId:5 ThreadName:JDWP Transport Listener: dt_socket
         ThreadId:4 ThreadName:Signal Dispatcher 分别处理发送给JVM信号的线程
         ThreadId:3 ThreadName:Finalizer 调用对象finalizer的线程
         ThreadId:2 ThreadName:Reference Handler 清除Reference的线程
         ThreadId:1 ThreadName:main
         *
         *
         */

    }
}
