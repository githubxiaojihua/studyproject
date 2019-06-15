package com.xiaojihua.javabasic.chapter17ContainnerDeeper.hashCode;

import com.xiaojihua.javabasic.chapter17ContainnerDeeper.C05Countries;

import java.util.*;

/**
 * 知识点：
 * 1、简单模拟hashMap，主要用来说明hashCode方法的作用，
 * 通过hashCode来快速定位和检索元素。
 *
 * @param <K>
 * @param <V>
 */
public class C08SimpleHashMap<K,V> extends AbstractMap<K,V> {
    private static final int SIZE = 977;
    private LinkedList<C05MapEntry<K,V>>[] bustkes = new LinkedList[SIZE];

    @Override
    public V get(Object o){
        int index = Math.abs(o.hashCode()) % SIZE;
        if(bustkes[index] == null){
            return null;
        }
        for(C05MapEntry<K,V> entry : bustkes[index]){
            if(entry.getKey().equals(o)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value){
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(bustkes[index] == null){
            bustkes[index] = new LinkedList<>();
        }
        ListIterator<C05MapEntry<K,V>> iterator = bustkes[index].listIterator();
        boolean hasFinded = false;
        C05MapEntry<K,V> entry = new C05MapEntry<>(key, value);
        while(iterator.hasNext()){
            C05MapEntry<K,V> iPair = iterator.next();
            if(iPair.getKey().equals(entry.getKey())){
                oldValue = iPair.getValue();
                iterator.set(entry);
                hasFinded = true;
                break;//找到的话别忘记break
            }
        }

        if(!hasFinded){
            bustkes[index].add(entry);
        }
        return oldValue;
    }

    @Override
    public Set<Map.Entry<K,V>> entrySet(){
        //还是存储的是key和value的copy
        Set<Map.Entry<K,V>> entrySet = new HashSet<>();
        for(LinkedList<C05MapEntry<K,V>> list : bustkes){
            if(list != null){
                for(Map.Entry<K,V> entry : list){
                    entrySet.add(entry);
                }
            }
        }
        return entrySet;
    }

    public static void main(String[] args){
        C08SimpleHashMap<String,String> hashMap = new C08SimpleHashMap<>();
        hashMap.putAll(C05Countries.capital(15));
        System.out.println(hashMap);
        System.out.println(hashMap.get("ANGOLA"));
        System.out.println(hashMap.entrySet());

    }

}
