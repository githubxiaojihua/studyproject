package com.xiaojihua.javabasic.enums;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HashMapAndTreeMap {

    public static void main(String[] args) {

        // HashMap
        Map<String,String> hashMap = new HashMap();
        hashMap.put("b","bbb");
        hashMap.put("a","aaa");
        hashMap.put("c","ccc");
        hashMap.put("d","add");
        Iterator<String> iterator = hashMap.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            System.out.println("hashMap,key is " + hashMap.get(key));
        }
        System.out.println("=============================================");
        // TreeMap
        Map<String,String> treeMap = new TreeMap<>();
        treeMap.put("b","bbb");
        treeMap.put("a","aaa");
        treeMap.put("c","ccc");
        Iterator<String> iterator1 = treeMap.keySet().iterator();
        while(iterator1.hasNext()){
            String key = iterator1.next();
            System.out.println("treeMap,key is " + treeMap.get(key));
        }
    }
}
