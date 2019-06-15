package com.xiaojihua.javabasic.chapter15generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * 1、泛型方法和可变参数类型可以共存
 * 2、泛型方法的声明是在返回值前面加类型参数
 */
public class C10VarargsGeneric {
    /**
     * 声明一个泛型方法，并且使用可变类型参数。
     * @param args
     * @param <T>
     * @return
     */
    public static <T> List<T> makeList(T ... args){
        List<T> tList = new ArrayList<>();
        for(T t : args){
            tList.add(t);
        }
        return tList;
    }

    public static void main(String[] args){
        List<String> strList = makeList("zhangsan");
        System.out.println(strList);
        strList = makeList("a","b","c","d");
        System.out.println(strList);
        strList = makeList("ABCDEFGHIGKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(strList);
    }
}
