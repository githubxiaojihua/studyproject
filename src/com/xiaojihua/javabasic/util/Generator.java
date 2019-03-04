package com.xiaojihua.javabasic.util;

/**
 * 生成器通用接口
 * @param <T>
 */
public interface Generator<T> {
    T next();
}
