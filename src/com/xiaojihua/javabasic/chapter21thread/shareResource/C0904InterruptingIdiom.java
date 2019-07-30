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
 *
 * 中断线程调用的两种途径：
 * 在任务的run方法中经常会使用到while循环，通常结束while循环
 * 或者是结束当前进程的时候需要通过不同的方式，当程序存在阻塞的时候
 * 通过调用interrupt()方法，线程会抛出InteruptedException异常
 * 通过捕获异常来中断阻塞的线程（sleep等能够抛出InterruptedException的
 * 方法可以通过如上方法实现中断，但是IO阻塞和synchronized阻塞是不能通过
 * 这种方式中断的只能结束程序或者关闭底层资源）。
 * 但是如果线程没有存在阻塞想通过interrupt()方法来结束的话，需要
 * 判断线程的中断状态，因为在调用interrupt()方法后会设置线程的中断状态为true
 * 通过Thread.interrupted()可以判断线程中断标志，但是注意当调用了一遍
 * Thread.interrupted()后线程的中断标志即复位，如果想要重新判断的话需要
 * 将Thread.interrupted()的值存储起来。
 *
 * 本例可以通过设置main线程中的sleep时长和Block3中point1中的sleep时长
 * 来测试阻塞时中断和非阻塞时中断的不同
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
                    /*
                        本任务通过两个不同的point来测试阻塞和非阻塞情况下
                        如何退出run方法
                        下面两句是第一个point，实现了阻塞，如果程序运行到喜爱案
                        这两句处于阻塞的情况下，外部调用线程的interrpt()方法后
                        这里会抛出异常。使用try finnally包裹起来
                        保证资源进行了释放
                     */
                    System.out.println("sleeping");
                    TimeUnit.SECONDS.sleep(1);

                    NeedsCleanup n2 = new NeedsCleanup(2);
                    /*
                        下面是第二个point，通过模拟复杂计算模拟了非阻塞情况，
                        此时在外部调用interrupt方法并不会抛出异常而是，会执行完成
                        剩余的语句，当到达新一轮while的时候通过
                        !Thread.interrupted()判断是否线程的中断状态被设置了
                        如果被设置了那么就不在进行while循环了。
                        同样如果有待释放的资源的话需要使用try finally包裹起来
                     */
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
        TimeUnit.MILLISECONDS.sleep(1001);
        thread.interrupt();
    }
}
