package com.xiaojihua.gof23.p01singleton;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;

/**
 * 使用多线程来验证各个单例模式的效率
 */
public class C08Client03 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException, InterruptedException {
        long start = System.currentTimeMillis();
        int threadNum = 10;
        // CountDownLatch类的使用可以参考教程中（pdf）的说明
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for(int i=0;i<threadNum;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {

                    for(int i=0;i<1000000;i++){
                        //Object o = SingletonDemo4.getInstance();
                        Object o = C01Singleton01.getInstance();
                    }

                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();	//main线程阻塞，直到计数器变为0，才会继续往下执行！

        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end-start));

    }
}
