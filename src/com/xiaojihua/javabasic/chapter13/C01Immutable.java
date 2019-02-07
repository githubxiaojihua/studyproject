package com.xiaojihua.javabasic.chapter13;

/**
 * 知识点：
 * 1、String对象是不可变的，所有String类的相关修改方法都是
 * 返回一个包含修改后信息的全新String对象，原对象保持不变
 */
public class C01Immutable {
    public static String upcase(String s){
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String q = "howdy";
        System.out.println(q);
        //qq是包含了修改信息的全新对象，原对象保持不变
        //当q传递给upcase时候，实际上传递的是一个q的copy。
        //原来的q仍然存在并且指向了对象的物理地址
        //当方法执行完成以后返回了一个引用，这个引用指向了一个
        //新的对象，这个对象包含了原来对象的修改信息。
        String qq = upcase(q);
        System.out.println(qq);
        System.out.println(q);
    }
}
