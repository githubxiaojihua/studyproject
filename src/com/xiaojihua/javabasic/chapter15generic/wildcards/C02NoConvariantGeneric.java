package com.xiaojihua.javabasic.chapter15generic.wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * 1、带泛型的容器是非协变的，就像下面注销的哪句，
 * 一个Apple的list不能赋值给一个Fruit的list
 * 2、List list = new ArrayList<Apple>();，这么写
 * 倒是可以的
 * 3、Apple的list并不是一个Fruit的list。Apple的list可以持有
 * Apple以及他的子类。Fruit的list可以持有Fruit及其子类，也包括
 * Apple，但是不能把它变成一个Apple的list或者当作Apple的list
 * 对待，即使他里面完全存储着Apple但还是一个Fruit类型的list。
 * List<Fruit>和List<Apple>不具有等价性，虽然Apple是Fruit的
 * 子类。
 */
public class C02NoConvariantGeneric {

    public static void main(String[] args){
        //List<Fruit> list = new ArrayList<Apple>();
        List list = new ArrayList<Apple>();
        abc(new ArrayList<Apple>());
    }

    public static void abc(List<? extends Fruit> c){}
}
