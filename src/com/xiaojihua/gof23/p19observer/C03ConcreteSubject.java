package com.xiaojihua.gof23.p19observer;

/**
 * 具体的目标对象,
 * 持有具体的状态，以及修改状态的后续操作，调用父类的通知方法
 */
public class C03ConcreteSubject extends C01Subject {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        //主题对象(目标对象)值发生了变化，请通知所有的观察者
        this.notifyAllObserver();
    }
}
