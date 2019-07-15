package com.zyg.redis;

import redis.clients.jedis.Jedis;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * Author: zhaoyuanguang
 * Date: 2019/7/10 16:26
 * Content:
 */
public class Producer implements Runnable {
    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(1000);
                // 模拟生成一个任务
                UUID taskId = UUID.randomUUID();
                //将任务插入任务队列：task-queue
                jedis.lpush("task-queue", taskId.toString());
                System.out.println("插入了一个新的任务： " + taskId);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    Jedis jedis = new Jedis("127.0.0.1", 6379);
}
