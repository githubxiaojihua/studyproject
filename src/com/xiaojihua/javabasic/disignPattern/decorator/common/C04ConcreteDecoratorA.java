package com.xiaojihua.javabasic.disignPattern.decorator.common;

/**
 * 具体的装饰器
 */
public class C04ConcreteDecoratorA extends C03Decorator {

    public C04ConcreteDecoratorA(C01Component component){
        super(component);
    }

    @Override
    public void simpleOperation(){

        //增加的业务逻辑
        System.out.println("ConcreteDecoratorA.simpleOperation!");
        super.simpleOperation();
    }
}
