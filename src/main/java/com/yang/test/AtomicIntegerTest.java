package com.yang.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: jianyuanyang
 * Date: 13-7-10
 * Time: 上午9:29
 */
public class AtomicIntegerTest {

    AtomicInteger atomicInteger =  new AtomicInteger(0);

    static int inttest = 0 ;

    synchronized void add(){
        inttest = inttest + 1;
        System.out.println(Thread.currentThread().getName()+"--inttest--"+inttest);
    }

    void add2(){
        System.out.println(Thread.currentThread().getName()+"--atomicInteger--"+  atomicInteger.incrementAndGet());
    }

    synchronized void add3(){
        inttest++;
        System.out.println(Thread.currentThread().getName()+"--inttest--"+inttest);
    }


    public static void main(String[] args) {
        final AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();

        for(int i=0 ;i<5 ;i++){
            new Thread(){
                @Override
                public void run() {
                    atomicIntegerTest.add2();
                    atomicIntegerTest.add();
                }
            }.start();
        }

        //System.out.println("final atomicInteger--" + atomicIntegerTest.atomicInteger + "--final inttest--" + inttest);
    }

}
