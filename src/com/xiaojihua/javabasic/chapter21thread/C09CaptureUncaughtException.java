package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 知识点：
 * 1、从线程中抛出的异常无法进行常规捕获（比如使用try catch)。这些异常一般会直接抛出到控制台，即使使用trycatch
 * 包围也捕获不到。但是可以使用Executor的相关方法来捕获。
 * 2、Thread.UncaughtExceptionHandler的使用：允许在每一个Thread上附着一个异常处理器，专门用于捕获线程抛出的未被捕获的异常
 * 3、ThreadFactory的使用，创建具有不同属性特点的Thread
 */
class ExceptionThread implements Runnable{

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());//获取任务当前线程的UncaughtExceptionHandler
        throw new RuntimeException();//抛出异常
    }
}

/**
 * 创建自定义UncaughtExceptionHandler
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e);//打印异常信息
    }
}

/**
 * 工厂模式，能创建指定自定义UncaughtExceptionHandler线程的工厂。
 */
class HandlerThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + "creating a new Thread");
        Thread t = new Thread(r);
        System.out.println("created a Thread " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh:" + t.getUncaughtExceptionHandler());
        return t;
    }
}

public class C09CaptureUncaughtException {
    public static void main(String[] args) {
        //通过ExecutorService来执行任务，能够捕获UncaughtException
        ExecutorService service = Executors.newCachedThreadPool(new HandlerThreadFactory());
        service.execute(new ExceptionThread());
        service.shutdown();
    }
}
