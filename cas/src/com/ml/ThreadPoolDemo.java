package com.ml;

import java.util.concurrent.*;

/**
 * @author mei0000
 * @date 2020/10/8 - 10:26
 */

/**
 *     public ThreadPoolExecutor(int corePoolSize,
 *                               int maximumPoolSize,
 *                               long keepAliveTime,
 *                               TimeUnit unit,
 *                               BlockingQueue<Runnable> workQueue,
 *                               ThreadFactory threadFactory,
 *                               RejectedExecutionHandler handler
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        //线程池最大接纳请求数量是阻塞队列的数量加线程池最大的容量
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 1; i <= 10; i++) {
                int finami =i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"aaaaaaaa"+finami);
                });
            }
        }catch (Exception exception){
        }finally {
            threadPool.shutdown();
        }
    }
}
