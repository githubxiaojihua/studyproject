package com.xiaojihua.javabasic.chapter5;

/**
 * 知识点1：传递this
 * Apple类将自身的实例传递给了Peeler的peel对象，peel方法返回Apple实例。
 * 知识点2：面向对象编程，对于问题域的抽象。
 * 场景：人吃一个削了皮的苹果。
 * 类：Person(人)、Apple(苹果)、Peeler(削皮器)
 * Person: eat(Apple)
 * Apple: getPeeled(this)
 * Peeler: peel
 * 每一个类都完成自己相关的功能，划分清楚。值得借鉴。
 */
public class PassingThis {
    public static void main(String[] args) {
        new Person().eat(new Apple().getPeeled());
    }
}

/**
 * Apple类，包含一个返回削皮后苹果的方法getPeeled
 */
class Apple{
    Apple getPeeled(){
        return Peeler.peel(this);
    }
}

/**
 * 削皮器类。包含一个peel削皮方法，接收一个Apple类
 */
class Peeler{
    static Apple peel(Apple apple){
        // peeled
        return apple;
    }
}

/**
 * 人类：包含吃苹果的动作
 */
class Person{
    public void eat(Apple apple){
        apple = apple.getPeeled();
        System.out.println("eat a peeled apple!");
    }
}
