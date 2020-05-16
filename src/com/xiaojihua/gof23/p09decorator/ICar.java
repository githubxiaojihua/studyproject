package com.xiaojihua.gof23.p09decorator;

import javax.security.auth.Subject;

// 抽象构件角色
public interface ICar {
    void move();
}

// 具体构件角色（真实对象）
class Car implements ICar{

    @Override
    public void move() {
        System.out.println("普通汽车陆地上跑");
    }
}

// Decorator装饰角色，持有抽象构件的引用，接收客户端的所有请求，并且将请求转发给真实对象
// 这样就能在真实对象调用前后增加新的功能
class SuperCar implements ICar{
    protected ICar car;// 抽象构件用来存储具体的构件（真实对象）

    public SuperCar(ICar car) {
        this.car = car;
    }

    @Override
    public void move() {
        car.move();
    }
}

// 具体装饰角色
class FlyCar extends SuperCar{

    public FlyCar(ICar car) {
        super(car);
    }

    public void fly(){
        System.out.println("增加飞行功能，可以天上飞");
    }

    // 覆盖move方法，里面调用真实对象的move方法
    @Override
    public void move() {
        super.move();
        fly();
    }
}

// 具体装饰角色
class WaterCar extends SuperCar{

    public WaterCar(ICar car) {
        super(car);
    }

    public void swim(){
        System.out.println("增加水上行驶功能，可以水里游");
    }

    // 覆盖move方法，里面调用真实对象的move方法
    @Override
    public void move() {
        super.move();
        swim();
    }
}


// 具体装饰角色
class AICar extends SuperCar{

    public AICar(ICar car) {
        super(car);
    }

    public void autoMove(){
        System.out.println("增加人工只能，可以自动驾驶");
    }

    // 覆盖move方法，里面调用真实对象的move方法
    @Override
    public void move() {
        super.move();
        autoMove();
    }
}