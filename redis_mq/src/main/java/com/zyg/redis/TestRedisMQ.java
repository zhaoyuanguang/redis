package com.zyg.redis;

/**
 * Author: zhaoyuanguang
 * Date: 2019/7/10 16:40
 * Content:
 */
public class TestRedisMQ {
    public static void main(String[] args) throws InterruptedException {
        // 启动一个生产者线程，模拟任务的产生
        Thread t1 = new Thread(new Producer());
        t1.start();
        Thread.sleep(1000);
        //启动一个线程者线程，模拟任务的处理
        Thread t2 = new Thread(new Consumer());
        t2.start();
        t2.join();
        //
        System.out.println("hhh");

    }
}
