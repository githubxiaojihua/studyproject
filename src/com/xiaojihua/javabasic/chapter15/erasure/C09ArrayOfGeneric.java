package com.xiaojihua.javabasic.chapter15.erasure;

/**
 * 知识点：
 * 1、Generic这样的类的数组，不能通过Object数组进行强制转换。
 * 虽然编译期可以通过但是运行时会报ClassCastException
 * 2、Generic这样的类的数组，不能直接使用new Generic<Integer>[Size]
 * 来初始化，也就是说new泛型类的时候不能带有类型参数，只能使用被擦除
 * 后的原始类型new Generic[SIZE]。因为Generic<T>擦除后为Generic
 */
public class C09ArrayOfGeneric {
    private static final int SIZE = 100;
    private static Generic<Integer>[] generics;

    @SuppressWarnings("unchecked")
    public static void main(String[] args){
        //这一句虽然也可以编译通过，但是运行的时候会报ClassCastException
        generics = (Generic<Integer>[]) new Object[SIZE];
        //这一句要注意不能直接new Generic<Integer>[SIZE]
        generics = (Generic<Integer>[]) new Generic[SIZE];
        generics[0] = new Generic<>();
        //这一句会出现编译器错误
        //generics[0] = new Generic<Double>();
    }
}

class Generic<T>{}
