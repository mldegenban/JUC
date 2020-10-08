package com.ml;

import org.omg.PortableServer.THREAD_POLICY_ID;
import sun.dc.pr.PRError;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mei0000
 * @date 2020/10/7 - 13:05
 */
//A线程打印A5次，B线程打印B10次，C线程打印C15次
public class LockDemo2 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.printThread(5,1);

            }

        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.printThread(10,2);

            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.printThread(15,3);

            }
        },"C").start();
    }
}

class ShareResource{
     Lock lock = new ReentrantLock();
     Condition c1 = lock.newCondition();
     Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void printThread(int count,int num){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+num);
            while (num==1){
                for (int i = 1; i <= count; i++) {
                    System.out.println(Thread.currentThread().getName()+"-----"+i);
                }
                c2.signal();
                c1.await();
                break;
            }
            while (num==2){
                for (int i = 1; i <= count; i++) {
                    System.out.println(Thread.currentThread().getName()+"-----"+i);
                }
                c3.signal();
                c2.await();
                break;
            }
            while (num==3){
                for (int i = 1; i <= count; i++) {
                    System.out.println(Thread.currentThread().getName()+"-----"+i);
                }
                c1.signal();
                c3.await();
                break;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}