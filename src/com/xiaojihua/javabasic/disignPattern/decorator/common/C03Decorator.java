package com.xiaojihua.javabasic.disignPattern.decorator.common;

/**
 * 装饰器父类：持有构件，并实现与构件统一的接口
 */
public class C03Decorator implements C01Component {
    private C01Component compoment;
    public C03Decorator(C01Component compoment){
        this.compoment = compoment;
    }

    @Override
    public void simpleOperation(){
        //这里一般什么也不做，只调用构件方法。
        //System.out.println("Decorator Operation before Compoment!");
        compoment.simpleOperation();
    }
}
