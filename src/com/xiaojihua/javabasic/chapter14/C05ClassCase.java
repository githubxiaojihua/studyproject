package com.xiaojihua.javabasic.chapter14;

class Building{}
class House extends Building{}

/**
 * 知识点：
 * 1、一种新的类型转换方法。通过Class的cast方法来完成
 */
public class C05ClassCase {
    public static void main(String[] args){
        Building b = new House();
        Class<House> type = House.class;
        House h = type.cast(b);
        h = (House)b;
    }
}
