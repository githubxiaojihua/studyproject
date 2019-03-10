package com.xiaojihua.javabasic.chapter15;

/**
 * 知识点：
 * 1、对于泛型类的集成以及扩展
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class C02ThreeTuple<A, B, C> extends C01TwoTuple<A, B>{
    public final C third;
    C02ThreeTuple(A first, B second, C third){
        super(first, second);
        this.third = third;
    }

    public String toString(){
        return "{" + first + "," + second + "," + third + "}";
    }
}
