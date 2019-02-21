package com.xiaojihua.javabasic.chapter14;

/**
 * 知识点：
 * 1、Class也支持泛型，通过使用泛型可以在编译期内限定引用的指向。
 * 2、Class引用指向了一个Class对象，此对象包含了目标类的相关类型信息
 * 以及可作用与此类的方法，包括：生成实例。
 */
public class C03GenericClassRef {
    public static void main(String[] args){
        Class intClass = int.class;//普通的Class引用
        Class<Integer> genericClass = int.class;//带泛型的Class引用
        genericClass = Integer.class;// 泛型的Class引用对于基础类型和其包装类型视为一致
        intClass = double.class;//将普通的Class引用换为其他引用无问题
        //genericClass = double.class;//泛型的Class引用则不能随便换成不对应的。
    }
}
