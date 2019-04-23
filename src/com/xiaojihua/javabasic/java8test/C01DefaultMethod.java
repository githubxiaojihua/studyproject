package com.xiaojihua.javabasic.java8test;

/**
 * 知识点：
 * 1、默认方法：java8为接口新增了默认方法。简单来说就是接口可以有
 * 实现方法，而且不需要实现类去实现。语法是在方法前面增加default。
 *
 * 为什么要有这个特性：
 * 首先，之前的接口是个双刃剑，好处是面向抽象而不是面向具体编程，
 * 缺陷是，当需要修改接口时候，需要修改全部实现该接口的类，目前的
 * java 8 之前的集合框架没有 foreach 方法，通常能想到的解决办法
 * 是在JDK里给相关的接口添加新的方法及实现。然而，对于已经发布的
 * 版本，是没法在给接口添加新方法的同时不影响已有的实现。所以引进
 * 的默认方法。他们的目的是为了解决接口的修改与现有的实现不兼容的问题。
 */
public class C01DefaultMethod {
    public static void main(String[] args){
        Vehicle car = new Car();
        car.print();

    }
}

interface Vehicle{
    /**
     * 默认方法
     */
    default void print(){
        System.out.println("我是一辆车！");
    }

    /**
     * 同时可以有静态默认方法
     */
    static void blowHorn(){
        System.out.println("按喇叭！");
    }
}

interface FourWheels{
    /**
     * 默认方法
     */
    default void print(){
        System.out.println("我是一辆四轮车！");
    }
}

/**
 * 当一个类继承了两个包含同样默认方法的接口时，有两种方式
 * 来解决否则会有编译错误：
 * 1、创建自己的默认方法，来覆盖接口的方法（ default void print()）
 * 2、通过super来调用指定的接口方法
 */
class Car implements Vehicle, FourWheels{

    public void print(){
        Vehicle.super.print();
        FourWheels.super.print();
        Vehicle.blowHorn();//调用静态方法
        System.out.println("我是一辆汽车耦！");
    }
}