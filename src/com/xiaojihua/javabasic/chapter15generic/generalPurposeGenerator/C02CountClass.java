package com.xiaojihua.javabasic.chapter15generic.generalPurposeGenerator;

/**
 * 生成器所使用的类
 * public的并且有默认构造函数
 */
public class C02CountClass {
    private static int count = 0;
    private final int id = count++;

    public int getId(){ return id; }
    public String toString(){
        return "CountClass:" + id;
    }
}
