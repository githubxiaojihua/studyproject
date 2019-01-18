package com.xiaojihua.javabasic.chapter11;

import com.xiaojihua.javabasic.chapter11.pets.C00Pet;
import com.xiaojihua.javabasic.chapter11.pets.Pets;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * 知识点：AbstractCollection抽象类的使用
 * AbstracConllection类实现了部分collection接口，通过扩展此抽象类，可以实现快速实现Collection
 *
 * 当一个类已经继承了一个类，但是还想实现Iterator相关功能，那么可以直接提供一个返回Iterator接口的方法，可以使用匿名内部类，比如下面的iterator()。
 */
public class C06CollectionSequence extends AbstractCollection<C00Pet> {
    //list转换成array
    private C00Pet[] pets = Pets.arrayList(9).toArray(new C00Pet[0]);

    @Override
    public Iterator<C00Pet> iterator() {
        return new Iterator<C00Pet>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < pets.length;
            }

            @Override
            public C00Pet next() {
                return pets[index++];
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public int size() {
        return pets.length;
    }

    public static void main(String[] args) {
        C06CollectionSequence c = new C06CollectionSequence();
        //调用两个display方法
        C05InterfaceVsIterator.display(c);
        C05InterfaceVsIterator.display(c.iterator());
    }
}
