package com.xiaojihua.javabasic.chapter16array;

import java.util.Arrays;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、数组工具的使用。System.arraycopy
 * 可以针对基本类型和引用类型
 *
 * 注意，原数组和目标数组必须是完全一致的才行，
 * 此方法不包含自动装箱和拆箱。
 */
public class C11CoppingArray {
    public static void main(String[] args){
        int[] i = new int[7];
        int[] j = new int[10];
        Arrays.fill(i,47);
        Arrays.fill(j,99);
        print("i:" + Arrays.toString(i));
        print("j:" + Arrays.toString(j));

        //参数为：原数组、原数组开始，目标数组，目标数组开始，复制长度
        System.arraycopy(i,0,j,0,i.length);
        print("j:" + Arrays.toString(j));

        int[] k = new int[5];
        Arrays.fill(k,103);
        System.arraycopy(i,0,k,0,k.length);
        print("k:" + Arrays.toString(k));

        Arrays.fill(k,103);
        System.arraycopy(k,0,i,0,k.length);
        print("i:" + Arrays.toString(i));

        Integer[] u = new Integer[10];
        Integer[] v = new Integer[5];
        Arrays.fill(u,new Integer(47));
        Arrays.fill(v,new Integer(99));
        print("u:" + Arrays.toString(u));
        print("v:" + Arrays.toString(v));
        System.arraycopy(v,0,u,u.length/2,v.length);
        print("u:" + Arrays.toString(u));

    }
}
