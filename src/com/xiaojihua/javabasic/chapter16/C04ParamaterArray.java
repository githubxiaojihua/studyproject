package com.xiaojihua.javabasic.chapter16;

/**
 * 知识点：
 * 1、数组与泛型的结合使用
 *
 */
public class C04ParamaterArray {
    public static void main(String[] args){

        Integer[] ints = {1,2,3,4,5};
        Double[] doubles = {1.1,2.2,3.3,4.4};

        Integer[] ints1 = new ClassParamater<Integer>().f(ints);
        Double[] doubles1 = new ClassParamater<Double>().f(doubles);

        ints1 = MethodParamater.g(ints);
        doubles1 = MethodParamater.g(doubles);

        //不能创建泛型数组，但是可以创建泛型数组的引用。比如List<String>[] ls;
        //ClassParamater<Integer>[] test = new ClassParamater<Integer>[11];

    }
}

class ClassParamater<T>{
    public T[] f(T[] g){
        return g;
    }
}

/**
 * 相对于泛型类更佳灵活
 * 不用针对每一个类型都new 新的实例
 * static使得方法更加灵活
 */
class MethodParamater{
    public static  <T> T[] g(T[] f){
        return f;
    }
}