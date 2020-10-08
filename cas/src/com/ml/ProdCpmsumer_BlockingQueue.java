package com.ml;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mei0000
 * @date 2020/10/7 - 18:36
 */
public class ProdCpmsumer_BlockingQueue {
    private volatile boolean FLAG=true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue=null;

    public ProdCpmsumer_BlockingQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void produce() throws Exception{
        String data;
        boolean result;
        while (FLAG){

            data=atomicInteger.incrementAndGet()+"";
            TimeUnit.SECONDS.sleep(1);
            result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if(result){
                System.out.println(Thread.currentThread().getName()+"\t 生产成功"+data);
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 生产失败"+data);
            }
        }
        System.out.println(Thread.currentThread().getName()+"生产结束FLAG为"+FLAG);
    }
    public void consumer() throws Exception{
        String result;
        while (FLAG){
            result = blockingQueue.poll(2,TimeUnit.SECONDS);

            if(null==result||result.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println("等待超时，退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"消费成功"+result);
        }
        System.out.println(Thread.currentThread().getName()+"消费结束FLAG为"+FLAG);
    }
    public void stop(){
        FLAG =false;
        System.out.println(Thread.currentThread().getName()+"终止了生产");
    }
    public static void main(String[] args) throws InterruptedException {
        ProdCpmsumer_BlockingQueue prodCpmsumer_blockingQueue= new ProdCpmsumer_BlockingQueue(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            try {
                prodCpmsumer_blockingQueue.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();
        new Thread(()->{
            try {
                prodCpmsumer_blockingQueue.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();

        TimeUnit.SECONDS.sleep(10);
        prodCpmsumer_blockingQueue.stop();
    }
}
