package com.xiaojihua.javabasic.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 知识点：
 * 一个针对Set的工具类
 * 1、使用泛型方法
 * 2、并不修改原始Set
 */
public class Sets {
    /**
     * 并集操作
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b){
        //建立新的Set，不修改原始数据
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    /**
     * 交集操作
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> Set<T> intersection(Set<T> a, Set<T> b){
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    /**
     * 从superSet中去掉subSet中的元素
     * @param superSet
     * @param subSet
     * @param <T>
     * @return
     */
    public static <T> Set<T> differenc(Set<T> superSet, Set<T> subSet){
        Set<T> result = new HashSet<>(superSet);
        result.removeAll(subSet);
        return result;
    }

    /**
     * 补集：从a和b中的所有元素中去掉a和b的交集
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> Set<T> complement(Set<T> a, Set<T> b){
        return differenc(union(a, b), intersection(a, b));
    }
}
