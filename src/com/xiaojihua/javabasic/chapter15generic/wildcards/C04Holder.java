package com.xiaojihua.javabasic.chapter15generic.wildcards;

/**
 * 知识点：
 * 1、将appHolder赋值给C04Holder<Fruit>类型是不允许的，因为泛型类型是非协变的。
 * 但是通过使用通配符可以完成协变操作，比如C04Holder<? extends Fruit> fruitHolder = appleHolder;
 * 但是使用了通配符以后fruitHolder就不能进行set操作了，因为set方法接收的是类型参数
 * 而且fruitHolder所声明的类型参数为“? extends Fruit”，因此set方法的参数也是“? extends Fruit”
 * 当出现这种情况的时候编译器将直接拒绝方法的调用。
 * 2、fruitHolder的equals方法却能很好的工作，是因为他的参数是Object。
 * @param <T>
 */
public class C04Holder<T> {
    T t;
    public C04Holder(T t){
        this.t = t;
    }
    public C04Holder(){}

    public void setT(T t){
        this.t = t;
    }

    public T getT(){
        return t;
    }

    public boolean equals(Object obj){
        return t.equals(obj);
    }

    public static void main(String[] args){
        C04Holder<Apple> appleHolder = new C04Holder<>(new Apple());
        Apple apple = appleHolder.getT();
        appleHolder.setT(apple);//类型参数没有通配符可以正常调用set

        //泛型是非协变的，不能这样复制。数组是协变的。
        //C04Holder<Fruit> testCast = appleHolder;

        //通过使用通配符可以做到协变
        C04Holder<? extends Fruit> fruitHolder = appleHolder;
        Fruit fruit = fruitHolder.getT();//返回Fruit类型
        Apple apple1 = (Apple) fruitHolder.getT();//返回Fruit类型，需要强制类型转换

        /*C04Holder<? super Fruit> fruitAdd = new C04Holder<>(new Fruit());
        fruitAdd.setT(new Apple());

        C04Holder<? extends Fruit> fruit1 = new C04Holder<>(new Fruit());
        fruit1.setT(new Fruit());*/

        try{
            Orange orange = (Orange) fruitHolder.getT();//编译期不报错，但是运行时报错
        }catch(Exception e){
            System.out.println(e);
        }

        //一下两句均不能通过编译因为，sett方法参数是使用了通配符的泛型参数
        //fruitHolder.setT(new Apple());
        //fruitHolder.setT(new Fruit());

        //equals的参数是Object因此不影响
        System.out.println(fruitHolder.equals(apple));
    }
}
