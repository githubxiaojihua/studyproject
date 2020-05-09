package com.xiaojihua.gof23.p02factory.p01simpleFactory;

import com.xiaojihua.gof23.p02factory.C01Car;
import com.xiaojihua.gof23.p02factory.C02Audi;
import com.xiaojihua.gof23.p02factory.C03Byd;

/**
 * 简单工厂:也叫静态工厂模式，因为方法都是静态的
 */
public class C04SimpleFactory {

    public static C01Car createAudi(){
        return new C02Audi();
    }

    public static C01Car createByd(){
        return new C03Byd();
    }
}
