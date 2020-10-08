package com.ml;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mei0000
 * @date 2020/10/7 - 13:05
 */
//A线程打印A5次，B线程打印B10次，C线程打印C15次
public class LockDemo3 {
    public static void main(String[] args) {
        ShareResource2 shareResource = new ShareResource2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.printThread1();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.printThread2();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.printThread3();
            }
        },"C").start();
    }
}

class ShareResource2{
    int num = 1;
     Lock lock = new ReentrantLock();
     Condition c1 = lock.newCondition();
     Condition c2 = lock.newCondition();
     Condition c3 = lock.newCondition();

    public void printThread1(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+num);
            while (num!=1){
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"-------"+i);
            }
            num=2;
            c2.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printThread2(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+num);
            while (num!=2){
                c2.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"-------"+i);
            }
            num=3;
            c3.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printThread3(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+num);
            while (num!=3){
                c3.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"-------"+i);
            }
            num=1;
            c1.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}