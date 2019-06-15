package com.xiaojihua.javabasic.chapter16array;

import com.xiaojihua.javabasic.util.ConvertTo;
import com.xiaojihua.javabasic.util.Generator;

import java.util.Arrays;

/**
 * 知识点：
 * 1、Arrays.binarySearch的使用，注意应用的数组应该是排序后的数组。
 *
 */
public class C15ArraySearch {
    public static void main(String[] args){
        Generator<Integer> gen = new C09RandomGenerator.Integer(1000);
        int[] ints = ConvertTo.primitive(C10Generated.array(new Integer[10],gen));
        System.out.println(Arrays.toString(ints));
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
        while(true){
            int r = gen.next();
            //找到则返回>=0，返回负值则代表1、元素不在数组中，2、在数组中第一个大于他的数的位置（从1开始）
            int location = Arrays.binarySearch(ints,r);
            if(location > 0){
                System.out.println(r + ",location:" + location);
                break;
            }
        }
    }
}
