package com.xiaojihua.javabasic.chapter10;

/**
 * 知识点：工厂模式。未使用匿名内部类的工厂模式。
 * 产品接口：Service
 * 工厂接口：ServiceFactory
 * 具体产品类：Implementation1,Implementation2
 * 每个具体产品类对应一个工厂类：Implementation1Factory Implementation2Factory
 *
 * serviceConsumer方法是具体使用Service的地方，它接收ServiceFactory 对象生成Service对象
 * 然后调用具体的Service方法。
 *
 * 工厂模式的优点：如果有一个新的服务，那么可以新增加一个实现Service的类，再增加一个实现ServiceFactory的工厂
 * 这样其他代码都不用动，即可将程序扩展支持新的Service.
 * 这满足了开闭原则（对扩展开放，对修改关闭）
 */

import static com.xiaojihua.javabasic.util.Print.*;

interface Service{
    void method1();
    void method2();
}

interface ServiceFacotry{
    Service getService();
}

class Implementation1 implements Service{
    Implementation1(){}
    public void method1(){
        print("Implementation1 method1");
    }

    public void method2(){
        print("Implementation1 method2");
    }
}

class Implementation1Factory implements ServiceFacotry{
    public Service getService(){
        return new Implementation1();
    }
}

class Implementation2 implements Service{
    Implementation2(){}
    public void method1(){
        print("Implementation2 method1");
    }
    public void method2(){
        print("Implementation2 mechod2");
    }
}

class Implementation2Factory implements ServiceFacotry{
    public Service getService(){
        return new Implementation2();
    }
}
public class Factories {
    public static void serviceConsumer(ServiceFacotry factory){
        Service service = factory.getService();
        service.method1();
        service.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(new Implementation1Factory());
        serviceConsumer(new Implementation2Factory());
    }
}
