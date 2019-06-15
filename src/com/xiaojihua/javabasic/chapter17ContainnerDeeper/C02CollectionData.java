package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import com.xiaojihua.javabasic.chapter16array.C09RandomGenerator;
import com.xiaojihua.javabasic.util.Generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 知识点：
 * 1、使用泛型+生成器来填充容器
 * @param <T>
 */
public class C02CollectionData<T> extends ArrayList<T> {
    /**
     * 继承自ArrayList，通过构造函数接收泛型生成器来
     * 填充容器
     * @param gen
     * @param size
     */
    public C02CollectionData(Generator<T> gen, int size){
        for(int i=0; i<size; i++){
            add(gen.next());
        }
    }

    /**
     * 使用泛型的简单方法，这样避免了在使用泛型构造方法的时候
     * 还要重复键入泛型元素，比如43行和48行的区别，这样就比较优雅
     * @param gen
     * @param size
     * @param <T>
     * @return
     */
    public static <T> C02CollectionData<T> collectionData(Generator<T> gen, int size){
        return new C02CollectionData<>(gen,size);
    }

    public static void main(String[] args){
        //通过C02CollectionData构造方法来初始化列表
        //LinkedHashSet是有序列表按照插入的顺序存储列表元素
        Set<String> newSet = new LinkedHashSet<>(
                new C02CollectionData<>(new StringGenerator(),15)
        );

        //通过优雅的方式来调用C02CollectionData
        newSet.addAll(C02CollectionData.collectionData(new StringGenerator(),15));
        System.out.println(newSet);

        System.out.println(new ArrayList<>(C02CollectionData.collectionData(new C09RandomGenerator.String(9),10)));

        System.out.println(new HashSet<>(C02CollectionData.collectionData(new C09RandomGenerator.Integer(),10)));
    }
}

class StringGenerator implements Generator<String>{
    private static String[] values = "strange women lying in ponds distributing swords is no basis for a system of government".split(" ");
    private int index = 0;
    @Override
    public String next() {
        return values[index++];
    }
}


