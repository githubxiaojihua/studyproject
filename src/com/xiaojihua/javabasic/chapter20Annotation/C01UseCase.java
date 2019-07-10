package com.xiaojihua.javabasic.chapter20Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 知识点：
 * annotation的定义，除了一些定义注解外其它的跟接口类似
 */
/*
    定义annotion应用的目的地：Constructor,field, local_variable, method,
    package, parameter, type(class,interface)

 */
@Target(ElementType.METHOD)
/*
    定义annotion的生存期限。runtime是直到运行时都有郊
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface C01UseCase {
    //定义annotion的属性
    public int id();
    //元素可以有默认值
    public String description() default "no description";
}
