package com.xiaojihua.javabasic.chapter15generic.wildcards;

import java.util.Arrays;
import java.util.List;

/**
 * 知识点：
 * 1、当使用泛型静态方法的时候，可以不使用协变机制，因为T可以根据传递
 * 的具体类型而改变。但使用一个泛型类的时候由于类型参数在类实例化的时
 * 候已经确定，比如像InnerRead，在f2中是没有办法接受List<Apple>的
 * 因为实例化的时候已经确定类型参数为Fruit，因此只能通过使用协变来解决，
 * 比如InnerReadWild，详细使用见f3 Fruit的类可以接受List<Apple>了
 */
public class C06GenericReading {
    private static List<Fruit> fruitList = Arrays.asList(new Fruit());
    private static List<Apple> apples = Arrays.asList(new Apple());

    /**
     * 静态泛型方法
     * @param tList
     * @param <T>
     * @return
     */
    public static <T> T readExat(List<T> tList){
        return tList.get(0);
    }

    public static void f1(){
        Fruit fruit = readExat(fruitList);
        Apple apple = readExat(apples);
    }

    /**
     * 嵌套类
     * @param <T>
     */
    public static class InnerRead<T>{
        public  T readExat(List<T> tList){
            return tList.get(0);
        }
    }

    public static void f2(){
        InnerRead<Fruit> fruitInner = new InnerRead<>();
        Fruit fruit = fruitInner.readExat(fruitList);

        //如下使用是编译不通过的
        //Apple apple= fruitInner.readExat(apples);
    }

    /**
     * 使用协变来解决上面的问题
     * @param <T>
     */
    public static class InnerReadWild<T>{
        public T readExat(List<? extends T> tList){
            return tList.get(0);
        }
    }

    public static void f3(){
        InnerReadWild<Fruit> fruitInner1 = new InnerReadWild<>();
        Fruit fruit = fruitInner1.readExat(fruitList);
        //方法返回的是Fruit需要强转
        Apple apple = (Apple) fruitInner1.readExat(apples);
    }

    public static void main(String[] args){
        f1();f2();f3();
    }
}
