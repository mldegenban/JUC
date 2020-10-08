package com.ml;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import javax.xml.bind.SchemaOutputResolver;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mei0000
 * @date 2020/10/6 - 18:09
 */
public class LockDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendMSG();
        }).start();
        new Thread(() -> {
            phone.sendMSG();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("------------------------------------------------");
        new Thread(phone).start();
        new Thread(phone).start();

    }


}

class Phone implements Runnable {
    public synchronized void sendMSG() {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMG");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendEmail");
    }


    //-----------------------------------

    Lock lock = new ReentrantLock(false);


    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t invoked get");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t invoked set");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}