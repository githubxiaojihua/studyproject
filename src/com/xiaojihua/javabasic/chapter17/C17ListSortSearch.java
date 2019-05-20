package com.xiaojihua.javabasic.chapter17;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、list的排序和查找
 */
public class C17ListSortSearch {
    public static void main(String[] args){
        List<String> list = new ArrayList<>(C16CollectionsUtilities.list);
        list.addAll(C16CollectionsUtilities.list);
        print(list);

        //随机排序
        Collections.shuffle(list,new Random(47));
        print("shuffled:" + list);

        //返回ListIterator，只有ListIterator才有remove
        ListIterator<String> listIterator = list.listIterator(10);
        while(listIterator.hasNext()){
           listIterator.next();
           listIterator.remove();
        }
        print("Trimmed:" + list);

        //自然排序
        Collections.sort(list);
        print("sorted:" + list);

        String key = list.get(7);
        //二分查找
        int index = Collections.binarySearch(list,key);
        print("Location of " + key + " is " + index + ", list.get(" + index + ")=" + list.get(index));

        //指定排序Comparator
        Collections.sort(list,String.CASE_INSENSITIVE_ORDER);
        print("CASE_INSENSITIVE_ORDER:" + list);
        key = list.get(7);
        //二分查找的时候需要根据List的排序规则，比如上面用String.CASE_INSENSITIVE_ORDER
        //进行了sort那么在binarySearch的时候也要使用Strng.CASE_INSENSITIVE_ORDER
        index = Collections.binarySearch(list,key,String.CASE_INSENSITIVE_ORDER);
        print("Location of " + key + " is " + index + ", list.get(" + index + ")=" + list.get(index));

    }
}
