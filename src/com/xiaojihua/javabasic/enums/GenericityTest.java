package com.xiaojihua.javabasic.enums;

/**
 * 泛型方法：
 * 1、泛型方法可以存在与泛型类中，也可以存在非泛型类中
 * 2、如果static方法需要具备泛型能力，就必须使其成为泛型方法。
 *
 * 泛型方法原则：
 * 无论何时只要你能做到，就应该尽量使用泛型方法。也就是说如果使用泛型方法可以取代整个类的泛型化，那么就应该使用泛型方法
 */
public class GenericityTest {

    public <T> void f(T t){
        System.out.println(t.getClass().getName());
    }

    public static void main(String[] args) {
        GenericityTest gt = new GenericityTest();
        gt.f("");
        gt.f(1);
        gt.f(1.0);
        gt.f('d');
    }
}
