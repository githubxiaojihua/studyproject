package com.xiaojihua.javabasic.chapter20Annotation.userReflectionForAannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface C02Constraints {
    public boolean isPrimary() default false;
    public boolean nullAble() default true;
    public boolean unique() default false;
}
