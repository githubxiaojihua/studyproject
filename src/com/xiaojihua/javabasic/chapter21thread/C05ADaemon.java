package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1、后台线程不会执行finally。
 * 经过试验在jdk8中是可以执行finally的。
 */
public class C05ADaemon implements Runnable {
    @Override
    public void run() {
        try{
            System.out.println("Starting ADaemon");
            TimeUnit.MILLISECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("this is shloud always run?");
        }

    }

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new C05ADaemon());
        t.setDaemon(true);//如果注销这一句的话就会执行finally
        t.start();
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
