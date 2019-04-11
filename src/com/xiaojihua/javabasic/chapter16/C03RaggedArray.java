package com.xiaojihua.javabasic.chapter16;

import java.util.Arrays;
import java.util.Random;

/**
 * 知识点：
 * 1、多维数组的各个维度数量可以不一样
 */
public class C03RaggedArray {
    public static void main(String[] args){
        Random random = new Random(47);
        int[][][] a = new int[random.nextInt(7)][][];
        for(int i=0; i<a.length; i++){
            a[i] = new int[random.nextInt(5)][];
            for(int j=0; j<a[i].length; j++){
                a[i][j] = new int[random.nextInt(5)];
            }
        }

        System.out.println(Arrays.deepToString(a));


    }
}
