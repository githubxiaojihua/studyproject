package com.xiaojihua.javabasic.chapter21thread.shareResource;

/**
 * 保证serialNumber的可见性和线程安全性
 */
public class C05SerialNumberGen {
    private static int serialNum = 0;

    synchronized public int next(){
        return serialNum++;//非原子操作，因此是非线程安全的
    }
}
