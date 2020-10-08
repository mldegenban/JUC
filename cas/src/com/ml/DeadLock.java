package com.ml;

import java.util.concurrent.TimeUnit;

/**
 * @author mei0000
 * @date 2020/10/8 - 13:46
 */

/**
 * jps查看运行中的java线程
 * D:\ideaworkspace\2020-ml>jps -l
 * 16416 org.jetbrains.jps.cmdline.Launcher
 * 5488 com.ml.DeadLock
 * 18404 sun.tools.jps.Jps
 * 2280
 * 17884 org.jetbrains.kotlin.daemon.KotlinCompileDaemon
 * jstack查看故障原因
 *
 * ===================================================
 * "B":
 *         at com.ml.LockResource.run(DeadLock.java:36)
 *         - waiting to lock <0x00000000d638f158> (a java.lang.String)
 *         - locked <0x00000000d638f188> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 * "A":
 *         at com.ml.LockResource.run(DeadLock.java:36)
 *         - waiting to lock <0x00000000d638f188> (a java.lang.String)
 *         - locked <0x00000000d638f158> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:748)
 *
 * Found 1 deadlock.
 */
public class DeadLock {
    public static void main(String[] args) {
        String A ="A";
        String B = "B";
        new Thread(new LockResource(A,B),"A").start();
        new Thread(new LockResource(B,A),"B").start();
    }
}
class LockResource implements Runnable{
    private String A;
    private String B;

    public LockResource(String a, String b) {
        A = a;
        B = b;
    }

    @Override
    public void run() {
        synchronized (A){
            System.out.println(Thread.currentThread().getName()+"\t 持有"+A+"\t 尝试获得"+B);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (B){
                System.out.println(Thread.currentThread().getName()+"\t 持有"+B+"\t 尝试获得"+A);
            }
        }
    }
}
