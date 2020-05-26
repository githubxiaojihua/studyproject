package com.xiaojihua.gof23.p19observer.javaAPI;

import java.util.Observable;

/**
 * 继承自java提供的目标类，相当于自己写的C03ConcreteSubject，只是
 * 继承自了java提供的Observable类
 */
public class C01ConcreteSubject extends Observable {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        //调用父类对象方法，表示目标对象已经更新
        setChanged();
        //通知所有观察者
        notifyObservers();
    }
}
