package com.xiaojihua.javabasic.chapter16;

import java.util.Arrays;

/**
 * 知识点：
 * 1、二维数组的使用。
 * 2、多维数组需要通过使用Arrays.deepToString方法来打印，
 * 如果使用Arrays.toString的话打印的是以为数组，二维数组
 * 是对象。
 */
public class C02MutidimensionArray {
    public static void main(String[] args){
        int[][] a = {
                {1,2,3},
                {4,5,6}
        };
        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.toString(a));

        int[][][] b = new int[2][2][4];
        System.out.println(Arrays.deepToString(b));
    }
}
