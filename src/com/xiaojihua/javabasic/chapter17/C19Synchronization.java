package com.xiaojihua.javabasic.chapter17;

import java.util.*;

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
