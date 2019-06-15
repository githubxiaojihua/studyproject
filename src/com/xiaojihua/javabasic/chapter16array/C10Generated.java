package com.xiaojihua.javabasic.chapter16array;

import com.xiaojihua.javabasic.util.CollectionData;
import com.xiaojihua.javabasic.util.ConvertTo;
import com.xiaojihua.javabasic.util.Generator;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 知识点：
 * 1、借助CollectionData工具类生成数组。
 * 2、所有的Collection都有toArray方法，可以将其中的元素
 * 填充到指定数组中。
 * 3、这就是面向接口编程的好处了
 */
public class C10Generated {

    /**
     * 根据一个现成的数组进行生成
     * @param a
     * @param gen
     * @param <T>
     * @return
     */
    public static <T> T[] array(T[] a, Generator<T> gen){
        return CollectionData.list(gen,a.length).toArray(a);
    }

    /**
     * 生成一个新的数组
     * @param type
     * @param gen
     * @param size
     * @param <T>
     * @return
     */
    public static <T> T[] array(Class<T> type, Generator<T> gen, int size){
        T[] a = (T[])Array.newInstance(type,size);
        return CollectionData.list(gen,a.length).toArray(a);
    }

    /**
     * 使用数组生成器。
     * @param args
     */
    public static void main(String[] args){
        Integer[] a = {9,8,7,6};
        System.out.println(Arrays.toString(a));
        //通过生成器覆盖了原来的数组元素
        a = C10Generated.array(a,new C08CounttingGenerator.Integer());
        System.out.println(Arrays.toString(a));
        //从开始生成一个元素
        Integer[] b = C10Generated.array(Integer.class, new C08CounttingGenerator.Integer(),15);
        System.out.println(Arrays.toString(b));

        System.out.println("=======================================================");

        //使用转化工具生成基本类型的数组
        Integer[] c = {6,5,4,3};
        int[] cc = ConvertTo.primitive(C10Generated.array(a,new C08CounttingGenerator.Integer()));
        System.out.println(Arrays.toString(cc));

        int[] d = ConvertTo.primitive(C10Generated.array(Integer.class,new C08CounttingGenerator.Integer(),15));
        System.out.print(Arrays.toString(d));
    }
}
