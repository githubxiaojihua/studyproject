package com.xiaojihua.javabasic.chapter21thread.shareResource;

/**
 * 通过synchronized块指定锁定的对象，一般情况下都写synchronized(this)
 * 这样一旦进程获得锁其他进程则不能再操作，
 * 如果指定其他对象锁则可以，相当于锁是锁到了其他对象上，因此可以多个线程功能访问
 * 一个对象。
 * 但是要注意如果是防止线程挣用一个资源，那么应该保证，加锁的对象应该一致。
 * 否则起不到作用
 */
public class C06SyncObject {
    public static void main(String[] args){
        final DualSynch dualSynch = new DualSynch();
        //新启动一个线程
        new Thread(){
            @Override
            public void run(){
                dualSynch.f();
            }
        }.start();
        //main线程
        dualSynch.g();
    }
}

class DualSynch{
    private Object syncObject = new Object();

    //在本对象上加锁
    public synchronized void f(){
        for(int i=0; i<5; i++){
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g(){
        //在其他对象上加锁
        synchronized(syncObject){
            for(int i=0; i<5; i++){
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}
