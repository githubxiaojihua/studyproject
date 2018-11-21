package com.xiaojihua.javabasic.chapter10.innerClassUse;

/**
 * 知识点：抽象类
 * 定义抽象类，抽象方法由具体的实现类实现。
 * 通过抽象类将不变的逻辑跟可变的逻辑进行分离
 */
public abstract class Event {
    private long eventTime;
    public long delayTime;
    Event(long delayTime){
        this.delayTime = delayTime;
        start();
    }
    public void start(){
        this.eventTime = System.nanoTime() + delayTime;
    }
    public boolean ready(){
        return System.nanoTime() >= eventTime;
    }
    //可变逻辑，由实现类实现
    public abstract void action();
}
