package com.xiaojihua.javabasic.chapter12;

import java.io.FileInputStream;

/**
 * 知识点：
 * 1、main方法也可以声明异常，其抛出的异常将直接打印到控制台上。不用写try catch语句
 */
public class C12MainException {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("MainException.java");
        in.close();
    }
}
