package com.xiaojihua.javabasic.chapter10;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：内部类和迭代器设计模式
 * 内部类可以访问外部类的任何对象。
 * 迭代器设计模式。
 * @param <T>
 */

/**
 * 迭代器接口
 * @param <T>
 */
interface Selector<T>{
    boolean end();
    T current();
    void next();
}

public class Sequence<U> {
    private U[] items;
    private int i;
    Sequence(int length){
        items = (U[]) new Object[length];
    }
    public void add(U item){
        if(i < items.length){
            items[i++] = item;
        }
    }

    /**
     * 返回迭代器
     * @return
     */
    public Selector selector(){
        return new SequenceSelector();
    }

    /**
     * 内部类实现迭代器借口
     * 可以访问外部类的任何资源
     */
    private class SequenceSelector implements Selector<U>{
        int next = 0;
        @Override
        public boolean end() {
            return next >= items.length;
        }

        @Override
        public U current() {
            return items[next];
        }

        @Override
        public void next() {
            if(next < items.length){
                next++;
            }
        }
    }

    public static void main(String[] args) {
        Sequence<Integer> sequence = new Sequence<>(10);
        for(int i = 0; i < 10; i++){
            sequence.add(i);
        }
        Selector selector =sequence.selector();
        while(!selector.end()){
            printnb(selector.current());
            selector.next();
        }
    }
}
