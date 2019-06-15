package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 知识点：
 * 1、通过ReentrantLock和Condition来实现前面的C10WaxOMatic.java。
 * 2、通过lock创建Condition，可以通过await()来挂起一个任务，通过signal和signalAll
 * 来唤醒任务，其用法和wait\notify\notifyall差不多。condition操作的是获得当前lock的
 * 线程。
 * 3、Lock属于显式锁与synchronized隐式锁相比，有更多的灵活性。比如，提供了tryLock，当
 * 获取不到锁的时候可以让程序做其他的工作，但是隐式锁没有这样的灵活性。但是隐式锁更优雅，编码
 * 也更少。LOCK和Condition使用在比较复杂的的多线程问题中。
 * 4、每个lock()后面都应该有try finally语句来释放锁。await,signal,singalall必须在获得
 * lock锁以后才能使用。
 */
class Car2{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;

    public void waxed(){
        lock.lock();//获取锁，如果锁不可用线程休眠，并直到锁可用为止。
        try{
            waxOn = true;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void buffed(){
        lock.lock();;
        try{
            waxOn = false;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try{
            while(waxOn == false){
                condition.await();
            }
        }finally {
            lock.unlock();
        }

    }

    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try{
            while(waxOn == true){
                condition.await();
            }

        }finally {
            lock.unlock();
        }
    }
}

class WaxOn1 implements Runnable{
    private Car2 car;
    public WaxOn1(Car2 car){
        this.car = car;
    }

    public void run(){
        try{
            while(!Thread.interrupted()){
                System.out.println("Wax on");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }

        }catch (InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff2 implements Runnable{
    private Car2 car;
    public WaxOff2(Car2 car){
        this.car = car;
    }

    public void run(){
        try{
            while(!Thread.interrupted()){
                car.waitForWaxing();
                System.out.println("Wax Off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        }catch(InterruptedException e){
            System.out.println("Exiting via Interrupt");
        }
        System.out.println("Ending wax off task");
    }
}

public class C10WaxOMaticByReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        Car2 car = new Car2();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new WaxOn1(car));
        service.execute(new WaxOff2(car));
        TimeUnit.SECONDS.sleep(5);
        service.shutdownNow();
    }
}
