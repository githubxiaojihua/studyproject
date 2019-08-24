package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 通过semaphore来限制访问资源的数量
 */
public class C03SemaphoreDemo {
    private static final int SIZE = 25;
    public static void main(String[] args) throws Exception{
        C01ObjectPool<C02Fat> pool = new C01ObjectPool<>(C02Fat.class,SIZE);
        ExecutorService service = Executors.newCachedThreadPool();
        List<C02Fat> fats = new ArrayList<>();

        //初始化与pool中对象数量一直的线程，来获取和释放pool中的对象
        for(int i=0; i<SIZE; i++){
            service.execute(new CheckOutTask<C02Fat>(pool));
        }
        System.out.println("all checkoutask is execute");

        /*
            只获取但是不是放pool中的对象
         */
        for(int i=0; i<SIZE; i++){
            C02Fat fat = pool.checkOut();
            System.out.println(i + ": main() thread checkout");
            fat.operation();
            fats.add(fat);
        }

        /*
            当执行此线程的时候，会阻塞，因为上面的for循环将pool中所有的
            对象都checkout了，但是没有一个被checkin，因此semaphore计数器
            为0，不允许再有新的线程继续操作，那么就只有阻塞。
            使用submit和Future的原因是可以第调用Future的cancel方法来中断
            阻塞的线程。
         */
        Future<?> future = service.submit(() ->{
            try{
                pool.checkOut();
            }catch(InterruptedException e){
                System.out.println("checkout interrupted");
            }
        });
        TimeUnit.MILLISECONDS.sleep(2000);
        //过一段时间后中断线程调用
        future.cancel(true);

        /**
         * 在checkin方法中进行了判断因此，重复的释放并不会影响
         * pool中的对象以及semaphore
         */
        System.out.println("Checking in object in " + fats);
        for(C02Fat fat : fats){
            pool.checkIn(fat);
        }

        for(C02Fat fat : fats){
            pool.checkIn(fat);
        }

        service.shutdown();

    }
}

class CheckOutTask<T> implements Runnable{
    private static int count = 0;
    private final int id = count++;
    private C01ObjectPool<T> pool;

    public CheckOutTask(C01ObjectPool<T> pool){
        this.pool = pool;
    }

    @Override
    public void run(){
        try{
            T obj = pool.checkOut();
            System.out.println(this + " checkout " + obj);
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(this + " checking " + obj);
            pool.checkIn(obj);

        }catch(InterruptedException e){
            System.out.println("Interrupted CheckoutTask");
        }

    }

    @Override
    public String toString(){
        return " CheckOutTask:" + id;
    }

}
