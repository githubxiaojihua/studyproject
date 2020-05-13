package com.xiaojihua.gof23.p06proxy.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理（使用jdk动态代理技术实现）
 * 动态代理就是自动生成代理对象
 */
public class C04Client {
    public static void main(String[] args) {
        C01Star realStar = new C02RealStar();
        C03StarHandler handler = new C03StarHandler(realStar);

        C01Star proxyStar = (C01Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[]{C01Star.class}, handler);
        proxyStar.bookTicket();
        proxyStar.sing();
    }
}
