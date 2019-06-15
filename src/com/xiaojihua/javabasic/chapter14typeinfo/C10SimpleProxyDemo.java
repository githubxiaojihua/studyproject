package com.xiaojihua.javabasic.chapter14typeinfo;

/**
 * 知识点：
 * 1、代理模式的简单应用
 *
 * 代理充当一个中间人的角色，代理是一个为了基于被代理对象提供更多操作或者不同操作的用于
 * 代替被代理对象的新的对象
 */
public class C10SimpleProxyDemo {
    public static void customer(Interface obj){
        obj.doSomething();
        obj.somethingElse("bbb");
    }

    public static void main(String[] args){
        customer(new RealObject());
        customer(new SimpleProxy(new RealObject()));
    }
}

/**
 * 代理模式基于的接口
 */
interface Interface{
    void doSomething();
    void somethingElse(String arg);
}

/**
 * 被代理的类
 */
class RealObject implements Interface{
    public void doSomething(){
        System.out.println("RealObject doSomething()");
    }
    public void somethingElse(String arg){
        System.out.println("RealObject somethingElse " + arg);
    }
}

/**
 * 代理，实现了统一的接口
 */
class SimpleProxy implements Interface{
    private Interface realObject;//被代理的对象
    public SimpleProxy(Interface realObject){
        this.realObject = realObject;
    }
    //代理方法
    public void doSomething(){
        System.out.println("SimpleProxy doSomething");
        realObject.doSomething();
    }
    //代理方法
    public void somethingElse(String arg){
        System.out.println("SimplePorxy somethingElse " + arg);
        realObject.somethingElse(arg);
    }
}
