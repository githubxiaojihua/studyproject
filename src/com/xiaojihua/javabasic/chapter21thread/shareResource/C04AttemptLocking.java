package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 知识点：显式锁的应用
 * 1、可以尝试请求锁定tryLock。
 * 2、可以显式解除锁定unluck
 * 3、匿名Thread的使用
 */
public class C04AttemptLocking {
    private Lock lock = new ReentrantLock();

    public void untimed(){
        boolean captured = lock.tryLock();
        try{
            System.out.println("trylock():" + captured);
        }finally{
            if(captured){
                lock.unlock();
            }
        }
    }

    public void timed(){
        boolean captured = false;
        try {
            captured = lock.tryLock(2,TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            System.out.println("tryloc(timed):" + captured);
        } finally {
            if(captured){
                lock.unlock();//可以尝试将此行注销，观察匿名thread的锁获取情况
            }
        }

    }

    public static void main(String[] args) {
       final C04AttemptLocking al = new C04AttemptLocking();

        al.untimed();;
        al.timed();

        //建立一个后台进程请求锁定
        Thread t = new Thread(){
            {setDaemon(true);}
            public void run(){
                if(al.lock.tryLock())
                    System.out.println("acquired");
                else
                    System.out.println("not acquired");
            }
        };
        t.start();
        Thread.yield();


        al.untimed();
        al.timed();
    }
}
