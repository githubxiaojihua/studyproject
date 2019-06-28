package com.xiaojihua.javabasic.chapter19enum;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * enum的基本使用，当创建一个enum的时候，JVM会自动创建一个与之相关联的类，
 * 这个类继承自java.lang.Enum，同时提供了下面的一些方法。
 * 个人理解enum和Enum的关系像非包装类和包装类之间的关系。
 */
public class C01EnumClass {
    public static void main(String[] args){
        /*
            values()返回声明的实例GROUND,CRAWING,HANGING
         */
        for(Shrubbery s : Shrubbery.values()){
            //ordinal返回实例的声明位置
            print(s + "ordinal:" + s.ordinal());
            /*
                可以进行compareTo\equals\==等操作
             */
            printnb(s.compareTo(Shrubbery.CRAWING) + " ");
            printnb(s.equals(Shrubbery.CRAWING) + " ");
            print((s == Shrubbery.CRAWING) + " ");
            //getDeclaringClass返回的是class com.xiaojihua.javabasic.chapter19enum.Shrubbery
            print(s.getDeclaringClass() + " ");
            //返回的是实例的声明名称
            print(s.name() + " ");
            print("=================================");
        }

        for(String s : "GROUND CRAWING HANGING".split(" ")){
            //Enum.valueOf可以根据enum类和对应的实例名称，构建enum类的对应实例
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class,s);
            //toString与name()方法作用一致
            print(shrubbery);
        }
    }
}

/**
 * 声明一个enum类名称为Shrubbery
 * 其中的GROUND,CRAWING,HANGING的每一个都是Shrubbery的一个实例。
 */
enum Shrubbery{
    GROUND,CRAWING,HANGING
}
