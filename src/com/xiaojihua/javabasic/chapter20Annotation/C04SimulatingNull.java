package com.xiaojihua.javabasic.chapter20Annotation;

/**
 * annotion的属性在用的时候是不能为null，或者不设置的，
 * 但是可以使用一些默认值来模仿不设置，
 * 这也是一种常用的annotion定义使用方法
 */
public @interface C04SimulatingNull {
    public int id() default -1;
    public String description() default "";
}
