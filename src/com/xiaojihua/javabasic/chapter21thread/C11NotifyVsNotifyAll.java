package com.xiaojihua.javabasic.chapter21thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 0、notify\notifyAll唤醒的都是当前对象上挂起的线程。
 * 1、notify唤醒众多等待同一个锁的任务中的一个。notify的使用场景：第一，保证被唤醒的
 * 是恰当的任务，第二各个任务必须等待相同的条件，如果多个任务在等到不同的条件那么唤醒的
 * 就不一定是前挡的任务，第三只能唤醒一个任务。
 * 2、notifyAll并不是唤醒所有挂起的线程，而是唤醒调用某个锁（对象锁）而挂起的线程
 * 3、Timer的使用
 * 4、如果不确定哪个任务被唤醒，那么可以使用notifyAll
 *
 * 本例中Task和Task2都有自己的blocker对象。Task在Task.blocker上阻塞，Task2在Task2.blocker
 * 上阻塞，因此在调用Task.blocker上的notify和notifyall的时候并不会影响Task2.blocker上
 * 被阻塞的线程。
 */

/**
 * 阻塞的对象，共享的资源
 * 对象中的三个方法必须加锁，否则，线程调用notify或者notifyall\wait
 * 会报错
 */
class Blocker{
    /**
     * 立即阻塞调用此方法的线程，并在唤醒后打印线程名字，然后重新阻塞
     */
    synchronized public void waitingCall(){
        try{
            while(!Thread.interrupted()){
                //这里不需要使用while判断条件状态，因为没有条件。
                //按照以前的知识点，使用wait的时候应该在外层包裹一层while
                //对wait条件进行判断，防止多个线程同时在等待同一把锁的解除
                //而导致的唤醒后，wait条件又满足了，但是无法wait的情况
                wait();
                System.out.println(Thread.currentThread() + " ");
            }
        }catch(InterruptedException e){
            //OK to exit any way
            //System.out.println("yichang");
        }

    }

    synchronized public void prod(){
        notify();
    }

    synchronized public void prodAll(){
        notifyAll();
    }
}

/**
 * Task任务，内部包含了自己的Blocker
 */
class Task implements Runnable{
    static Blocker broker = new Blocker();
    @Override
    public void run(){
        broker.waitingCall();
    }
}

/**
 * Task2，内部包含了自己的Blocker
 */
class Task2 implements Runnable{
    static Blocker blocker = new Blocker();
    @Override
    public void run(){
        blocker.waitingCall();
    }
}

public class C11NotifyVsNotifyAll {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();
        //往线程池中添加5个task任务
        for(int i = 0; i < 5 ; i++){
            service.execute(new Task());
        }
        //往线程池中添加1个task2任务
        service.execute(new Task2());
        //使用timer对象，每4/10秒执行一次TimerTask的run方法
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;
            //交替的唤醒一个Task.blocker上阻塞的线程和唤醒所有Task.blecker上阻塞的线程
            //唤醒后又立即阻塞了，除非调用了Thread.interrupt()
            @Override
            public void run() {
                if(prod){
                    System.out.println("\nnotify");
                    Task.broker.prod();
                    prod = false;
                }else{
                    System.out.println("\nnotifyAll");
                    Task.broker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();//终止所有schedule
        System.out.println("\nTimer canceled");

        //唤醒Task2.blocker上阻塞的线程，从输出可以看到不会影响Task.blocker上阻塞的线程
        TimeUnit.MILLISECONDS.sleep(400);
        System.out.println("Task2.blocker.prodAll");
        Task2.blocker.prodAll();

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nshutting down");
        //中断所有线程
        service.shutdownNow();

    }
}
