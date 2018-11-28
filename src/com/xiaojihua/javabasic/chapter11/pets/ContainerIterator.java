package com.xiaojihua.javabasic.chapter11.pets;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、LinkedList\HashSet\TreeSet等都可以通过接收List进行初始化
 * 2、display方法使用了接口与几口的实现分离的知识，此方法可以接收任何能生成iterator对象的Collection，而不去关心具体是哪一种Collection
 * 3、iterator的用法
 */
public class ContainerIterator {

    public static void display(Iterator<Pet> iterator){
        while(iterator.hasNext()){
            printnb(iterator.next() + " ");
        }
        print();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        List<Pet> pets = Pets.arrayList(8);
        LinkedList<Pet> petLl = new LinkedList<>(pets);
        HashSet<Pet> petHs = new HashSet<>(pets);
        //打印TreeSet会发现元素个数比上面的少，这是因为，TreeSet是按照自然排序（实现了Comparable接口）排列元素
        //由于Pet中重写了equals方法，因此在出现相同Pet实例的时候会出现相等的情况，而set又不允许重复，因此就去掉了重复的元素
        //数量也就减少了。也就是说，TreeSet是通过equals排序，并且通过equals排重
        TreeSet<Pet> petTs = new TreeSet<>(pets);
        display(pets.iterator());
        display(petLl.iterator());
        display(petHs.iterator());
        display(petTs.iterator());
    }
}
