package com.xiaojihua.javabasic.chapter15generic;

import com.xiaojihua.javabasic.chapter15generic.coffee.Coffee;
import com.xiaojihua.javabasic.chapter15generic.coffee.CoffeeGenerator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 知识点：
 * 1、通过生成器来填充Collection
 * 2、配合泛型方法使用。
 */
public class C11Generators {
    public static <T> Collection<T> fill(Collection<T> col, C04Generate<T> gen, int n){
        for(int i=0; i<n; i++){
            col.add(gen.next());
        }
        return col;
    }

    public static void main(String[] args){
        Collection<Coffee> coffees = fill(new ArrayList<>(), new CoffeeGenerator(), 5);
        System.out.println(coffees);
        Collection<Integer> integers = fill(new ArrayList<>(), new C05Fibonacci(), 10);
        System.out.println(integers);
    }
}
