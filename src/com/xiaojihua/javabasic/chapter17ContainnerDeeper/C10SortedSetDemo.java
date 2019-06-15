package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、SortedSet接口的使用，排序依据是根据存储对象的comparable接口的实现逻辑
 */
public class C10SortedSetDemo {
    public static void main(String[] args){
        SortedSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet,"one two three four five six seven eight".split(" "));
        print(sortedSet);
        String low = sortedSet.first();
        String hight = sortedSet.last();
        print(low);
        print(hight);

        Iterator<String> iterator = sortedSet.iterator();
        for(int i=0; i<=6; i++){
            if(i == 3) low = iterator.next();
            if(i == 6) hight = iterator.next();
            else iterator.next();
        }
        print(low);
        print(hight);

        print(sortedSet.subSet(low,hight));
        print(sortedSet.headSet(hight));
        print(sortedSet.tailSet(low));
    }
}
