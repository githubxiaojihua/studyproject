package com.xiaojihua.javabasic.chapter15generic;

/**
 * 知识点：
 * 1、自限定类型天生具有协变返回类型。父类中的方法返回值均是具体的导出类型
 *
 * 自限定类型的几个要求可以参考
 * @see C20SelfBounding
 *
 */
public class C22GenericsAndReturnType {
    public void test(Getter getter){
        Getter getter1 = getter.get();
        GenericsGetter getter2 = getter.get();
    }
}

interface GenericsGetter<T extends GenericsGetter<T>>{
    T get();
}

interface Getter extends GenericsGetter<Getter>{}