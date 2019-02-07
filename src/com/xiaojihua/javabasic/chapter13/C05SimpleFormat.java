package com.xiaojihua.javabasic.chapter13;

/**
 * 知识点：
 * 1、java se5 以后PrintStream,PrintWriter提供了format方法。
 * 2、printf和format方法等价
 */
public class C05SimpleFormat {
    public static void main(String[] args) {
        int x = 5;
        double y = 5.332233;
        System.out.println("Row 1: [" + x + "," + y + "]");
        System.out.printf("Row 1:[ %d %f ]\n", x, y);
        System.out.format("Row 1.[ %d %f]\n", x, y);
    }
}
