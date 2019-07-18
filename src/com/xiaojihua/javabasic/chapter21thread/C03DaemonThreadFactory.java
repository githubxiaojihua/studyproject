package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.ThreadFactory;

/**
 * 通过实现ThreadFactory来设置在生成Thread的时候，对Thread进行一些参数化
 * 设置。可以配合C03DaemonFromFactory来使用。将ThreadFactory传入Executors.newCashedThreadsPool
 * 或者是Executors.fixThreadPool等线程池作为参数。可以做做到参数化线程创建
 */
public class C03DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable runnable){
        //设置所创建的线程都是daemon Thread
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        return t;
    }
}
