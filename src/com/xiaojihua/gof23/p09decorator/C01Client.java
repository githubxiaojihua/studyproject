package com.xiaojihua.gof23.p09decorator;

/**
 * 客户端测试
 */
public class C01Client {
    public static void main(String[] args) {
        Car realCar = new Car();
        realCar.move();

        System.out.println("开始增加飞行功能-------");
        // 增加飞行功能
        SuperCar flyCar = new FlyCar(realCar);
        flyCar.move();

        System.out.println("开始增加游泳功能-------");
        // 增加飞行功能
        SuperCar waterCar = new WaterCar(realCar);
        waterCar.move();

        System.out.println("开始增加飞行和潜水功能-------");
        // 增加飞行功能
        SuperCar flyWaterCar = new WaterCar(new FlyCar(new Car()));
        flyWaterCar.move();

    }
}
