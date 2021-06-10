package com.ocjp.concurrent.learn;

public class ThreadPractice {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(ThreadPractice::sayHello);
        System.out.println("pre-start:" + thread.getState());
        thread.start();
        System.out.println("pre-join:" + thread.getState());
        thread.join();
        System.out.println("hello main");
        System.out.println("last :" + thread.getState());

    }

    private static void sayHello(){

        System.out.printf("hello 线程：%s \n",Thread.currentThread().getName());
    }
}
