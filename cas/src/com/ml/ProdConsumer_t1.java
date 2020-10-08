package com.ml;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mei0000
 * @date 2020/10/7 - 11:49
 */
//需求：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减一，循环5次
public class ProdConsumer_t1 {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                shareData.increment();
            }

        },"Athread").start();
        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                shareData.decrement();
            }
        },"Bthread").start();
    }
}

class ShareData {
    private int num;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 加
     */
    public void increment() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t " + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    /*
    减一
     */
    public void decrement() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t " + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}