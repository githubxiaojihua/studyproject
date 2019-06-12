package com.xiaojihua.javabasic.chapter18IO;

import java.io.PrintWriter;

/**
 * 知识点：
 * 标准IO，Syste.out转换成PrintWriter
 */
public class C12ChangeSystemOut {
    public static void main(String[] args){
        //第二个参数为是否自动flush
        PrintWriter writer = new PrintWriter(System.out,true);
        writer.println("Hello world!");
    }
}
