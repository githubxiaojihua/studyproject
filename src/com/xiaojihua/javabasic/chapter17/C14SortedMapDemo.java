package com.xiaojihua.javabasic.chapter17;

import com.xiaojihua.javabasic.util.C13CounttingMapData;
import static com.xiaojihua.javabasic.util.Print.*;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * 知识点：
 * 1、SortedMap接口的使用，实现着目前只有TreeMap
 * 按照key值的Comparable接口的实现逻辑来排序
 */
public class C14SortedMapDemo {
    public static void main(String[] args){
        TreeMap<Integer,String> treeMap = new TreeMap<>(new C13CounttingMapData(10));
        Integer low = treeMap.firstKey();
        Integer hight = treeMap.lastKey();
        print(low);
        print(hight);

        Iterator<Integer> iterator = treeMap.keySet().iterator();
        for(int i=0; i<=6; i++){
            if(i == 3){
                low = iterator.next();
            }
            if(i == 6){
                hight = iterator.next();
            }else{
                iterator.next();
            }
        }
        print(low);
        print(hight);

        print(treeMap.subMap(low,hight));
        print(treeMap.headMap(hight));
        print(treeMap.tailMap(low));
    }
}
