package com.xiaojihua.javabasic.chapter13string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、Pattern标志的使用，作为Matcher.compile()可以接收flag标志来对
 * 匹配规则进行调整。常用的规则包括：
 * Pattern.CASE_INSENSITIVE 不区分大小写
 * Pattern.MULTILINE 最小行匹配，而不是整个input
 * Pattern.COMMENTS忽略空格以及 #开头到结尾的注释行
 */
public class C15ReFlags {
    public static void main(String[] args){
        Pattern pattern = Pattern.compile("^java", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher("java has regex\nJava has regex\n" +
                "JAVA has pretty good regular expressions\n" +
                "Regular expressions are in Java");
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
