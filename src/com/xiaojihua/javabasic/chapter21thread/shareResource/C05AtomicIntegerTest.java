package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 知识点：
 * 使用Atomic class来完成原子性，优化C05AtomicityTest，去掉了Synchronized
 *
 *
 */
public class C05AtomicIntegerTest implements Runnable{
    //保持原子性
    private AtomicInteger ai = new AtomicInteger(0);

    public int getValue(){
        return ai.get();
    }

    public void increment(){
        ai.addAndGet(2);
    }

    @Override
    public void run(){
        while(true){
            increment();
        }
    }

    public static void main(String[] args){
        C05AtomicIntegerTest test= new C05AtomicIntegerTest();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(test);

        //java 定时器的使用
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("abording...");
                System.exit(0);
            }
        }, 5000);
        while(true){
            if(test.getValue() % 2 != 0){
                System.exit(-1);
            }
        }

    }

}
