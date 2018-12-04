package com.xiaojihua.javabasic.chapter11;

import java.util.Iterator;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：任何实现了Iterable接口的类都可以在foreach语句中使用
 */
public class IterableClass implements Iterable<String> {
    String[] words = "And this is how we know the Earth to be abdd".split(" ");

    /**
     * 实现方法
     * @return
     */
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        IterableClass iterableClass = new IterableClass();
        //在foreach语句中使用
        for(String str : iterableClass){
            print(str);
        }
    }
}
