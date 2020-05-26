package com.xiaojihua.gof23.p19observer;

/**
 * 观察者抽象接口
 * 持有更新状态的方法，共目标对象调用
 */
public interface C02Observer {
    void update(C01Subject subject);
}
