package com.xiaojihua.javabasic.chapter15generic.wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * 1、无界通配符的使用，使用无界通配符与不使用无界通配符的作用是一样的。
 * 无界通配符标识list可以持有任何类型，因此可以说List<?>与List是等价的。
 * 但是又有区别，List是泛型形式List<T>的擦除后的类型，我们叫做row type
 * ，但是使用List<?>表明我现在在使用泛型但是可以接收任何类型的参数类型
 * 是要与使用row type区别开的，此时的？更像是一种装饰符。
 * List与List<Object>实际上也是一样的
 * 2、List\List<?>\List<? extends Object>在使用上没有区别
 */
public class C07UnboundedWildCard1 {
    private static List list1;
    private static List<?> list2;
    private static List<? extends Object> list3;

    public static void method1(List list){
        list1 = list;
        list2 = list;
        list3 = list;
    }

    public static void method2(List<?> list){
        list1 = list;
        list2 = list;
        list3 = list;
    }

    public static void method3(List<? extends Object> list){
        list1 = list;
        list2 = list;
        list3 = list;
    }

    public static void main(String[] args){
        method1(new ArrayList());
        method2(new ArrayList());
        method3(new ArrayList());

        method1(new ArrayList<String>());
        method2(new ArrayList<String>());
        method3(new ArrayList<String>());

        List<?> list = new ArrayList();
        list = new ArrayList<String>();
        method1(list);
        method2(list);
        method3(list);

    }
}
