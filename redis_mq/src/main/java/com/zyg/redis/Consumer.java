package com.zyg.redis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * Author: zhaoyuanguang
 * Date: 2019/7/10 16:31
 * Content:
 */
public class Consumer implements Runnable {
    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            String taskid = jedis.rpoplpush("task-queue", "tmp-queue");
           /*
           RPOPLPUSH source destination
            命令 RPOPLPUSH 在一个原子时间内，执行以下两个动作：
            1、将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端。
            2、将 source 弹出的元素插入到列表 destination ，作为 destination 列表的的头元素。
            */

            //模拟处理任务：睡觉
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //模拟成功和失败的偶然现象
            if(random.nextInt(13) % 7 == 0){// 模拟失败
                //将本次处理失败的任务从暂存队列"tmp-queue"中，弹回任务队列"task-queue"
                jedis.rpoplpush("tmp-queue", "task-queue");
                System.out.println(taskid + "处理失败，被弹回任务队列");

            } else {// 模拟成功
                // 将本次任务从暂存队列"tmp-queue"中清除
                jedis.rpop("tmp-queue");
                System.out.println(taskid+"处理成功，被清除");
            }
        }
    }
    Jedis jedis = new Jedis("127.0.0.1", 6379);

}
