package com.xiaojihua.javabasic.disignPattern.decorator.common;

/**
 * 具体的构件类，需要在此类上增加附加功能
 */
public class C02ConcreteCompoment implements C01Component {
    public void simpleOperation(){
        //相关业务逻辑
        System.out.println("ConcreteComponent Operation!");
    }
}
