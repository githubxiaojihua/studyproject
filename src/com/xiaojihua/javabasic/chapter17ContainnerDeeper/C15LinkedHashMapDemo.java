package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import com.xiaojihua.javabasic.util.C13CounttingMapData;

import java.util.LinkedHashMap;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、linkedHashMap的使用：
 * LinkedHashMap同时具有HashMap的速度和按照插入顺序排序的顺序。
 *
 */
public class C15LinkedHashMapDemo {
    public static void main(String[] args){
        LinkedHashMap<Integer,String> linkedHashMap = new LinkedHashMap<>(new C13CounttingMapData(9));
        print(linkedHashMap);

        //通过指定accessOrder可以配置LinkedList作为最少最近使用LRU算法排列元素（越少访问的，越靠前）
        //通过输出原始情况下都是按照插入顺序排列，但是如果accessOrder为ture的话会根据访问的次数进行排列
        //次数少的靠前
        linkedHashMap = new LinkedHashMap<>(16,0.75f,true);
        linkedHashMap.putAll(new C13CounttingMapData(9));
        print(linkedHashMap);

        for(int i=0; i<6; i++){
            linkedHashMap.get(i);
        }
        print(linkedHashMap);
        linkedHashMap.get(0);
        print(linkedHashMap);
    }
}
