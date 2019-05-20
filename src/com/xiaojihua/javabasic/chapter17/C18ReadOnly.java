package com.xiaojihua.javabasic.chapter17;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

public class C18ReadOnly {
    private static Collection<String> data = new ArrayList<>(C05Countries.names(6));
    public static void main(String[] args){
        Collection<String> collection = Collections.unmodifiableCollection(new ArrayList<>(data));
        print(collection);
        //collection.add("one");

        List<String> list = Collections.unmodifiableList(new ArrayList<>(data));
        ListIterator<String> listIterator = list.listIterator();
        print(listIterator.next());
        //listIterator.add("one");

        Set<String> set = Collections.unmodifiableSet(new HashSet<>(data));
        print(set);
        //set.add("one");

        Set<String> sortedSet = Collections.unmodifiableSortedSet(new TreeSet<>(data));

        Map<String,String> map = Collections.unmodifiableMap(new HashMap<>(C05Countries.capital()));
        print(map);
        //map.put("a","b");

        Map<String,String> sortedMap = Collections.unmodifiableSortedMap(new TreeMap<>(C05Countries.capital()));
    }
}
