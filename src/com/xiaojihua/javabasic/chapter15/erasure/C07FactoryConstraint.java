package com.xiaojihua.javabasic.chapter15.erasure;

/**
 * 知识点：
 * 1、通过工厂方法来创建泛型实例。实例化泛型是不允许的，比如 T t = new T();，
 * 本例通过使用工厂方法来简介实例化T。比如Foo类就可以做到实例化u。
 * 2、工厂方法的使用
 *
 * 本例还可以通过使用type tag（Class<T>）来实现。
 * 可以参考
 * @see C08InstanceGenericType
 *
 *
 */
public class C07FactoryConstraint {
    public static void main(String[] args){
        //将U实例化为Integer对象
        new Foo<Integer>(new IntegerFactory());
        //将U实例化为AnObject对象
        new Foo<AnObject>(new AnObject.ObjectFactory());
    }
}

/**
 * 工厂接口
 * @param <T>
 */
interface FactoryI<T>{
    T create();
}

/**
 * Integer的工厂实现
 */
class IntegerFactory implements FactoryI<Integer>{
    @Override
    public Integer create(){
        return new Integer(0);
    }
}

/**
 * AnObject的工厂实现。通过内部类来实现
 */
class AnObject{
    public static class ObjectFactory implements FactoryI<AnObject>{

        @Override
        public AnObject create(){
            return new AnObject();
        }
    }
}

/**
 * 一个泛型类，本例主要目的是在调用构造方法的时候
 * 能实例化U。
 * 也是工厂方法的使用目的地
 * @param <U>
 */
class Foo<U>{
    private U u;
    public <V extends FactoryI<U>> Foo(V factoryI){
        u = factoryI.create();
    }
}