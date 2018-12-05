package com.xiaojihua.javabasic.chapter21;

import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1、后台线程。所谓的后台线程是指在程序运行期间在后台提供一种通用服务的线程，当所有非后台线程结束时，程序终止了，同时会杀死所有的后台线程。
 */
public class C03SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try{
            //在这里并没有设置线程退出循环的挑件。本后台线程根据是否还有非后台线程而存在。
            while(true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " +this);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10; i++){
            Thread t = new Thread(new C03SimpleDaemons());
            t.setDaemon(true);//设置为后台线程
            t.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(127);//休眠结束后所有的非后台线程结束，程序结束，后台线程也结束。
    }
}
