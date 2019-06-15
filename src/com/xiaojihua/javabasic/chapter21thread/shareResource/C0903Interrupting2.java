package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 知识点：
 * 1、通过使用ReentrantLock而不是synchronized可以中断锁引起的互斥中断。
 * 使用synchronized并不会中断。
 */

/**
 * 构造函数获得锁(在本例中由main线程获得此锁)
 * f为其他线程调用的方法
 */
class BlockedMutex{
    private ReentrantLock lock = new ReentrantLock();
    public BlockedMutex(){
        lock.lock();
    }
    public void f(){
        try {
            lock.lockInterruptibly();//获取锁除非当前线程中断
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("Interrpted from lock acquisition in f()");
        }
    }
}

class Blocked2 implements Runnable{
    private BlockedMutex blockedMutex = new BlockedMutex();
    public void run(){
        System.out.println("Waiting for f() in BlockedMutex");
        blockedMutex.f();//这里将出现阻塞，因为得不到锁
        System.out.println("Broken out of blocked call");
    }
}

public class C0903Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        //main方法所在线程获得锁
        Thread thread = new Thread(new Blocked2());
        thread.start();//新线程阻塞
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        thread.interrupt();//中断新线程的阻塞
    }
}
