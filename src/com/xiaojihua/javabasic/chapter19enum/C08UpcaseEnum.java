package com.xiaojihua.javabasic.chapter19enum;

/**
 * 知识点：
 * enum实例可以向上转型为Enum，但是转型后就没有Values()方法了，
 * 因为Enum中是没有这个方法的，values()是编译器在生成enum类的
 * 时候添加进去的。
 * 但是可以通过Class类的getEnumConstants来达到values()相同的
 * 目的。
 */
public class C08UpcaseEnum {
    public static void main(String[] args){
        //Search[] searches = Search.values();
        Enum<Search> e = Search.HIGHER;
        for(Enum ee : e.getClass().getEnumConstants()){
            System.out.println(ee);
        }
    }
}

enum Search{
    HIGHER, YON
}
