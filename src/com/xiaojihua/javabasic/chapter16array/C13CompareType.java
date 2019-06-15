package com.xiaojihua.javabasic.chapter16array;

import com.xiaojihua.javabasic.util.Generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * 知识点：
 * 1、使用Arrays.sort()方法对数组进行排序，这要求数组对象实现Comparable接口。
 * 2、Arrays.toString最终调用的是数组元素的toString方法。
 * 3、Collections.reverseOrder的使用
 */
public class C13CompareType implements Comparable<C13CompareType> {
    protected int i;
    protected int j;
    public C13CompareType(int n1, int n2){
        i = n1;
        j = n2;
    }

    /**
     * 实现Comparable接口
     * @param type
     * @return
     */
    @Override
    public int compareTo(C13CompareType type){
        return (i<type.i)?-1:(i==type.i?0:1);
    }

    private static Random random = new Random(47);

    /**
     * 匿名内部类返回一个本类的生成器
     * @return
     */
    public static Generator<C13CompareType> generator(){
        return new Generator<C13CompareType>() {
            int bound = 100;
            @Override
            public C13CompareType next() {
                return new C13CompareType(random.nextInt(bound),random.nextInt(bound));
            }
        };
    }

    private static int count = 1;
    @Override
    public String toString(){
        String result = "[i=" + i + ",j=" + j +"],";
        if((count++)%3==0){
            result += "\n";
        }
        return result;
    }

    public static void main(String[] args){
        //调用数组生成方法使用一个已有数组和生成器，生成数组
        C13CompareType[] arr = C10Generated.array(new C13CompareType[10],generator());
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        //策略模式的一个应用，通过传入反向排序策略，让数组倒序排列
        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println(Arrays.toString(arr));

        //使用自定义的比较策略
        Arrays.sort(arr, new CompareTypeCompartor());
        System.out.println(Arrays.toString(arr));
    }
}


/**
 * 自定义的比较策略，比较j的值。
 */
class CompareTypeCompartor implements Comparator<C13CompareType>{

    @Override
    public int compare(C13CompareType c1,C13CompareType c2){
        return (c1.j < c2.j) ? -1 : (c1.j == c2.j) ? 0 : 1;
    }
}