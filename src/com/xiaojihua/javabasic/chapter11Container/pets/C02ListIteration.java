package com.xiaojihua.javabasic.chapter11Container.pets;

import java.util.List;
import java.util.ListIterator;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * ListIterator的用法：往前、往后迭代、set方法
 *
 */
public class C02ListIteration {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        List<C00Pet> pets = Pets.arrayList(8);
        ListIterator<C00Pet> listIterator = pets.listIterator();
        print(pets);
        //往后索引
        while(listIterator.hasNext()){
            //当第一调用的时候返回-1
            printnb(listIterator.previousIndex());
            //提供往前和往后的遍历机制
            //nextIndex代表迭代器下一次要返回元素的索引，priviousIndex返回最近一次调用next后返回元素的索引
            printnb(listIterator.next() + ", " + listIterator.nextIndex() + ", " + listIterator.previousIndex());
        }
        print();
        //往前索引
        while(listIterator.hasPrevious()){
            printnb(listIterator.previous() + " ");
        }
        print();
        //从指定索引返回迭代器
        listIterator = pets.listIterator(1);
        while(listIterator.hasNext()){
            printnb(listIterator.next() + " ");
            //listIterator还可以提供set方法设置值
            listIterator.set(Pets.next());
        }
        print();
        print(pets);
    }
}
