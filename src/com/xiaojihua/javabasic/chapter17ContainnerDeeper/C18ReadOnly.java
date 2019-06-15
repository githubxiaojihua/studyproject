package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、创建不可修改的Collection和Map
 * 2、当对不可修改的Collection和Map调用更改方法（add等）方法的时候，并不会进行
 * 编译期间的安全检查，编译期是可以通过的，但是在运行期会报UnsupportedOperationException.
 * 3、最佳实践：
 * （1）将可修改的Collection和Map的引用设置为private，然后对外提供经过unmodifiable***()方法
 * 返回的不可修改的引用。这样在类内部提供可修改的private引用，对外只能使用不可修改的引用，
 * 防止这边刚生成了不可修改的Collection或者Map，却修改了原始的。
 * （2）可以在unmodifiable****（）方法中直接传入new ArrayList()，这样就只有一个
 * 不可修改的引用。
 */
public class C18ReadOnly {
    private static Collection<String> data = new ArrayList<>(C05Countries.names(6));
    public static void main(String[] args){
        //创建不可修改的Collection
        Collection<String> collection = Collections.unmodifiableCollection(new ArrayList<>(data));
        print(collection);
        //collection.add("one");//不可修改

        //创建不可修改的List
        List<String> list = Collections.unmodifiableList(new ArrayList<>(data));
        ListIterator<String> listIterator = list.listIterator();
        print(listIterator.next());
        //listIterator.add("one");//不可修改

        //创建不可修改的Set
        Set<String> set = Collections.unmodifiableSet(new HashSet<>(data));
        print(set);
        //set.add("one");//不可修改

        //创建不可修改的sortedSet
        Set<String> sortedSet = Collections.unmodifiableSortedSet(new TreeSet<>(data));

        //创建不可修改的Map
        Map<String,String> map = Collections.unmodifiableMap(new HashMap<>(C05Countries.capital()));
        print(map);
        //map.put("a","b");//不可修改

        //创建不可修改的sortedMap
        Map<String,String> sortedMap = Collections.unmodifiableSortedMap(new TreeMap<>(C05Countries.capital()));
    }
}
