package com.xiaojihua.javabasic.chapter13string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、替换方法的使用，包括：replaceAll,replaceFirst，Matcher.appendReplacement
 * Matcher.appendTail等
 */
public class C17TheReplacemants {
    public static void main(String[] args){
        String input = "/*! Here’s a block of text to use as input to \n" +
                "    the regular expression matcher. Note that we’ll \n" +
                "    first extract the block of text by looking for \n" +
                "    the special delimiters, then process the \n" +
                "    extracted block. !*/";
        /**
         * 匹配了上面的整个input，因为有换行，所以，使用Pattern.DOTALL标志，
         * 使用此标识 . 就能匹配所有字符包括行终结符。默认情况下 . 是不匹配
         * 行终结符的。
         */
        Matcher matcher = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL).matcher(input);
        if(matcher.find()){
            //group(1)实际上取得就是两个叹号中间的所有字符
            input = matcher.group(1);
        }
        input = input.replaceAll(" {2,}", " ");//将大于等于2的空格换成一个空格
        /**
         * (?m)等同于Pattern.MUTILINE，是后者的行内模式
         * 将每行开始的一个或者多个空格去掉
         */
        input = input.replaceAll("(?m)^ +","");
        System.out.println(input);
        input = input.replaceFirst("[aeiou]","(VOWEL1)");
        //上面代码中的replaceAll,replaceFirst因为都是一次行的因此没有必要使用Matcher.
        Matcher m = Pattern.compile("[aeiou]").matcher(input);
        StringBuffer buf = new StringBuffer();
        while(m.find()){
            /**
             * Matcher的appendReplacement方法可以一步步的将find到的部分
             * 按照指定的规则进行替换，并且追加到buf中，他比replaceALL那种
             * 一次性的替换更佳灵活，可以灵活的处理每一个匹配到的待替换项
             * 最终buf中的内容与原始的input基本一致，出了将符合替换规则的
             * 部分进行了替换
             */
            m.appendReplacement(buf, m.group().toUpperCase());
        }
        /**
         * Matcher的appendTail方法是将使用appendReplacement方法后
         * 剩余的部分增加到buf中的方法，可以尝试注销掉，并观察结果。
         * 注意观察最后
         */
        m.appendTail(buf);
        System.out.println(buf);
    }
}
