package com.xiaojihua.javabasic.chapter19enum.multipleDispatching;

import com.xiaojihua.javabasic.util.Enums;

public class C04RoShamBo2 {
    /**
     * 静态方法有且于减少泛型代码的键入量
     * 有点类似于chapter15generic/latantType/C03Fill2.java的AdapterHelper方法的说明
     * @param t1
     * @param t2
     * @param <T>
     * @return
     */
    public static <T extends C03Competitor<T>> String compete(T t1, T t2){
        return t1 + " vs " + t2 + ":" +t1.compete(t2);
    }

    /**
     * 静态方法有且于减少泛型代码的键入量
     * 有点类似于chapter15generic/latantType/C03Fill2.java的AdapterHelper方法的说明
     * @param clazz
     * @param nums
     * @param <T>
     */
    public static <T extends Enum<T> & C03Competitor<T>> void play(Class<T> clazz, int nums){
        for(int i=0; i<nums; i++){
            System.out.println(compete(Enums.randomNext(clazz),Enums.randomNext(clazz)));
        }
    }
}
