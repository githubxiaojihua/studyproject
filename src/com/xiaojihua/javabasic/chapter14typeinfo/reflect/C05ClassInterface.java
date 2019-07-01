package com.xiaojihua.javabasic.chapter14typeinfo.reflect;

import com.xiaojihua.javabasic.Test;

import java.lang.reflect.Type;

/**
 * 知识点：
 * Class类的 getGenericInterfaces、getGeneircSuperclass、getInterfaces的用法
 * getGenericInterfaces，返回Class类继承的接口，如果接口中有参数类型（泛型）那么会返回参数类型
 * 的实际类型。getGeneircSuperclass返回父类，如果父类中有参数类型，那么会返回参数类型的实际类型
 * 。getInterfaces返回继承的 接口，但是不会返回 参数类型，无论接口中是否有参数类型。
 */
public class C05ClassInterface  implements  A<String> {
    public static void main(String[] args) throws Exception{

        for(Type t : Test.class.getGenericInterfaces()){
            System.out.println(t);
        }

        System.out.println(Test.class.getGenericSuperclass());

        for(Class<?> c : Test.class.getInterfaces()){
            System.out.println(c.getName());
        }
    }

    public String ab(){
        return "ddd";
    }
}

interface A <B>{
    B ab();
}
