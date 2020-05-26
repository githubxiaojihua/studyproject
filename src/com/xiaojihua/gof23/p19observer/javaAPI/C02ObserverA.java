package com.xiaojihua.gof23.p19observer.javaAPI;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体的观察者对象，实现了java提供的Observer接口
 */
public class C02ObserverA implements Observer {
    private int myStatus;

    @Override
    public void update(Observable o, Object arg) {
        myStatus = ((C01ConcreteSubject)o).getStatus();
    }

    public int getMyStatus() {
        return myStatus;
    }

    public void setMyStatus(int myStatus) {
        this.myStatus = myStatus;
    }
}
