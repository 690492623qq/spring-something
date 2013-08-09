package com.yang.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: jianyuanyang
 * Date: 13-7-11
 * Time: 下午1:23
 */
public class ThreadPoolTest {



    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i = 0 ;i<1000;i++){
            executorService.execute(new newRunnable());
        }
    }
}

class newRunnable implements  Runnable{
    public void run() {
        System.out.println("currentThreadName--"+Thread.currentThread().getName());
    }
}