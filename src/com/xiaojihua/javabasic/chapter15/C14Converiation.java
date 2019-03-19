package com.xiaojihua.javabasic.chapter15;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 知识点：
 * 1、容器的泛型不支持协变。但是数组是支持协变的。
 * 2、所谓的协变就是：
 */
public class C14Converiation {
    public static void main(String[] args) {
        Collection<A> ac = new ArrayList<>();
        Collection<B> bc = new ArrayList<>();
        System.out.println(total(ac));
        //System.out.println(total(bc));
    }

    public static int total(Collection<A> collection){
        int totals = 0;
        for(A a : collection){
            totals += a.m();
        }
        return totals;
    }
}

class A{ public int m(){return 1;} }
class B extends A {}

