package com.concurrent.learn;

public class ThreadDumpDemo {
    public static void main(String[] args) {

        // Thread api 获取线程栈
        Thread.dumpStack();
        // Throwable
        new Throwable().printStackTrace();

        new Exception("hello Stack trace").printStackTrace();
    }
}
