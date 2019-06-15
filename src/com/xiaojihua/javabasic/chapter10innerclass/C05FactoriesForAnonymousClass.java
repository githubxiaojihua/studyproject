package com.xiaojihua.javabasic.chapter10innerclass;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：使用匿名内部类实现工厂设计模式
 * 工厂设计模式的一个最大的用途就是解耦。
 * 具体到下面的代码：在使用某个具体的ServiceForAnonymousClass的实现类的时候，不用去new 因为new出来的耦合度都比较高，
 * 一旦需要扩展或者修改的时候所有
 */
interface ServiceForAnonymousClass{
    void service1();
    void service2();
}

interface ServiceFactoryForAnonymousClass{
    ServiceForAnonymousClass getService();
}

class Implementation1ForService implements ServiceForAnonymousClass{
    public void service1(){
        print("ImplementationForService1 service1");
    }
    public void service2(){
        print("ImplementationForService1 service2");
    }
    public static ServiceFactoryForAnonymousClass factory =
            new ServiceFactoryForAnonymousClass() {
                @Override
                public ServiceForAnonymousClass getService() {
                    return new Implementation1ForService();
                }
            };
}

class Implementation2ForService implements ServiceForAnonymousClass{
    public void service1(){
        print("Implementation2ForService service1");
    }
    public void service2(){
        print("Implementation2ForService service2");
    }
    public static ServiceFactoryForAnonymousClass factory =
            new ServiceFactoryForAnonymousClass() {
                @Override
                public ServiceForAnonymousClass getService() {
                    return new Implementation2ForService();
                }
            };
}


public class C05FactoriesForAnonymousClass {
    public static void serviceConsumer(ServiceFactoryForAnonymousClass factory){
        ServiceForAnonymousClass service = factory.getService();
        service.service1();
        service.service2();
    }

    public static void main(String[] args) {
        serviceConsumer(Implementation1ForService.factory);
        serviceConsumer(Implementation1ForService.factory);
    }
}
