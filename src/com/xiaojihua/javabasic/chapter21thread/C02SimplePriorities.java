package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 知识点：
 * 线程优先级的设置。
 * 可以通过Thread.currentThread().setPriority(int)来设置线程的优先级，
 * 可以通过Thread.currentThread().getPriority()来获取线程的优先级。
 * 设置了优先级就会导致高优先级的线程会被优先执行，但并不是说优先级低的就不执行。
 * 就如本例来讲，第六个线程的优先级设置了最高，那么它是优先执行的，然后再执行5，4。。。
 * 再每一个执行时间片中，第六个线程总是在最前面执行。线程高度机制会根据优先级来
 * 进行调度。
 *
 * JDK设置了10级优先级，但是运行的平台不一样，真正的优先级可能没有10个，可能只有
 * 7个或者6个，为了通用性，可以使用Thread.MIN_PRIORITY和Thread.MAX_PRIORITY、NORM_PRIORITY
 * 来设置。
 */
public class C02SimplePriorities implements Runnable {
    private int countDown = 5;
    private int priority;
    //用volatile来修饰变量可保证变量的可视性
    private volatile double d;

    public C02SimplePriorities(int priority){
        this.priority = priority;
    }

    public String toString(){
        //获取当前线程
        return Thread.currentThread() + ":" + countDown;
    }

    @Override
    public void run(){
        /*
            在run方法中设置优先级是合适的，如果在构造方法中设置那时，还没有为此任务
            分配线程呢。
         */
        Thread.currentThread().setPriority(priority);
        while(true){
            /*
                通过模仿大数据计算，来使优先级的效果更加明显。
                每计算到1000次的时候就进行yield()，这就使得，线程在执行过程中可以
                中断一下，让线程调度器可以更明显的将高优先级的线程放到前面执行。
                如果没有这个的话，会导致前5个在第6个线程开始前就已经开始了，
                效果不会那么明显
             */
            for(int i=0; i<1000000; i++){
                d += (Math.PI + Math.E)/(double)i;

                if(i % 1000 == 0){
                    Thread.yield();
                }
            }
            System.out.println(this);
            if(--countDown == 0){
                return;
            }
        }
    }

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0; i<5; i++){
            service.execute(new C02SimplePriorities(Thread.MIN_PRIORITY));
        }
        service.execute(new C02SimplePriorities(Thread.MAX_PRIORITY));
        service.shutdown();
    }

}
