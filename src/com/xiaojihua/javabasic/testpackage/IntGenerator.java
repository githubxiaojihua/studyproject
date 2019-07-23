package com.xiaojihua.javabasic.testpackage;

/**
 * 需求：
 * 多个线程共享一个int生成器，通过生成器产生偶数，
 * 线程检测如果生成的不是偶数则终止程序
 * IntGenerator
 * EvenGenerator extends IntGenerator
 * EvenChecker implements Runnable
 */
public abstract class IntGenerator {
    private volatile boolean cancle = false;

    public void cancle(){
        cancle = true;
    }

    public boolean isCancle(){
        return cancle;
    }

    abstract int next();
}
