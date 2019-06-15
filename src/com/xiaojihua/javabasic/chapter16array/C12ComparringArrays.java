package com.xiaojihua.javabasic.chapter16array;

import java.util.Arrays;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、比较整个数组的相等性。
 * 只有两个数组元素数量相等，并且每个对应元素相等的时候才
 * 认为两个数组相等。
 */
public class C12ComparringArrays {
    public static void main(String[] args){
        int[] a1 = new int[10];
        int[] a2 = new int[10];
        Arrays.fill(a1, 47);
        Arrays.fill(a2,47);
        print(Arrays.equals(a1,a2));
        a2[3] = 11;
        print(Arrays.equals(a1,a2));

        /*
            s1有4个元素都指向同一个字符串（字符串的不变性，参考String章节
            的相关笔记），但是s2是四个分别独立的对象。
            但是结果依然是true，原因是相等性是通过Object.equals(),方法来
            确定的，而String的equals是比较内容的，如果内容相等则认为是相等的。
         */
        String[] s1 = new String[4];
        Arrays.fill(s1,"hi");
        String[] s2 = { new String("hi"),new String("hi"),new String("hi"),new String("hi")};
        print(Arrays.equals(s1,s2));
    }
}
