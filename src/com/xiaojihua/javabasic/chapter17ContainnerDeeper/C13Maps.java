package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import com.xiaojihua.javabasic.chapter17ContainnerDeeper.hashCode.C07SlowMap2;
import com.xiaojihua.javabasic.util.C13CounttingMapData;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、map的使用
 */
public class C13Maps {
    public static void printKeys(Map<Integer,String> map){
        printnb("size():" + map.size() + ", ");
        printnb("keys:");
        print(map.keySet());//返回一个包含所有key的set
        //map.forEach((k,v) -> print("key:" + k + ",value:" + v));
    }

    public static void test(Map<Integer,String> map){
        print(map.getClass().getSimpleName());
        map.putAll(new C13CounttingMapData(25));
        //map的key与set的表现是一样的，不允许key值重复。实现的关键是Map.Entry中的equals和hashcode方法的实现
        map.putAll(new C13CounttingMapData(25));
        printKeys(map);

        printnb("Values:");
        print(map.values());//返回一个包含map中所有value的collection
        print(map);

        print("map.containsKey(11):" + map.containsKey(11));
        print("map.get(11):" + map.get(11));

        print("map.containsValue(\"F0\")" + map.containsValue("F0"));

        Integer key = map.keySet().iterator().next();
        print("First key in map:" + key);
        map.remove(key);
        printKeys(map);

        map.clear();
        printKeys(map);
        print("map.isEmpty():" + map.isEmpty());
        map.putAll(new C13CounttingMapData(25));

        //对返回的keyset以及values做操作会直接修改到底层的map
        map.keySet().removeAll(map.keySet());
        print("map.isEmpty():" + map.isEmpty());
    }

    public static void main(String[] args){
        /*test(new HashMap<Integer,String>());
        test(new TreeMap<Integer,String>());
        test(new LinkedHashMap<Integer,String>());
        test(new IdentityHashMap<Integer,String>());
        test(new ConcurrentHashMap<Integer,String>());
        test(new WeakHashMap<Integer,String>());*/

        //C06SlowMap，无法做到clear和remove，来修改底层的map数据，只是修改了copy
        //test(new C06SlowMap<>());
        //改进后的SlowMap
        test(new C07SlowMap2<>());

    }
}
