package com.xiaojihua.javabasic.disignPattern.decorator.common;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 装饰器设计模式的使用。
 * 装饰器模式：装饰模式又称包装（wapper)模式，装饰器模式以对客户端透明的方式扩展对象功能，
 * 是继承关系的一个替代方案。
 * 装饰器以对客户端透明的方式<p>动态</p>的给一个对象附加上更多的附加功能，对对象进行了扩展。
 * 装饰器模式可以在不创造对象更多子类的前提下扩展对象。
 * 在标准的装饰器模式中一般有四类角色：
 * 1、抽象的构件角色（Component):一个接口用于规范准备接收附加功能的对象。
 * 2、具体的构件角色（ConcreteCompoment）:将要接收附加功能的对象。
 * 3、装饰器角色（Decorator)：持有一个构件角色的引用，并且实现抽象构件接口。
 * 4、具体的装饰器角色（ConcreteDecoratorA、ConcreteDecoratorB):为具体的构建角色
 * 增加附加功能。
 *
 * 关于更详细的装饰器模式可以参考mybase笔记中的相关文章
 */
public class C06Test {
    public static void main(String[] args){
        print("原始的component：");
        C01Component component = new C02ConcreteCompoment();
        component.simpleOperation();
        print("应用了A装饰器以后：");
        C01Component componentA = new C04ConcreteDecoratorA(component);
        componentA.simpleOperation();
        print("应用了B装饰器以后：");
        C01Component componentB = new C05ConcreteDecoratorB(component);
        componentB.simpleOperation();
        print("应用了A和B装饰器以后：");
        C01Component componentAandB = new C05ConcreteDecoratorB(new C04ConcreteDecoratorA(component));
        componentAandB.simpleOperation();
    }
}
