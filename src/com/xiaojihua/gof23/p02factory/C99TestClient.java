package com.xiaojihua.gof23.p02factory;

import com.xiaojihua.gof23.p02factory.P02factoryMethod.C02AudiFactory;
import com.xiaojihua.gof23.p02factory.P02factoryMethod.C03BydFactory;
import com.xiaojihua.gof23.p02factory.P03AbstractFactory.C01Engine;
import com.xiaojihua.gof23.p02factory.P03AbstractFactory.C04CarFactory;
import com.xiaojihua.gof23.p02factory.P03AbstractFactory.C06LuxuryCarFactory;
import com.xiaojihua.gof23.p02factory.p01simpleFactory.C04SimpleFactory;

public class C99TestClient {

    public static void main(String[] args){
        // 没有工厂模式的时候调用，这时候调用者需要与接口类以及实现类分别打交道
        C01Car audi = new C02Audi();
        C01Car byd = new C03Byd();
        audi.run();
        byd.run();


        /**
         * 1、使用简单工厂模式来创建，此时工厂是一个具体的类
         * 这样调用这只需要跟工厂类打交道即可，但是简单工厂无法做到OCP原则。
         * 因为如果要增加新的车辆还需要修改工厂类
         *
         * 常用
         */
        C01Car audi01 = C04SimpleFactory.createAudi();
        C01Car byd01 = C04SimpleFactory.createByd();
        audi01.run();
        byd01.run();

        /**
         * 2、使用工厂方法来创建，此时满足开闭原则，但是编码量增加，客户端调用
         * 难度增加。
         * 此时有一个工厂接口以及各个汽车专用的工厂实现类
         *
         * 较常用
         */
        C01Car audi02 = new C02AudiFactory().createCar();
        C01Car byd02 = new C03BydFactory().createCar();
        audi02.run();
        byd02.run();


        /**
         * 3、使用抽象工厂来创建。此时比较适用于创建产品族。即有不同的组件构成一个产品
         * 那么通过组合不同的组件，来完成最终产品
         * 满足OCP原则
         */
        C04CarFactory factory = new C06LuxuryCarFactory();
        C01Engine e = factory.createEngine();
        e.run();
        e.start();
    }
}
