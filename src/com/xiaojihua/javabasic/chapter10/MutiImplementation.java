package com.xiaojihua.javabasic.chapter10;

/**
 * 知识点：通过匿名内部类的方式实现多继承自类或者抽象类。
 *
 */
class D{}
abstract class E{}

class Z extends D{
    E makeE(){
        return new E(){};
    }
}
public class MutiImplementation {
    static void takesD(D d){}
    static void takesE(E e){}

    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());
    }


}
