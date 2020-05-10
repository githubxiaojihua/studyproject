package com.xiaojihua.gof23.p03builder;

/**
 * 客户端使用
 *
 * 建造者模式
 * 用于创建一个复杂的产品，而且这个产品的各个子组件组装可以有一定的步骤。
 * 建造者模式将子组件的构建和装配解耦合，从而可以构造出复杂的对象。
 * 适用于某个对象的构建过程比较复杂的情况。不同的构建器和不同的装配器组合可以构建不同的产品
 *
 * 而且可以配合工厂模式或者单例模式等其他模式来创建子组件，本例中子组件的创建没有使用设计模式
 * 但是是可以使用的比如工厂模式创建子组件
 */
public class C09Client {
    public static void main(String[] args){
        C07AirShipDirector director = new C08SxtAirShipDirector(new C06SxtAirShipBuilder());
        C01AirShip airShip = director.createAirShip();

        System.out.println(airShip);
    }
}
