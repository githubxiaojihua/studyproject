package com.xiaojihua.gof23.p05adapter;

/**
 * 使用继承的方式来实现适配器
 */
public class C04Adapter01 extends C01Adaptee implements C02Target {


    @Override
    public void handleRequest() {
        super.request();
    }
}
