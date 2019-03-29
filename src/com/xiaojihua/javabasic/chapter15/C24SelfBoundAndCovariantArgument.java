package com.xiaojihua.javabasic.chapter15;

/**
 * 知识点：
 * 1、自限定类型的主要价值在于产生“协变参数类型”（方法的参数类型
 * 根据子类的类型而变化。也就是说父类中方法的参数类型根据最终确定
 * 的子类的变化而变化。对应本类就是SelfBoundedSetter的set方法
 * 的参数类型与最终T所代表的子类一直，而T也只能是SelfBounded的
 * 子类，这是由自限定类型的格式所决定的。
 * 2、与“协变参数类型”相对应的还有“协变返回类型”。具体可参考：
 * @see C21CovariantReturnType,C22GenericsAndReturnType
 */
public class C24SelfBoundAndCovariantArgument {
    public void test(Setter s1, Setter s2, SelfBoundedSetter<Setter> selfB){
        s1.set(s1);
        s1.set(s2);
        //不能传入基类，因为不满足自限定类型的格式限制。
        //s1.set(selfB);
    }
}

/**
 * 自限定类型接口。相当于导出类的模版，而T根据不同的子类来确定
 * @param <T>
 */
interface SelfBoundedSetter<T extends SelfBoundedSetter<T>>{
    void set(T t);
}

/**
 * 自限定类型的应用，这样继承而来的所有方法中的类型参数均为Setter
 */
interface Setter extends SelfBoundedSetter<Setter>{
    void set(Setter setter);
}

