package com.xiaojihua.javabasic.util;

import java.util.ArrayList;

/**
 * 工具：集合生成工具
 *
 * 通过Generator填充，生成一个Collection
 *
 * 类上的泛型和方法上的泛型可以同时使用。
 * @param <T>
 */
public class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> gen, int size){
        for(int i=0; i<size; i++){
            add(gen.next());
        }
    }

    /**
     * 优雅的使用方式
     * @see com.xiaojihua.javabasic.chapter15.latantType.C03Fill2
     * 中的AdapterHelper.getCa方法
     * @param gen
     * @param size
     * @param <T>
     * @return
     */
    public static <T> CollectionData<T> list(Generator<T> gen, int size){
        return new CollectionData<>(gen,size);
    }
}
