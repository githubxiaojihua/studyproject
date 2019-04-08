package com.xiaojihua.javabasic.disignPattern.decorator.common;


/**
 * 具体的装饰器
 */
public class C05ConcreteDecoratorB extends C03Decorator {
    public C05ConcreteDecoratorB(C01Component component){
        super(component);
    }

    @Override
    public void simpleOperation(){
        //增加的业务逻辑
        System.out.println("ConcreteDecratorB.simpleOperation");
        super.simpleOperation();

    }
}
