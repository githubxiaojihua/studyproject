package com.xiaojihua.javabasic.chapter21.shareResource;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 使用threadLocal进行共享资源的控制。每个线程都有一个自己的副本.
 * threadlocal就是线程的本地存储。
 */
class Accessor implements Runnable{
    private final int id;
    Accessor(int id){ this.id = id; }
    @Override
    public void run() {
        //循环直到线程被中断
        while(!Thread.currentThread().isInterrupted()){
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }
    public String toString(){
        return "#" + id + ":" + ThreadLocalVariableHolder.get();
    }
}

public class ThreadLocalVariableHolder {
    //声明ThreadLocal变量并生成初始值
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random rand = new Random(47);
        //指定初始值。这里加synchronized和不加是没有区别的
        protected synchronized Integer initialValue(){
            return rand.nextInt(1000);
        }
    };

    /**
     * 通过value.set设置新值。只能通过set设置
     */
    public static void increment(){
        value.set(value.get() + 1);
    }

    /**
     * 通过value.get获取新值。只能通过set获取
     * @return
     */
    public static Integer get(){ return value.get(); }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++){
            service.execute(new Accessor(i));
        }
        TimeUnit.MILLISECONDS.sleep(5);
        //shutdownNow是主动尝试停止当前正在执行的线程，并且停止等待处理的进程，并返回等待处理的进程
        //shutdown是有序停止正在执行的线程,停止动作依赖于线程本身，如果线程自己不停止的话
        //shutdown是不主动关闭的。并且不再接受新的线程请求
        service.shutdownNow();
        //service.shutdown();
    }
}
