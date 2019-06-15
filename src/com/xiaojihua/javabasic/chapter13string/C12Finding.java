package com.xiaojihua.javabasic.chapter13string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、Matcher.find()方法的使用，
 * 第一：类似于iterator
 * 第二：有个重载的find(int)的方法，重置Matcher对象，然后从int参数指定的位置后开始匹配
 * 2、Matcher.group()方法的使用，
 * group是由括号分割的正则表达式，并且在匹配后可以通过调用group的number来调用，比如 A(B(C))D,
 * group(0)代表ABCD也就是整个正则表达式，group(1)代表BC，group(2)代表C。
 */
public class C12Finding {
    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("\\w+").matcher("Evening is full of the linnet's wings");
        while(matcher.find()){
            //每一次find后就会有相应的group数据
            System.out.print("group count:" + matcher.groupCount() + " ");
            System.out.print(matcher.group() + " ");
        }
        System.out.println();
        int i = 0;
        while(matcher.find(i)){
            //每次输入第一个匹配到的group，然后i++后，重置matcher对象，从指定的位置重新开始匹配
            System.out.print(matcher.group() + " ");
            i++;
        }

    }
}
