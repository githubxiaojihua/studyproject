package com.xiaojihua.javabasic.chapter14;

/**
 * 知识点：
 * 1、instanceof的用法。instanceof是作为一个表达式符号来使用的。
 * 2、isInstance的用法，这个方法是Class类的一个方法。
 * 3、instanceof和isInstance方法都是可以识别继承关系的
 * 4、但是class之间的==和equals是不会考虑继承关系的，是同一个类就为true，
 * 不是那就为false
 */
public class C08FamilyAndExactType {
    public static void test(Object t){
        String name = t.getClass().getSimpleName();
        System.out.println("Test:" + name);
        System.out.println(name + " instanceof Base:" + (t instanceof Base));
        System.out.println(name + " instanceof Divade:" + (t instanceof Divade));
        System.out.println(name + " isInstanceof Base:" + Base.class.isInstance(t));
        System.out.println(name + " isInstanceof Divade:" + Divade.class.isInstance(t));
        System.out.println(name + " == Base.class:" + (t.getClass() == Base.class));
        System.out.println(name + " == Divade.class:" + (t.getClass() == Divade.class));
        System.out.println(name + " equals Base.class:" + (t.getClass().equals(Base.class)));
        System.out.println(name + " equals Devade.class:" + (t.getClass().equals(Divade.class)));
        System.out.println("=====================================");
    }

    public static void main(String[] args){
        test(new Base());
        test(new Divade());
    }
}

class Base{}
class Divade extends Base{}