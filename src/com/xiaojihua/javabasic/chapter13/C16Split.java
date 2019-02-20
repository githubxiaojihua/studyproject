package com.xiaojihua.javabasic.chapter13;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、Pattern.split的用法。可以方便的将input字符串根据规则分割成
 * String数组。有两个重载方法：
 * String[] split(CharSequence input)
 * String[] split(CharSequence input, int limit)
 */
public class C16Split {
    public static void main(String[] args){
        String input = "This!!unusual use!!of exclamation!!points";
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
        //指定分割后的数组长度
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input,3)));
    }
}
