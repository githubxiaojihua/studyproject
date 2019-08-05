package com.xiaojihua.javabasic.chapter21thread.deadLock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class C02Philosopher implements Runnable {
    private C01Chopstick leftChopstic, rightChopstic;
    private Random random = new Random(47);
    private final int id;
    private final int factor;

    public C02Philosopher(C01Chopstick chopstick1,C01Chopstick chopstick2,int id, int factor){
        this.leftChopstic = chopstick1;
        this.rightChopstic = chopstick2;
        this.id = id;
        this.factor = factor;
    }

    private void pase() throws InterruptedException{
        if(factor == 0){
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(factor * 250));

    }

    @Override
    public void run(){
        //这里try catch了才能在main方法中调用shutdownnow方法后退出，否则
        //无法退出因为Thread.interrupted()方法通常是作为非中断情况下的退出线程的方法
        //当阻塞线程抛出InterruptedException以及调用Thread.interrupted()方法后
        //线程的中断标志都将重置回来。
        /*
            一开是我没有将pase和tack方法的异常直接抛出在这里捕获，而是在
            各自方法中处理了。
            这样就会造成下面的语句块没有try catch。如果单靠Thread.interrupted()
            来退出是不可能的，因为在main方法中的退出是通过shutdownNow()方法，
            他是发送了interrup()方法到线程，而阻塞线程会抛出InterruptedException
            因为pase和tack内部都是可中断阻塞。
            并且重置线程的中断标志，这样异常在方法中处理了，阻塞标志被重置了，
            Thread.interrupted方法无法起到判断的作用，只有在没有抛出InterruptedException
            的情形下想中断线程，才能起到作用，因为没有其他动作重置线程的中断标志。
            这一块可以参考
            H:\code_80968\studyproject\src\com\xiaojihua\javabasic\chapter21thread\shareResource\C0904InterruptingIdiom.java
            的point1和point2两点的线程中断方法。
         */
        try{
            while(!Thread.interrupted()){
                System.out.println(this + "start thinking");
                pase();
                System.out.println(this + "grbbing left");
                leftChopstic.tack();
                System.out.println(this + "grbbing right");
                rightChopstic.tack();
                System.out.println(this + "start eat");
                pase();
                leftChopstic.dropDown();
                rightChopstic.dropDown();
            }
        }catch(InterruptedException e){
            System.out.println(this + " exiting via interrupted");
        }

    }

    @Override
    public String toString(){
        return "Philosopher" + id;
    }
}
