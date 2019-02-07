package com.xiaojihua.javabasic.chapter13;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * 1、在toString中打印对象的地址，不能用this，否则会造成无限递归调用。
 * 应该用super.toString代替。
 */
public class C03InfiniteRecursion {
    public String toString(){
        //如果按照如下写法，将会出现无限递归
        //return "C03InfiniteRecursion:" + this;
        return "C03InfiniteRecursion:" + super.toString();
    }

    public static void main(String[] args) {
        List<C03InfiniteRecursion> v = new ArrayList<>();
        for(int i=0; i < 10; i++){
            v.add(new C03InfiniteRecursion());
        }
        System.out.println(v);
    }
}
