package com.xiaojihua.javabasic.chapter21.shareResource;

/**
 *
 * int生成器抽象类
 * 知识点：
 * 1、生成器，可以参考泛型部分的生成器代码
 * 2、访问共享资源代码之一
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){ this.canceled = true; }
    public boolean isCanceled(){ return canceled; }
}
