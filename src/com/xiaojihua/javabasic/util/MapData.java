package com.xiaojihua.javabasic.util;

import java.util.LinkedHashMap;

/**
 * Map容器的填充工具类
 * @param <K>
 * @param <V>
 */
public class MapData<K,V> extends LinkedHashMap<K,V> {
    /**
     * 根据Pair生成器填充本类
     * @param generate
     * @param quantity
     */
    public MapData(Generator<Pair<K,V>> generate, int quantity){
        for(int i=0; i < quantity; i++){
            Pair<K,V> pair = generate.next();
            put(pair.key,pair.value);
        }
    }

    /**
     * 根据分离的key Generator，和value Generator填充本类
     * @param kGen
     * @param vGen
     * @param quantity
     */
    public MapData(Generator<K> kGen, Generator<V> vGen, int quantity){
        for(int i=0; i<quantity; i++){
            put(kGen.next(),vGen.next());
        }
    }

    /**
     * 根据单一的kGen和固定的value填充本类
     * @param kGen
     * @param value
     * @param quantity
     */
    public MapData(Generator<K> kGen, V value, int quantity){
        for(int i=0; i<quantity; i++){
            put(kGen.next(), value);
        }
    }

    /**
     * 根据Iterable接口的key，和Generator接口的value填充本类
     * @param ks
     * @param vGen
     */
    public MapData(Iterable<K> ks, Generator<V> vGen){
        for(K key : ks){
            put(key, vGen.next());
        }
    }

    /**
     * 根据Iterable接口的key 和固定的value填充本类
     * @param ks
     * @param value
     */
    public MapData(Iterable<K> ks, V value){
        for(K key : ks){
            put(key, value);
        }
    }

    //通用的使用方法，优雅的使用访法
    public static <K,V> MapData map(Generator<Pair<K,V>> pair, int quantity){
        return new MapData(pair,quantity);
    }
    public static <K,V> MapData map(Generator<K> kGen,Generator<V> vGen, int quantity){
        return new MapData(kGen,vGen,quantity);
    }
    public static <K,V> MapData map(Generator<K> kGen, V value, int quantity){
        return new MapData(kGen,value,quantity);
    }
    public static <K,V> MapData map(Iterable<K> ks, Generator<V> vGen){
        return new MapData(ks,vGen);
    }
    public static <K,V> MapData map(Iterable<K> ks, V value){
        return new MapData(ks,value);
    }

}
