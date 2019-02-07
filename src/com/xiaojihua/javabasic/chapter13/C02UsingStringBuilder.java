package com.xiaojihua.javabasic.chapter13;

import java.util.Random;

/**
 * 知识点：
 * 1、StringBuilder的使用
 * 2、java中的+和+=操作符被重载过，应用于String类，底层java编译器会自动
 * 使用StringBuilder来优化。但是应该避免在循环中使用+因为，每次循环都会
 * new新的StringBuilder效率较低，应该显示的使用StringBuilder。而且在使用
 * StringBuilder的append方法的时候也应该避免+(比如append("a" + "c"))，这样编译器还是会自动引入
 * 新的StringBuilder对象。
 * 3、StringBuilder是javase5后引入的，之前一直使用StringBuffer。
 * StringBuffer是线程安全的因此开销大速度慢，StringBuilder是非线程安全的
 * 速度快。
 */
public class C02UsingStringBuilder {
    public static Random random = new Random(47);
    public String toString(){
        StringBuilder resule = new StringBuilder();
        resule.append("[");
        for(int i=0; i < 25; i++){
          resule.append(random.nextInt(100));
          resule.append(",");
        }
        resule.delete(resule.length() - 2, resule.length());
        resule.append("]");
        return resule.toString();
    }

    public static void main(String[] args) {
        C02UsingStringBuilder usb = new C02UsingStringBuilder();
        System.out.println(usb);
    }
}
