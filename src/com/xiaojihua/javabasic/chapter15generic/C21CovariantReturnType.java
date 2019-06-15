package com.xiaojihua.javabasic.chapter15generic;

/**
 * 知识点：
 * 1、<b>协变返回类型</b>，指的是导出类的方法可以返回比其覆盖的基类的方法的返回值更精确的类型。
 * 比如：DerivedGetter中的get方法可以返回基类OrdinaryGetter中的get方法返回值Base
 * 类的导出类Derived，而DerivedGetter的get方法与OrdinaryGetter中的get方法的关系是覆盖的关系。
 * 2、本例使用的是接口，实际上普通的类也是可以的。
 * 3、协变返回类型在jdk5之前是不允许的
 * 本类与泛型无关，而是jdk5以后的一个特性。
 */
public class C21CovariantReturnType {
    public void test(DerivedGetter derivedGetter){
        Derived d1 = derivedGetter.get();
    }
}

class Base{}
class Derived extends Base{}
interface OrdinaryGetter{
    Base get();
}


interface DerivedGetter extends OrdinaryGetter{
    /**
     * 覆盖基类的get方法，
     * 返回值是基类get方法返回值的导出类。
     * 这就是协变返回类型
     * @return
     */
    Derived get();
}