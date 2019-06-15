package com.xiaojihua.javabasic.chapter17ContainnerDeeper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.xiaojihua.javabasic.util.Print.*;

public class C06CollectionMethods {
    public static void main(String[] args){
        Collection<String> c = new ArrayList<>();
        c.addAll(C05Countries.names(6));
        c.add("ten");
        c.add("eleven");
        print(c);

        //从列表创建数组
        Object[] array = c.toArray();
        String[] str = c.toArray(new String[0]);

        //获取最大和最小值，这根据c对Comparable接口的实现不同而不同
        print("Collections.max(c):" + Collections.max(c));
        print("Collections.min(c):" + Collections.min(c));

        Collection<String> c2 = new ArrayList<>();
        c2.addAll(C05Countries.names(6));
        //将一个collection中的元素添加到另一个collection
        c.addAll(c2);
        print(c);

        //删除某个元素
        c.remove(C05Countries.DATA[0][0]);
        print(c);
        c.remove(C05Countries.DATA[1][0]);
        print(c);

        //将一个collection中的元素从另一个collection中删除
        c.removeAll(c2);
        print(c);
        c.addAll(c2);
        print(c);

        String val = C05Countries.DATA[3][0];
        //判断是否包含
        print("c.contains(val):" + c.contains(val));

        print("c.containsAll(c2):" + c.containsAll(c2));

        Collection<String> c3 = ((ArrayList<String>) c).subList(3,5);

        c2.retainAll(c3);
        print(c2);

        c2.removeAll(c3);
        print("c2.isEmpty()=" + c2.isEmpty());

        c = new ArrayList<>();
        c.addAll(C05Countries.names(6));
        print(c);
        c.clear();
        print("after c.clear():" + c);
    }
}
