package com.xiaojihua.javabasic.chapter15generic;

import java.lang.reflect.Method;
import java.util.*;

import static com.xiaojihua.javabasic.util.Sets.*;

/**
 * 本类用于比较给定两个类型之间的方法差异
 * 知识点：
 * 1、泛型方法的使用
 * 2、注意Class类的getMethods方法和getDeclaredMethods()方法的差异，
 * 其中getMethods将返回所有本类以及继承过来的方法，getDeclearedMethods()
 * 却不包含继承过来的方法
 */
public class C13ContainerMethodDifferences {
    /**
     * 返回给定类型的所有方法，包括继承过来的
     * @param type
     * @return
     */
    public static Set<String> methodsSet(Class<?> type){
        Set<String> result = new TreeSet<>();
        //应该注意getMethods和getDeclearedMethods()方法的区别
        for(Method method : type.getMethods()){
            result.add(method.getName());
        }
        return result;
    }

    private static Set<String> object = methodsSet(Object.class);
    static{ object.add("clone"); }

    /**
     * 打印指定接类型所实现或者继承的接口
     * @param type
     */
    public static void interfaces(Class<?> type){
        List<String> result = new ArrayList<>();
        for(Class<?> c : type.getInterfaces()){
            result.add(c.getSimpleName());
        }
        System.out.println(result);
    }

    /**
     * 打印给定的两个类型之间方法的差异
     * 这里默认subType 继承自 superType
     * @param subType
     * @param superType
     */
    public static void difference(Class<?> subType, Class<?> superType){
        Set<String> result = differenc(methodsSet(subType), methodsSet(superType));
        result.removeAll(object);
        System.out.println(result);

        interfaces(subType);
    }

    public static void main(String[] args){
        interfaces(Collection.class);
        difference(Set.class, Collection.class);
    }
}
