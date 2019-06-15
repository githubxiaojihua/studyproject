package com.xiaojihua.javabasic.chapter16array;

import java.util.ArrayList;
import java.util.List;

/**
 * 可以声明一个泛型数组，然后通过类型转换来赋值。
 * 但是不能直接new一个泛型数组
 */
public class C05ArrayOfGenerics {
    public static void main(String[] args){
        List<String>[] ls;
        ls = (List<String>[])new List[10];
        ls[0] = new ArrayList<String>();
        //如下写法会报编译错误
        //ls[1] = new ArrayList<Integer>();

        //转化为Object数组，后可以存入new ArrayList<String>()
        //数组是协变的一个List<String>[]也是一个Object[]数组
        //因此将要给new ArrayList<String>插入也是没有问题的
        //在变异期间和运行期间都是不报错的。
        Object[] object = ls;
        object[1] = new ArrayList<String>();
        System.out.println(object[1]);

    }
}
