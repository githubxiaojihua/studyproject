package com.xiaojihua.javabasic.chapter11;

import com.xiaojihua.javabasic.chapter11.pets.Pet;
import com.xiaojihua.javabasic.chapter11.pets.Pets;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.List;

/**
 * 知识点：AbstractCollection抽象类的使用
 * AbstracConllection类实现了部分collection接口，通过扩展此抽象类，可以实现快速实现Collection
 *
 * 当一个类已经继承了一个类，但是还想实现Iterator相关功能，那么可以直接提供一个返回Iterator接口的方法，可以使用匿名内部类，比如下面的iterator()。
 */
public class CollectionSequence extends AbstractCollection<Pet> {
    //list转换成array
    private Pet[] pets = Pets.arrayList(9).toArray(new Pet[0]);

    @Override
    public Iterator<Pet> iterator() {
        return new Iterator<Pet>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < pets.length;
            }

            @Override
            public Pet next() {
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
        CollectionSequence c = new CollectionSequence();
        //调用两个display方法
        InterfaceVsIterator.display(c);
        InterfaceVsIterator.display(c.iterator());
    }
}
