package com.xiaojihua.javabasic.chapter15generic;

/**
 * 知识点：
 * 1、在非泛型条件下的参数类型特性(无“协变参数类型”)。从输出可以看到
 * OrdinarySetter.set和DerivedSetter.set两个方法根据
 * 传入的参数类型不同，是可以分别调用的，这说明DerivedSetter.set
 * 并不是重写（overriding)父类的set而是重载（orverloading)。
 * 在非泛型代码中，方法的参数类型并不会随着子类的变化而变化，
 * 而是通过重载父类的方法来实现。比如说：调用DerivedSetter的set方法
 * 时候根据调用的参数不同会分别调用基类和导出类的方法，是两个方法，
 * 并不是一个方法并且参数依据参数而变化。
 * 2、实例化子类DerivedSetter的时候实际上已经实例化了父类
 * OrdinarySetter了，不然怎么会调用到父类的方法呢。
 *
 * 泛型下的“协变类型参数”相关对比参考：
 * @see C24SelfBoundAndCovariantArgument
 */
public class C23OrdinaryArgument {
    public static void main(String[] args){
        DerivedSetter ds = new DerivedSetter();
        ds.set(new Base());//调用的是父类的set方法
        ds.set(new Derived());//调用的是子类的set方法

    }
}

class OrdinarySetter{
    public void set(Base base){
        System.out.println("OrdinarySetter.set(Base)");
    }
}

class DerivedSetter extends OrdinarySetter{
    /**
     * 此方法并没有覆盖父类中的set，而是重载
     * 另外在实例化子类的时候父类也进行了实例化。
     *
     * @param derived
     */
    public void set(Derived derived){
        System.out.println("DerivedSetter.set(Derived)");
    }
}

