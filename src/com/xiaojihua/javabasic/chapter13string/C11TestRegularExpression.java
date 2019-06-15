package com.xiaojihua.javabasic.chapter13string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、使用正则表达式类
 * 2、首先通过Pattern.compaile(regex)静态方法编译正则表达式得到Pattern对象
 *    然后通过Pattern对象的matcher(待检索的字符串)方法得到一个Matcher对象
 *    Matcher对象有很多方法来操作带检索字符串中的匹配字符串。
 *
 * 本例通过接受命令行参数来运行。命令行必须有2个以上的参数，第一个作为待检索
 * 的字符串，剩下的全部当作正则表达式。
 */
public class C11TestRegularExpression {
    public static void main(String[] args){
        if(args.length < 2){
            System.out.println("please input the right: java C11T String regexs+");
            System.exit(0);
        }

        System.out.println("Input:" + args[0]);

        //避免在循环中使用+链接字符串，这里显式使用StringBuilder
        StringBuilder outStr = new StringBuilder();
        for(String regex : args){
            System.out.println("Regular expression:" + regex);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(args[0]);
            System.out.println(matcher.matches());//只有正则表达式和被匹配的字符串完全一样才为true
            System.out.println(matcher.lookingAt());//当正则表达式与被匹配的字符串开始部分(不一定是全部)一样时为true
            /**
             * Matcher对象的具体用法（部分）
             */
            while(matcher.find()){
                outStr.setLength(0);//清空
                outStr.append("Match \"")
                        .append(matcher.group())
                        .append("\" at position ")
                        .append(matcher.start())
                        .append("-")
                        .append(matcher.end() - 1);
                System.out.println(outStr.toString());
            }
        }
    }
}
