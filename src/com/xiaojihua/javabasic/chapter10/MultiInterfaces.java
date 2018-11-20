package com.xiaojihua.javabasic.chapter10;

/**
 * 知识点：内部类使得java的多重继承机制得到完善。java的多重继承机制是通过接口来实现的，但是接口
 * 只是解决了部分问题，而内部类是的多重继承更佳完善。
 *
 * 下面的例子一个类中实现两个接口，有两种方式。
 * 第一种是同时实现A,B。
 * 第二种是实现其中一个，另一个由内部类实现，并提供返回实现类的实例。
 *
 * 如果需要同时实现继承自两个类或者抽象类那么只能通过内部类的方式实现了。
 * 参考MutiImplementation类
 *
 */
interface A{}
interface B{}

/**
 * 第一种实现方式
 */
class X implements A,B{}

/**
 * 第二种实现方式
 */
class Y implements A{
    B makeB(){
        return new B(){};
    }
}

/**
 * 使用这两种实现方式
 */
public class MultiInterfaces {
    static void takesA(A a){}
    static void takesB(B b){}

    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();

        takesA(x);
        takesB(x);

        takesA(y);
        takesB(y.makeB());
    }
}
