package com.xiaojihua.javabasic.chapter10;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：java闭包、接口与接口的实现分离（工厂模式也是一种分离方式）、回调
 *
 * 闭包：书中的定义是，是一个可调用的对象，它记录了一些信息，这些信息来自于创建它的作用域。对应本例
 * 闭包是指内部类Closure。它是与诶个可调用的对象，同时可以通过它访问Callee2的所有信息包括private成员。
 *
 * 接口与接口的实现分离：最对外公布接口而隐藏接口的实现细节。
 * 具体到下面的例子：
 * 接口是Incretable，接口的实现是Callee2的private 内部类closure。当要使用接口的实现类的时候，
 * 只能通过Callee2的getCallbackRefrence方法返回实现类的实例，而且返回的是接口的引用指向了实现类的实例，
 * 这样类的使用者就无法知道或者无需知道具体的实现逻辑是什么，只需要知道接口的方法即可使用。这就实现了接口
 * 与接口的实现相分离的目的。同理工厂模式也是一种分离方式。
 * 简单来说就是将接口的实现类设置为对外不可见（private或者是内部类)的形式，然后通过工厂方法或者其他返回引用的方法，如本例的getCallbackRefrence。
 * 返回一个接口引用指向实现类的实例。
 *
 * 回调：一个对象能够携带一些信息，这些信息允许它在某个时刻调用初始的对象。对应到本例就是：
 * Caller对象会携带一个Callee类的引用或者其基类的引用，然后在任意时刻和位置，可以使用此引用回调Callee类的方法。
 */
interface Incretable{
    void increment();
}

/**
 * 接口的简单实现
 */
class Callee1 implements Incretable{
    private int i = 0;
    public void increment(){
       i++;
       print(i);
    }
}

/**
 * 一个自定义的基类，同样有increment方法
 */
class MyIncretable{
    public void increment(){ print("Other operate"); }
    static void f(MyIncretable mi){ mi.increment(); }
}

/**
 * 继承自MyIncretable，但是
 */
class Callee2 extends MyIncretable{
    private int i = 0;
    public void increment(){
        super.increment();
        i++;
        print(i);
    }

    /**
     * 内部类实现闭包
     */
    private class Closure implements Incretable{
        //返回一个Callee2的钩子
        @Override
        public void increment() {
            Callee2.this.increment();
        }
    }

    //通过公用方是法返回Incretable引用指向实现类，实现了接口与接口实现的分离。也返回闭包对象
    public Incretable getCallbackRefrence(){
        return new Closure();
    }
}

class Caller{
    Incretable incretable;
    Caller(Incretable incretable){
        this.incretable = incretable;
    }
    public void go(){
        incretable.increment();
    }
}
public class Callbacks {
    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncretable.f(c2);
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackRefrence());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();

    }


}
