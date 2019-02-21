package com.xiaojihua.javabasic.chapter13;

import java.util.Scanner;

/**
 * 知识点：
 * 1、Scanner类的使用，构造器可以接收File,InputStream,String,Readable(接口).
 * 2、提供了出了char之外所有的基本类型的next方法.比如nextInt，nextDouble
 * 3、next()不带任何参数的返回一个String类型
 * 4、有hasNext()方法，也重载了基本类型。判断下一个输入分词是否是相应的类型。
 * 5、Scanner并不会抛出IOException异常，他会内部消化掉。不过可以通过ioExcepion()方法
 * 获取到最近的一次异常抛出。
 * 6、Scanner默认的分隔符为空格，可以通过scanner.useDelimiter(正则表达式)，来指定。
 * 通过delimiter()方法可以返回当前的分隔符
 */
public class C20BetterReader {
    public static void main(String[] args){
        Scanner scanner = new Scanner(C19SimpleRead.reader);
        System.out.println("what is your name?");
        String name = scanner.nextLine();
        System.out.println("How old are you, what is your faverit double ?");
        System.out.println("input:<int> <double>");
        int age = scanner.nextInt();
        double d = scanner.nextDouble();
        System.out.println(age);
        System.out.println(d);
        System.out.format("my name is %s \n", name);
        System.out.format("after 5 years I am %d years old\n", age + 5);
        System.out.format("my favrious double is %f\n", d / 2);
        System.out.println(scanner.delimiter());
    }
}
