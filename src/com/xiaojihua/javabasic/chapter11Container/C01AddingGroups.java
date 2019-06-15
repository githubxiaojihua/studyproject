package com.xiaojihua.javabasic.chapter11Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 知识点：向集合中一次性添加一组元素
 */
public class C01AddingGroups {
    public static void main(String[] args) {
        //Arraylist构造函数接收一个Collection对象
        //Arrays.asList将一个可变列表转换为Collection对象
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Integer[] ints = {6,7,8,9,10};
        //addAll接收一个Collection对象
        //Arrays.asList接受一个数组并返回一个LIST
        collection.addAll(Arrays.asList(ints));

        //Collection.addAll函数接收可变类型参数以及数组合并已有Collection 对象 collection
        Collections.addAll(collection,11,12,13,14,15,16);
        Collections.addAll(collection,ints);

    }
}
