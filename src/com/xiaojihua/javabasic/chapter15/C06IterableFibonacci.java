package com.xiaojihua.javabasic.chapter15;

import java.util.Iterator;

/**
 * 知识点：
 * C05Fibonacci的基础上再增加Iterable功能。
 * 假设C05Fibonacci不能进行源码修改，那么只能通过使用适配器模式来解决。
 * 适配器模式可以有很多种实现方式，比如组合和集成。
 * 下面使用继承来实现。
 * 所以本类实际上是C05Fibonacci的适配器类，用来改造C05Fibonacci类来实现
 * Iterable功能
 */
public class C06IterableFibonacci extends C05Fibonacci implements Iterable<Integer> {
    private int count = 0;//末端哨兵

    C06IterableFibonacci(int count){
        this.count = count;
    }

    /**
     * 匿名内部类实现Iterable接口
     * @return
     */
    public Iterator<Integer> iterator(){
        return new Iterator<Integer>(){
            private int size = count;

            @Override
            public boolean hasNext(){
                return size > 0;
            }

            @Override
            public Integer next(){
                if(hasNext()){
                    size--;
                    return C06IterableFibonacci.this.next();
                }else{
                    throw new RuntimeException("There is no Elements!");
                }
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args){
        for(Integer integer : new C06IterableFibonacci(18)){
            System.out.print(integer + " ");
        }
    }
}
