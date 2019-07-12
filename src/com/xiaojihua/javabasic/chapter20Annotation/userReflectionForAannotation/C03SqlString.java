package com.xiaojihua.javabasic.chapter20Annotation.userReflectionForAannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface C03SqlString {
    public int value() default 0;
    public String name() default "";
    //注解土元素的值也可以是注解
    public C02Constraints constraints() default @C02Constraints;
}
