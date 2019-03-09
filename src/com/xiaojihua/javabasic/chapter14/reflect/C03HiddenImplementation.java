package com.xiaojihua.javabasic.chapter14.reflect;

import com.xiaojihua.javabasic.chapter14.reflect.innerPackage.C02HiddenC;

import java.lang.reflect.Method;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、反射机制部分方法的是使用
 * 通过反射机制可以访问包括private之内的任何方法和属性
 */
public class C03HiddenImplementation {
    public static void main(String[] args) throws Exception {
        /**
         * 这里注意getC()返回的是下一层包：innerPackage中的C类，
         * 由于C是默认访问权限，也就包访问权限因此在此处是不能直接访问的。
         * 比如： a instanceof C就会报编译错误。当然如果C与本类在同一个
         * 包下，那么这句会返回true.
         */
        C01A a = C02HiddenC.getC();
        a.f();
        //a的具体类型实际上是C
        print(a.getClass().getSimpleName());

        //通过反射机制访问不同访问级别的方法。
        callHiddenMethods(a,"g");
        callHiddenMethods(a,"u");
        callHiddenMethods(a,"w");
        callHiddenMethods(a,"x");

    }

    /**
     * 通过反射机制访问给定对象的指定方法
     * @param a
     * @param methodName
     * @throws Exception
     */
    public static void callHiddenMethods(Object a, String methodName) throws Exception {
        //根据名称返回方法名
        Method method = a.getClass().getDeclaredMethod(methodName);
        //设置可访问为true
        method.setAccessible(true);
        //调用
        method.invoke(a);
    }
}
