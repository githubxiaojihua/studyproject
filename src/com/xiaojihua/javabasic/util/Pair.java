package com.xiaojihua.javabasic.util;

/**
 * 手工创建一个键值对类
 *
 * 知识点：
 * 1、通过将key和value设置为public final的，并且只能通过构造函数来进行
 * 初始化。从而创建了一个只读DTO(Data Transfer Object) 或者Messager。
 * 因为key和value一旦通过构造函数初始化以后就不能在进行更改，并且由于是
 * public的因此可以直接进行访问，省去了getter和setter
 * @param <K>
 * @param <V>
 */
public class Pair<K,V> {
    public final K key;
    public final V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

}
