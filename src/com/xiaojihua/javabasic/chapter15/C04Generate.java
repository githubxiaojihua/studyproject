package com.xiaojihua.javabasic.chapter15;

/**
 * 生成器
 * 生成指定参数类型的类
 *
 * 知识点：
 * 1、泛型也可应用于接口
 * @param <T>
 */
public interface C04Generate<T> {
    T next();
}
