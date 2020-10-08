package com.ml;

/**
 * @author mei0000
 * @date 2020/10/6 - 22:56
 */

import java.util.concurrent.CountDownLatch;

/**
 * 需求：
 *
 */
public class CountDownLatchDemo {
    private static final int NUM =6;
    public static void main(String[] args) throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"国被灭");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println("一统华夏");
    }
}
