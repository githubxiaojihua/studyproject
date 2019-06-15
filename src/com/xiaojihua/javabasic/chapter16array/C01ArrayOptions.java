package com.xiaojihua.javabasic.chapter16array;
import java.util.Arrays;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 数组的基本使用
 * 初始化数组的几种方法
 * 对象数组持有的是引用，基本类型的数组持有的是值
 * 对象数组持有的是引用，基本类型的数组持有的是值
 */
public class C01ArrayOptions {
    public static void main(String[] args){
        //持有对象
        BerylliumSphere[] a;
        BerylliumSphere[] b = new BerylliumSphere[4];//1
        print("b" + Arrays.toString(b));

        BerylliumSphere[] c = new BerylliumSphere[4];
        for(int i=0; i<c.length; i++){
            if(c[i] == null){
                c[i] = new BerylliumSphere();//2
            }
        }

        //3
        BerylliumSphere[] d = {new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()};
        //4
        a = new BerylliumSphere[]{new BerylliumSphere(),new BerylliumSphere()};

        print("a.length = " + a.length);
        print("b.length = " + b.length);
        print("c.length = " + c.length);
        print("d.length = " + d.length);

        a=d;
        print("a.length = " + a.length);

        //基本类型
        // Arrays of primitives:
        int[] e; // Null reference
        int[] f = new int[5];
        // The primitives inside the array are
        // automatically initialized to zero:
        print("f: " + Arrays.toString(f));
        int[] g = new int[4];
        for(int i = 0; i < g.length; i++)
            g[i] = i*i;
        int[] h = { 11, 47, 93 };
        // Compile error: variable e not initialized:
        //!print("e.length = " + e.length);
        print("f.length = " + f.length);
        print("g.length = " + g.length);
        print("h.length = " + h.length);
        e = h;
        print("e.length = " + e.length);
        e = new int[]{ 1, 2 };
        print("e.length = " + e.length);

    }
}

class BerylliumSphere{
    private static int count;
    private final int id = count++;
    public String toString(){
        return "BerylliumSphere:" + id;
    }
}
