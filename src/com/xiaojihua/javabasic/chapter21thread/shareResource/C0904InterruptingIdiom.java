package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1、通过检查中断状态来检查来中断，不处于阻塞状态的线程。
 * 2、以前的例子都是通过在阻塞线程上抛出异常来退出。但是当线程不处于阻塞状态时，就需要使用检查中断来
 * 中断run方法。
 * 3、Thread.interrupt()会设置中断状态，并通过Thread.interrupted()来检测中断
 * 状态是否为已中断，并且将中断状态复位。可以将Thread.interrupted()结果存储起来再次使用（因为中断
 * 状态已经复位了）
 *
 * 下面的例子中演示了，处于阻塞时候的中断以及出于非阻塞时候的中断
 */

/**
 * 模拟需要关闭的资源
 */
class NeedsCleanup{
    private final int id;
    public NeedsCleanup(int id){
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup(){
        System.out.println("Cleaning up " + id);
    }
}

class Block3 implements Runnable{
    private double d = 0.0;
    @Override
    public void run(){
        try{
            /**
             * 检测中断状态，当检测到中断状态已经为中断的时候
             * 合理的退出while循环，进而退出run
             * 这是非阻塞情况下退出线程
             */
            while(!Thread.interrupted()){
                NeedsCleanup n1 = new NeedsCleanup(1);
                try {
                    System.out.println("sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try{
                        //模拟复杂计算
                        System.out.println("Calculating");
                        for(int i = 1; i < 2500000; i++){
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("Finished time-consuming operation");
                    }finally {
                        //无论是否发生异常均执行
                        n2.cleanup();//清理
                    }

                } finally {
                    //无论是否发生异常均执行
                    n1.cleanup();//清理
                }
            }
            System.out.println("Exiting via while() test");
        }catch(InterruptedException e){
            //这是在阻塞状态下，通过抛出异常来中断线程
            //在这里可以做一些其他的工作
            System.out.println("Exiting via InterruptedException");
        }

    }
}

public class C0904InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Block3());
        thread.start();
        TimeUnit.MILLISECONDS.sleep(6000);
        thread.interrupt();
    }
}
