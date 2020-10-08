package com.ml;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author mei0000
 * @date 2020/10/6 - 22:09
 */
public class RWLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {

            int finalI = i;
            new Thread(() -> {
                try {
                    myCache.put(finalI + "", finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int finali = i;
            new Thread(() -> {
                myCache.get(finali+"");
            }, String.valueOf(i)).start();
        }

    }
}
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) throws InterruptedException {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入" + key);
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }
    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 正在获取");
        try {
            TimeUnit.SECONDS.sleep(1);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 获取完成" + value);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}