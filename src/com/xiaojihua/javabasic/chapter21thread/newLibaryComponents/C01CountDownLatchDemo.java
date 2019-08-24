package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点:
 * java.util.concurrent包中的工具类之：CountDownLatch
 * CountDownLatch可以用于以下情景：
 * 一个任务或者多个任务，必须等到一定数量的其他任务完成以后才能开始。
 * 给一个CountDownLatch对象传递一个int类型的计数器，任务在此对象上
 * 调用await()来挂起，直到有其他任务调用CountDownLatch对象的
 * countDown()方法来将int减少为0。
 * 比如本例中，将一个CountDownLatch对象初始化为100，然后初始化了
 * 100个PartTask任务，每个任务都共享同一个CountDownLatch对象
 * 每个任务结束后都会调用countDown()方法来减少计数器。同时初始化了5个
 * 等待任务，这些任务在CountDownLatch对象计数器为零之前都会挂起。
 * 等到计数器为0后等待任务才执行。
 */
public class C01CountDownLatchDemo {
    public static int SIZE = 100;

    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(SIZE);
        ExecutorService service = Executors.newCachedThreadPool();
        int waitSize = 10;
        for(int i=0; i<waitSize; i++){
            service.execute(new WaitCountLatch(latch));
        }

        for(int i=0; i<SIZE; i++){
            service.execute(new PartTask(latch));
        }

        System.out.println("Lanched all task");
        service.shutdown();
    }
}

class PartTask implements Runnable{
    private static int count = 0;
    private final int id = count++;
    private final CountDownLatch latch;
    //是线程安全的因此可以使用static
    private static Random random = new Random(47);

    public PartTask(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run(){
        try{
            work();
            latch.countDown();

        }catch(InterruptedException e){
            System.out.println("interrupted " + e);
        }
    }

    public void work() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
        /*
            输出语句放到这里，输出比较规范，会先所有的PartTask输出完成后
            才输出waitCountLatch的
            如果将以下这句放到上面的run方法中则会混乱输出。
            或者将这句话放到work()之后countDown方法之前也可以。
         */
        System.out.println("ParTask " + this + "complete.");
    }

    public String toString(){
        return String.format("%1$-3d",id);
    }
}


class WaitCountLatch implements Runnable{
    private static int count = 0;
    private final int id = count++;
    private CountDownLatch latch;

    public WaitCountLatch(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run(){
        try{
            latch.await();
            System.out.println("WaitCountLatch pass " + this);
        }catch(InterruptedException e){
            System.out.println("interrupted " + e);
        }

    }

    public String toString(){
        return String.format("%1$-3d",id);
    }
}