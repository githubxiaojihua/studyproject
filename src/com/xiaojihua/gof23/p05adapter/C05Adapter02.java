package com.xiaojihua.gof23.p05adapter;

/**
 * 使用组合的方式来实现适配器
 */
public class C05Adapter02 implements C02Target {

    private C01Adaptee adaptee;

    public C05Adapter02(C01Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void handleRequest() {
        adaptee.request();
    }
}
