package com.xiaojihua.javabasic.chapter20Annotation.userReflectionForAannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface C01DBTable {
    public String name() default "";
}
