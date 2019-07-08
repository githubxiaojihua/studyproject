package com.xiaojihua.javabasic.chapter19enum.multipleDispatching;

/**
 * 注意是自限定类型
 * @param <T>
 */
public interface C03Competitor<T extends C03Competitor<T>> {
    C01OutCome compete(T competitor);
}
