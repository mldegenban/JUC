package com.ml;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author mei0000
 * @date 2020/10/7 - 10:51
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        //默认初始值为10
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                String poll = blockingQueue.poll();
                System.out.println(Thread.currentThread().getName() + "\t get" + poll);
                TimeUnit.SECONDS.sleep(2);
                String poll2 = blockingQueue.poll();
                System.out.println(Thread.currentThread().getName() + "\t get" + poll2);
                TimeUnit.SECONDS.sleep(2);
                String poll3 = blockingQueue.poll();
                System.out.println(Thread.currentThread().getName() + "\t get" + poll3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
