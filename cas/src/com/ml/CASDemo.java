package com.ml;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mei0000
 * @date 2020/10/5 - 16:46
 */
public class CASDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        new Thread(()->atomicInteger.set(100)).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(atomicInteger.compareAndSet(5, 2020)+"------current data-----"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2050)+"------current data-----"+atomicInteger.get());
    }
}
