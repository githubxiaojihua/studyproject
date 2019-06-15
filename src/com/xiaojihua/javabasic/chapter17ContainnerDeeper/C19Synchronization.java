package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.*;

/**
 * 知识点：
 * 1、简单列举通过Collections.synchronized***()方法建立Snychronizing
 * Collections和Maps.
 * 关于多线程的同步会在第21章单独讲解
 */
public class C19Synchronization {
    public static void main(String[] args){
        Collection<String> collection = Collections.synchronizedCollection(new ArrayList<>());
        List<String> list= Collections.synchronizedList(new ArrayList<>());
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> sortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String,String> sortedMap = Collections.synchronizedSortedMap(new TreeMap<>());
    }
}
