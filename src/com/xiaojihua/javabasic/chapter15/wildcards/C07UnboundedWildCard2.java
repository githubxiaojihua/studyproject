package com.xiaojihua.javabasic.chapter15.wildcards;

import java.util.HashMap;
import java.util.Map;

public class C07UnboundedWildCard2 {
    private static Map map1;
    private static Map<?,?> map2;
    private static Map<String,?> map3;

    public static void method1(Map map){
        map1 = map;
    }

    public static void method2(Map<?,?> map){
        map2 = map;
    }

    public static void method3(Map<String,?> map){
        map3 = map;
    }

    public static void main(String[] args){
        method1(new HashMap());
        method2(new HashMap());
        method3(new HashMap());

        method1(new HashMap<String,Integer>());
        method2(new HashMap<String,Integer>());
        method3(new HashMap<String,Integer>());
    }
}
