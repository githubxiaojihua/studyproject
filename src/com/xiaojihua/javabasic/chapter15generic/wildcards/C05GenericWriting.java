package com.xiaojihua.javabasic.chapter15generic.wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * 1、不带边界的泛型的时候。
 * 2、super表示的逆变。协变使得子类型的变量可以传递给父类行的变量，比如
 * List<Apple>类型的变量可以传递给List<? extends Fruit>的形参，协变
 * 通过<? extends Fruit>来实现的，通配符代表了Fruit的子类。逆变
 * 是通过？ super Fruit来实现的，标识通配符代表的最低界为Fruit,？被绑定
 * 到了Fruit这个基础的类型，编译器认为这是类型安全的，因此可以往list中赋值
 * ，或者说往以类型参数为形参的方法中传参，参数可以是Fruit或者其子类，
 * 但是不能超过Fruit。通过逆变
 * 可以解决协变无法修改列表内部对象的问题，比如writeExct方法如果第一个
 * 参数类型是协变的那么就不能进行list.add(item);原因在
 * @see C03GenericsAndConveriant
 * 中已经解释明白。
 *
 * 通过逆变可以实现在使用通配符的情况下写入LIST。
 *
 * *************************************************************************************
 * 协变对于数组来讲就是，父类数组的引用实际指向了其某一个子类的数组，但是
 * 由于继承的关系却可以向这个数组中添加其他子类或者父类。这只是在编译期内是合法的
 * 当在运行期的时候仍然会报错。可以参考C01ConverianArray.java
 * 对于容器来讲协变指的是一个类型参数为父类的容器和一个类型参数为子类的容器
 * 并不能相互复制或者传递比如List<Fruit> list = new ArrayList<Apple>()，
 * 这是不允许的，另外如果一个方法接收List<Fruit>则不能传递ArrayList<Apple>
 * 的因为编译器认为是两个类型并不存在继承关系。要达到接收类型参数为子类的要求
 * 只能通过List<? extends Fruit>这种方式，将类型参数进行向下扩展。
 *
 * 逆变是对于容器来讲的，是协变的反向List<? super Fruit>这种方式，将参数类型
 * 向上扩展，以Fruit为最低界限，接受Fruit或者类型参数为其父类的list。正是有了
 * 最低界所以可以确定
 * **************************************************************************************
 *
 * 泛型的主要目的就是将数组的协变错误（编译期不进行提示，但是运行时报错），提前到编译期
 * 泛型的非协变特性以及协变和逆变的语法不仅仅适应于容器，所有应用泛型的类都一样。
 * 上面所说的容器的协变和非协变其实都是泛型的协变和非协变
 */
public class C05GenericWriting {
    private static List<Fruit> fruits = new ArrayList<Fruit>();
    private static List<Apple> apples = new ArrayList<Apple>();


    public static <T> void writeExct(List<T> list, T item){
        System.out.println(item.getClass().getSimpleName());
        list.add(item);
    }

    public static void f1(){
        writeExct(apples, new Apple());
        writeExct(fruits, new Apple());
    }

    /**
     * 通过super来逆变，逆变后可以进行add操作
     * @param list
     * @param item
     * @param <T>
     */
    public static <T> void writeWithWild(List<? super T> list, T item){
        list.add(item);
    }

    public static void f2(){
        //这里传递的形参列表的对应关系：apples的类型参数，是Apple的父类或同级，但是apples可以存储Apple及其子类
        writeWithWild(apples, new Apple());
        writeWithWild(fruits, new Apple());
        //满足上面所说的对应关系：Object 是Apple的父类或是同级
        writeWithWild(new ArrayList<Object>(), new Apple());
        //不满足上面所说的对应关系：Jonthon是Applede 子类，不满足逆变，适合协变。
        //writeWithWild(new ArrayList<Jonthon>(), new Apple());
    }

    public static void main(String[] args){
        f1();
        f2();
    }

}
