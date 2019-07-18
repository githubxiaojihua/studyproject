package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 配合着C03DaemonThreadFactory来使用。
 * 通过向newCachedThreadPool传入ThreadFactory来参数化创建Thread
 */
public class C03DaemonFromFactory implements Runnable {
    @Override
    public void run(){
        try{
            while(true){
                System.out.println(Thread.currentThread()+ " " + this);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch(InterruptedException e){
            e.printStackTrace();

        }
    }

    public static void main(String[] args) throws InterruptedException{
        //创建的Thread都是daemon Thread
        ExecutorService service = Executors.newCachedThreadPool(new C03DaemonThreadFactory());
        for(int i=0; i<5; i++){
            service.execute(new C03DaemonFromFactory());
        }
        System.out.println("all daemon thread start");
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
