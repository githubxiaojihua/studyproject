package com.xiaojihua.gof23.p19observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标对象的父类对象，也就是被观察对象
 * 持有观察者列表，并可以添加和删除观察这列表
 * 并且有更新所有观察者状态的方法
 */
public class C01Subject {
    private List<C02Observer> obs = new ArrayList<>();

    public void registyObserver(C02Observer observer){
        obs.add(observer);
    }

    public void removeObserver(C02Observer observer){
        obs.remove(observer);
    }

    //通知所有的观察者更新状态
    public void notifyAllObserver(){
        for(C02Observer ob : obs){
            //将当前目标对象传递过去
            ob.update(this);
        }
    }

}
