package com.xiaojihua.javabasic.util;

import java.util.Random;

/**
 * 知识点：
 * 一个产生随机enum的工具
 */
public class Enums {
    private static Random random = new Random(47);

    /**
     * 通过<T extens Enum<T>> 来限定，tClass为Enum的子类，因此
     * 保证getEnumConstants()会有值产生
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> T randomNext(Class<T> tClass){
        return randomNext(tClass.getEnumConstants());
    }

    /**
     * 重载的randomNext方法，接收T[].
     * 设置成public的，方便不同情况下的调用
     * @param values
     * @param <T>
     * @return
     */
    public static <T> T randomNext(T[] values){
        return values[random.nextInt(values.length)];
    }
}
