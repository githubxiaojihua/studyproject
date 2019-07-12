package com.xiaojihua.javabasic.chapter20Annotation.userReflectionForAannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface C04SqlInt {
    public String name() default "";
    public C02Constraints constraints() default @C02Constraints;
}
