package com.xiaojihua.gof23.p19observer.javaAPI;

public class C03Client {
    public static void main(String[] args) {
        //调用的方式与自己实现的一致
        //创建目标对象
        C01ConcreteSubject subject = new C01ConcreteSubject();
        //创建观察者对象
        C02ObserverA obs1 = new C02ObserverA();
        C02ObserverA obs2 = new C02ObserverA();
        C02ObserverA obs3 = new C02ObserverA();
        //注册观察者
        subject.addObserver(obs1);
        subject.addObserver(obs2);
        subject.addObserver(obs3);
        //改变目标对象的状态
        subject.setStatus(1000);
        System.out.println("***************目标对象的状态改变了");
        //查看观察者的状态
        System.out.println(obs1.getMyStatus());
        System.out.println(obs2.getMyStatus());
        System.out.println(obs3.getMyStatus());

        //改变目标对象的状态
        subject.setStatus(3000);
        System.out.println("***************目标对象的状态改变了");
        //查看观察者的状态
        System.out.println(obs1.getMyStatus());
        System.out.println(obs2.getMyStatus());
        System.out.println(obs3.getMyStatus());
    }
}
