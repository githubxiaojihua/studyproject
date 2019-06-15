package com.xiaojihua.javabasic.chapter16array;

import java.util.Arrays;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、Arrays.fill的应用
 */
public class C07FillArray {
    public static void main(String[] args){
        int size = 6;
        boolean[] booleans = new boolean[size];
        byte[] bytes = new byte[size];
        char[] chars = new char[size];
        short[] shorts = new short[size];
        int[] ints = new int[size];
        long[] longs = new long[size];
        float[] floats = new float[size];
        double[] doubles = new double[size];
        String[] strings = new String[size];

        Arrays.fill(booleans,true);
        print(Arrays.toString(booleans));
        Arrays.fill(bytes,(byte)11);
        print(Arrays.toString(bytes));
        Arrays.fill(chars,'x');
        print(Arrays.toString(chars));
        Arrays.fill(shorts, (short) 17);
        print(Arrays.toString(shorts));
        Arrays.fill(ints,1);
        print(Arrays.toString(ints));
        Arrays.fill(longs, 16);
        print(Arrays.toString(longs));
        Arrays.fill(floats,29);
        print(Arrays.toString(floats));
        Arrays.fill(doubles,30);
        print(Arrays.toString(doubles));
        Arrays.fill(strings,"Hello");
        print(Arrays.toString(strings));

        //指定填充范围
        Arrays.fill(strings,3,5,"World");
        print(Arrays.toString(strings));

    }
}
