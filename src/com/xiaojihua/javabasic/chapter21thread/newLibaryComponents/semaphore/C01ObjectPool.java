package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 知识点
 * Semaphore的使用，Semaphore相当于一个独立的计数器，在初始化的时候给他一个计数器的初始值，
 * 当调用acquire()方法的时候如果当前计数器不为零则允许线程继续操作，并将计数器的数量减少
 * ，如果计数器为0那么就会阻塞当前线程。当调用release()方法的时候会增加计数器的值。
 *
 * Semaphore通常用于限制访问一个资源的线程数量。
 *
 * 当Semaphore的初始化值设置为1的时候可以当作互斥锁来使用，但是与互斥锁不一样的地方在于，
 * 锁的释放可以由其他线程来执行。
 * @param <T>
 */
public class  C01ObjectPool<T> {
    private List<T> objectList = new ArrayList<>();
    private volatile boolean[] checkOut;
    private Semaphore semaphore;

    public C01ObjectPool(Class<T> tClass, int size) throws InstantiationException,IllegalAccessException {
        checkOut = new boolean[size];
        //初始化semaphore，ture的保证了FIFO
        semaphore = new Semaphore(size,true);
        for(int i=0; i<size; i++){
            objectList.add(tClass.newInstance());
        }
    }

    public T checkOut() throws InterruptedException{
        //先看是否还允许获取（计数器是否为0），为零则挂起当前线程
        //如果不为0则减少计数器，然后调用getItem方法，getItem方法
        //本身也有synchronized锁机制，与semaphore相互独立
        //acquire本身并不获取对象的锁
        semaphore.acquire();
        //调用获取方法（此方法有独立的锁）
        return getIitem();
    }

    /**
     * Pool对象本身的保持一致性的锁机制
     * @return
     */
    private synchronized T getIitem(){
        for(int i=0; i<objectList.size(); i++){
            if(!checkOut[i]){
                checkOut[i] = true;
                return objectList.get(i);
            }
        }
        return null;
    }

    public void checkIn(T item){
        if(release(item)){
            semaphore.release();
        }
    }

    private synchronized boolean release(T item){
        int index = objectList.indexOf(item);
        if(index == -1){
            return false;
        }
        if(checkOut[index]){
            checkOut[index] = false;
            return true;
        }
        return false;
    }
}
