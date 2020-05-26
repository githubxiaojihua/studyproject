package com.xiaojihua.gof23.p19observer;

public class C05Client {
    public static void main(String[] args) {
        //目标对象
        C03ConcreteSubject subject = new C03ConcreteSubject();
        //创建多个观察者
        C02Observer observer1 = new C04ObserverA();
        C02Observer observer2 = new C04ObserverA();
        C02Observer observer3 = new C04ObserverA();
        //注册多个观察者
        subject.registyObserver(observer1);
        subject.registyObserver(observer2);
        subject.registyObserver(observer3);
        //改变目标对象的状态
        subject.setStatus(1000);
        System.out.println("**********************");
        //查看观察者的状态
        System.out.println(((C04ObserverA) observer1).getMyStatus());
        System.out.println(((C04ObserverA) observer2).getMyStatus());
        System.out.println(((C04ObserverA) observer3).getMyStatus());

        //改变目标对象的状态
        subject.setStatus(20);
        System.out.println("**********************");
        //查看观察者的状态
        System.out.println(((C04ObserverA) observer1).getMyStatus());
        System.out.println(((C04ObserverA) observer2).getMyStatus());
        System.out.println(((C04ObserverA) observer3).getMyStatus());



    }
}
