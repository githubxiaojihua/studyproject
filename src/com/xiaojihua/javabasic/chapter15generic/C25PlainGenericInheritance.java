package com.xiaojihua.javabasic.chapter15generic;

/**
 * 知识点：
 * 1、在没有自限定类型的规则下，泛型对于“协变参数类型”的表现与非泛型代码一直
 * @see C23OrdinaryArgument
 */
public class C25PlainGenericInheritance {
    public static void main(String[] args){
        Base base = new Base();
        Derived derived = new Derived();
        SubGeneicBasic subG = new SubGeneicBasic();
        subG.set(base);
        subG.set(derived);
    }
}

class GenericBasic<T>{
    public void set(T t){
        System.out.println("GenericBasic.set()");
    }
}

/**
 * 注意GenericBasic<Base> 这里的类型参数为Base。这样
 * 基类的set方法的类型参数就是Base.
 * 本导出类的set方法与非泛型代码
 * @see C23OrdinaryArgument 一样，都是对基类方法的
 * 重载而非重写。
 */
class SubGeneicBasic extends GenericBasic<Base>{
    public void set(Derived derived){
        System.out.println("SubGeneicBasic.set()");
    }
}
