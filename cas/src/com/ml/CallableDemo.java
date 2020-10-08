package com.ml;

import java.util.concurrent.*;

/**
 * @author mei0000
 * @date 2020/10/7 - 21:38
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new ThreadDemo());
        new Thread(futureTask,"aaa").start();
        Integer integer = futureTask.get();
        System.out.println(integer);
    }
}
class ThreadDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println ("=====");
        return 1000;
    }
}