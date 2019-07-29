package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1、中断正在阻塞的线程。
 * 2、线程的五种状态以及进入阻塞状态的情况：参考读书笔记中java基础--并发--线程的几种状态小结
 * 3、通过Excutor的shutdownNow()方法中断所有线程，底层是发送Thread.Interrupt()方法终止被阻塞的程序。
 * 会抛出InterruptedException。
 * 4、中断Executor中的单一程序
 * 5、能够中断sleepblock，但是不能中断IOBlock,SynchronizedBlock
 *
 * 通过Thread.interrupt()可以终止一个处于阻塞的任务，interrupt方法会设置线程的interrupted status
 * 为true，当一个线程的interrupted status被设置为true的时候，当线程处于阻塞状态或者尝试执行一个阻塞操作
 * 的时候会抛出InterruptedException。当异常抛出后或者调用Thread.interrupted()方法后线程的
 * interrupted status会重置为false。通过Thread.interrupted()方法可以作为离开线程run()方案中的循环
 * 的第二种方法，另外一种是抛出异常。
 * interrupt()能够中断sleepblock，但是不能中断IOBlock,SynchronizedBlock
 */

/**
 * sleep阻塞
 */
class SleepBlock implements Runnable{


    @Override
    public void run(){
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            //throw new RuntimeException(e);
        }
        System.out.println("Exiting SleepBlock.run()");
    }
}

/**
 * IO阻塞
 */
class IOBlocked implements Runnable{
    private InputStream in;
    public IOBlocked(InputStream in){
        this.in = in;
    }

    @Override
    public void run(){
        System.out.println("Waiting for read");
        try {
            in.read();
        } catch (IOException e) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted from I/0block");
            }else{
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlock.run()");
    }
}

/**
 * Synchronized阻塞
 */
class SynchronizedBlocked implements Runnable{
    synchronized public void f(){
        while(true){
            Thread.yield();
        }
    }

    public SynchronizedBlocked(){
        new Thread(){
            public void run(){
                f();
            }
        }.start();
    }

    @Override
    public void run(){
        System.out.println("try to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}

public class C09Interrupting {
    private static ExecutorService service = Executors.newCachedThreadPool();

    /**
     * 终止Executor中的单一程序
     * 必须使用submit而不是execute.
     * Future的cancel可以中断特定任务，cancel参数为true
     * @param r
     * @throws InterruptedException
     */
    public static void test(Runnable r) throws InterruptedException {
        Future<?> f = service.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        f.cancel(true);
        System.out.println("Interruping send to" + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlock());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
}
