package com.xiaojihua.javabasic.util;

import java.io.PrintStream;

/**
 * Print 工具类
 */
public class Print {

    //换行打印
    public static void print(Object o){
        System.out.println(o);
    }
    //打印新行
    public static void print(){
        System.out.println();
    }
    //不换行打印
    public static void printnb(Object o){
        System.out.print(o);
    }
    //printf
    public static PrintStream printf(String fomat, Object... args){
        return System.out.printf(fomat, args);
    }
}
