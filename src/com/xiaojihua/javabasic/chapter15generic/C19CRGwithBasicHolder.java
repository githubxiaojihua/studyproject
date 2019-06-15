package com.xiaojihua.javabasic.chapter15generic;

/**
 * 知识点：
 * 1、curiously recurring generics (CRG)的概念：像SubType类那样的
 * 在<b>基类</b>泛型参数中设置为<b>自己或者其他基类的导出类</b>的形式。
 * 2、java中的泛型是与参数和返回类型想关系的。CRG使得base class（这里
 * 指的是Subtype的基类BasicHold）可以在相关参数与返回类型处设置为subType.
 * 3、CGR的精髓：This is the essence of CRG: The base class substitutes
 * the derived class for its parameters.（基类使用导出类替换类型参数），
 * 通过使用CRG使得基类（此处为BasicHold）变为一个针对于导出了的通用性模版，
 * 但是模板中的所有参数类型和返回值都是导出类。
 */
public class C19CRGwithBasicHolder {
    public static void main(String[] args){
        //模板类（基类）的不同实现
        SubType sub1 = new SubType(), sub2 = new SubType();
        sub1.setT(sub2);
        SubType sub3 = sub1.getT();
        sub1.f();
    }
}

/**
 * 基类
 * 在CRG模式下相当于导出类的通用性模版，在实际代码的编写过程中
 * T将会被作为导出类对待（擦除机制仍然是起作用的）。
 * @param <T>
 */
class BasicHold<T>{
    private T t;
    public void setT(T item){
        t = item;
    }
    public T getT(){
        return t;
    }
    public void f(){
        System.out.println(t.getClass().getSimpleName());
    }
}

/**
 * 使用CRG的导出类，
 * 将基类的模版应用到本类，这样基类中的所有
 * 参数类型和返回值均为导出类。
 */
class SubType extends BasicHold<SubType>{}