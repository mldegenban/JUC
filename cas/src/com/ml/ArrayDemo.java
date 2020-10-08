package com.ml;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author mei0000
 * @date 2020/10/5 - 22:29
 */
public class
ArrayDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,1));
                System.out.println(list);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);

        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());

    }

}
