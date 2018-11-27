package com.xiaojihua.javabasic.chapter11;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、数组需要Array.toString()方法打印，但是集合（collection\map）则可以直接打印。
 * 2、list以特定顺序保存元素，set元素不能重复，queuq只允许在容器的一端插入对象，在另一端删除对象，map保存键值对。
 * 3、ArrayList和LinkedList都是按照被插入的顺序保存元素。
 * 4、HashSet存储的数据无序但是查找速度快，TreeSet按照key值得比较结果保存元素，LinkedHashSet按照插入的顺序存储数据
 * 5、HashMap存储的数据无序但是查找速度快，TreeMap按照key值得比较结果保存元素，LinkedHashMap按照插入的顺序存储数据同时保留了HashMap的查询速度
 */
public class PrintingContainers {
    public static Collection fill(Collection<String> collection){
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return collection;
    }

    public static Map fill(Map<String,String> map){
        map.put("rat","frazz");
        map.put("cat","tom");
        map.put("dog","bosca");
        map.put("dog","spot");
        return map;
    }

    public static void main(String[] args) {
        print(fill(new ArrayList<>()));
        print(fill(new LinkedList<>()));
        print(fill(new HashSet<>()));
        print(fill(new TreeSet<>()));
        print(fill(new LinkedHashSet<>()));
        print(fill(new HashMap<>()));
        print(fill(new TreeMap<>()));
        print(fill(new LinkedHashMap<>()));
    }
}
