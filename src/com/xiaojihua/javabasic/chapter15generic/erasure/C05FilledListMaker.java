package com.xiaojihua.javabasic.chapter15generic.erasure;

import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * 1、当泛型应用于List的时候，虽然在代码中，比如create中擦除了T的具体类型
 * 但是编译器仍然会知道往list中add的是具体的T类型。编译器保证了泛型方法的
 * 内部一致性。比如create方法中虽然T被擦除了，但是编译器仍然会保证整个方法
 * 中T的一致性，即如果T被声明为String那么在add过程中会保证类型的一致性
 * @param <T>
 */
public class C05FilledListMaker<T> {
    public List<T> create(T t, int size){
        List<T> list = new ArrayList<>();
        for(int i=0; i < size; i++){
            //由于保证了一致性因此不需要对t进行类型转换，虽然他被擦除到Object了
            list.add(t);
        }
        return list;
    }

    public static void main(String[] args){
        C05FilledListMaker<String> fl = new C05FilledListMaker<>();
        List<String> list = fl.create("abc", 5);
        System.out.println(list);
    }
}
