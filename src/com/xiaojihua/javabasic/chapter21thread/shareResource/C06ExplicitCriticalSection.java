package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock代替synchronized，但是效果上是不对的，运行的时候会因为
 * X,Y不一致而抛出异常
 */
public class C06ExplicitCriticalSection {
    public static void main(String[] args){
        PairManager pm1 = new ExplicitManager1(),
                pm2 = new ExplicitMnager2();
        C06CriticalSection.testApproaches(pm1,pm2);
    }
}

class ExplicitManager1 extends PairManager{
    private Lock lock = new ReentrantLock();
    @Override
    synchronized public void increment(){
        //这里不用trylock，trylock是尝试获取，如果获取不到立即返回false
        //也可以设置超时返回。
        //lock和Synchronized获得的锁都是Object的锁
        lock.lock();
        try{

            p.incrementX();
            p.incrementY();
            //一定要使用getPair不能直接store(p)，因为p本身是非线程安全的
            //通过getPair()保证了他的线程安全性
            store(getPair());
        }finally{
            lock.unlock();
        }
    }
}

class ExplicitMnager2 extends PairManager{
    Lock lock = new ReentrantLock();
    @Override
    public void increment(){

        Pair temp = null;
        lock.lock();
        try{

            p.incrementX();
            p.incrementY();
            //一定要使用getPair不能直接store(p)，因为p本身是非线程安全的
            //通过getPair()保证了他的线程安全性
            temp = getPair();
        }finally{
            lock.unlock();
        }
        store(temp);
    }
}
