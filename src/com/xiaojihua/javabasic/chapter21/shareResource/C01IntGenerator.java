package com.xiaojihua.javabasic.chapter21.shareResource;

/**
 *
 * int生成器抽象类
 * 知识点：
 * 1、生成器，可以参考泛型部分的生成器代码
 * 2、访问共享资源代码之一
 * 3、volatile的使用，这牵扯到原子性和可视性，以及java虚拟机的内存模型，可以参考读书笔记并发部分的笔记。
 */
public abstract class C01IntGenerator {
    private volatile boolean canceled = false;//为了保证可视性增加volatile
    public abstract int next();
    public void cancel(){ this.canceled = true; }
    public boolean isCanceled(){ return canceled; }
}
