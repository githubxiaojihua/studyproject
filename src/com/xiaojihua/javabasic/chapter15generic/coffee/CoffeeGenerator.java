package com.xiaojihua.javabasic.chapter15generic.coffee;

import com.xiaojihua.javabasic.chapter15generic.C04Generate;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 泛型应用之一：生成器
 * coffee类的生成器
 * 1、通过普通的生成器next来生成
 * 2、通过实现Iterator来生成。内部还是调用了生成器的next方法
 *
 */
public class CoffeeGenerator implements C04Generate<Coffee>,Iterable<Coffee> {
    private Class[] types = {Latte.class, Americano.class, Cappuccino.class, Mocha.class};//可用于生成的类型
    private static Random random = new Random(47);
    private int count = 0;//末端哨兵，用来探测何时停止。

    public CoffeeGenerator(){}

    public CoffeeGenerator(int size){ count = size; }

    /**
     * 实现生成器的next方法，返回随机的Coffee
     * @return
     */
    @Override
    public Coffee next() {
        Coffee coffee = null;
        try {
            coffee = (Coffee)types[random.nextInt(types.length)].newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return coffee;
    }

    /**
     * Iterable的iterator方法，返回Iterator类
     * @return
     */
    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    /**
     * 内部类具体实现Iterator
     */
    class CoffeeIterator implements Iterator<Coffee>{

        @Override
        public boolean hasNext() {
            return count >= 0;
        }

        @Override
        public Coffee next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            count--;
            return  CoffeeGenerator.this.next();

        }
    }

    public static void main(String[] args) {
        //调用普通的生成器，生成对象
        CoffeeGenerator coffeeGenerator = new CoffeeGenerator();
        for(int i = 0; i < 5; i++){
            System.out.println(coffeeGenerator.next());
        }

        //通过迭代器生成对象，内部还是调用生成器的方法
        CoffeeGenerator coffeeGenerator1 = new CoffeeGenerator(5);
        //实现了Iterable接口就可以用于foreach语句。
        for(Coffee coffee : coffeeGenerator1){
            System.out.println(coffee);
        }
    }


}
