package com.xiaojihua.javabasic.chapter21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1、通过同步队列实现进程之间的协作。
 * 2、本例模拟制作和食用寿司：
 * Toast寿司类：通过使用枚举类改变寿司的状态（干的寿司，涂香油，涂果酱）
 * Toaster寿司制作者（线程）通过一个同步队列dryQueue将初始状态下的寿司入队。
 * Butter涂香油（线程），通过从dryQueue队列中取出寿司，然后涂香油，然后投入butterQueue
 * Jammer涂果酱（线程），通过从butterQueue队列中取出寿司，然后投入finishQueue.
 * Eater实用者（线程），通过从finishQueue取出寿司使用。
 * 整个过程从上到下依次进行，并功过各个同步队列达成完整的统一，顺序一次执行一个个制作
 * 寿司的过程。
 * 3、枚举类型的使用
 */

/**
 * 寿司类，注意枚举类型的使用
 */
class Toast{
    /**
     * 这里必须是public的，因为在其他类中有使用
     */
    public enum Status{
        DRY, BUTTERED, JAMMED
    }
    private Status status = Status.DRY;
    private final int id;
    public Toast(int id){
        this.id = id;
    }
    public void butter(){
        status = Status.BUTTERED;
    }
    public void jam(){
        status = Status.JAMMED;
    }
    public Status getStatus(){
        return status;
    }
    public int getId(){
        return id;
    }
    public String toString(){
        return "Toast " + id + ":" + status;
    }
}

/**
 * 通过继承创造ToastQueue类
 */
class ToastQueue extends LinkedBlockingQueue<Toast>{}

/**
 * 制作寿司任务
 */
class Toaster implements Runnable{
    private ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random(47);
    public Toaster(ToastQueue queue){
        toastQueue = queue;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                Toast toast = new Toast(count++);
                System.out.println(toast);
                toastQueue.put(toast);
            }
        }catch (InterruptedException e){
            System.out.println("Toaster Interrupted");
        }
        System.out.println("Toaster off");
    }
}

/**
 * 上香油任务
 */
class Butterer implements Runnable{
    private ToastQueue dryQueue, butterQueue;
    public Butterer(ToastQueue dryQueue, ToastQueue butterQueue){
        this.dryQueue = dryQueue;
        this.butterQueue = butterQueue;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butterQueue.put(t);
            }
        }catch(InterruptedException e){
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}

/**
 * 涂果酱任务
 */
class Jammer implements Runnable{
    private ToastQueue butterQueue, finishQueue;
    public Jammer(ToastQueue butterQueue, ToastQueue finishQueue){
        this.butterQueue = butterQueue;
        this.finishQueue = finishQueue;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                Toast t = butterQueue.take();
                t.jam();
                System.out.println(t);
                finishQueue.put(t);
            }
        }catch(InterruptedException e){
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }

}

/**
 * 消费者任务
 */
class Eater implements Runnable{
    private ToastQueue finishQueue;
    private int count = 0;
    public Eater(ToastQueue finishQueue){
        this.finishQueue = finishQueue;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                Toast t = finishQueue.take();
                if(t.getId() != count++ || t.getStatus() != Toast.Status.JAMMED){
                    System.out.println(">>>>>error:" + t);
                    System.exit(1);
                }else{
                    System.out.println("Chomp! " + t);
                }
            }
        }catch(InterruptedException e){
            System.out.println("Eater Interrupted");
        }
        System.out.println("Eater off");
    }
}


public class C14ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(),
                butterQueue = new ToastQueue(),
                finishQueue = new ToastQueue();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Toaster(dryQueue));
        service.execute(new Butterer(dryQueue,butterQueue));
        service.execute(new Jammer(butterQueue,finishQueue));
        service.execute(new Eater(finishQueue));
        TimeUnit.SECONDS.sleep(5);
        service.shutdownNow();
    }
}
