package com.xiaojihua.javabasic.genericity;

/**
 * 生成器
 * 生成指定参数类型的类
 * @param <T>
 */
public interface Generate<T> {
    T next();
}
