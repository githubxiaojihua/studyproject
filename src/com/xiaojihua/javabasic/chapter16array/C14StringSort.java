package com.xiaojihua.javabasic.chapter16array;

import java.util.Arrays;
import java.util.Collections;

/**
 * 知识点：
 * 1、字符串数组的排序，及其策略模式的使用
 */
public class C14StringSort {
    public static void main(String[] args){
        String[] strings = C10Generated.array(new String[10],new C09RandomGenerator.String(5));
        System.out.println(Arrays.toString(strings));

        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));

        Arrays.sort(strings, Collections.reverseOrder());
        System.out.println(Arrays.toString(strings));

        Arrays.sort(strings, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(strings));


    }
}
