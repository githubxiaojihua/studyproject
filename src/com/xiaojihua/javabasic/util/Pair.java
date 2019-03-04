package com.xiaojihua.javabasic.util;

/**
 * 手工创建一个键值对类
 * @param <K>
 * @param <V>
 */
public class Pair<K,V> {
    private K key;
    private V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }
}
