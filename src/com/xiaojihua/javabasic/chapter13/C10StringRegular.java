package com.xiaojihua.javabasic.chapter13;

import java.util.Arrays;

/**
 * 知识点：正则表达式
 * 1、String类内置的正则表达式应用
 * 2、相关量词 ？代表0或者1次。+代表1或者多次。
 * 3、相关字符类\\d代表数字。\\W代表非单词。\\w代表单词语
 * 4、量词的三种使用方法。贪婪、勉强、占有。
 */
public class C10StringRegular {
    private static String knights = "Then, when you have found the shrubbery, you mast " +
            "cut down the mightiest tree in the forest... " +
            "with... a herring!";
    public static void split(String regex){
        System.out.println(Arrays.toString(knights.split(regex)));
    }
    public static void main(String[] args){
        /**
         * 通过matches检查字符串是否匹配某正则表达式
         */
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        System.out.println("+911".matches("(-|\\+)?\\d+"));

        /**
         * String内置的split方法可以按照正则表达式类拆分字符串，符合
         * 正则表达式的部分都被替换掉了
         */
        split(" ");
        split("\\W+");
        split("n\\W+");

        /**
         * 其他常用的正则表达式
         */
        for(String regex : new String[]{"Rudolph", "[rR]udolph", "[rR][aeiou][a-z]ol.*", "R.*"}){
            System.out.println("Rudolph".matches(regex));
        }

        /**
         * 区分这三种模式的方法：
         * 在量词后面再跟量词，默认为贪婪，跟？表示懒惰，跟+表示占有
         * 重点在于区分字符类和量词。字符类是类似于. \\w \\W \\d [abc]等，
         * 而量词：？ + * {n} {n,} {n,m}
         */
        String qua = "a<tr>aava </tr>abb ";
        String regex = "<.+>";//贪婪匹配（最大匹配）
        String regexRe = "<.+?>";//勉强匹配或者懒惰匹配（最小匹配）
        String regexPo = "<.++>";//占有匹配（完全匹配）,换成 .++就匹配成功
        System.out.println(qua.replaceAll(regex,"*"));
        System.out.println(qua.replaceAll(regexRe,"*"));
        System.out.println(qua.replaceAll(regexPo,"*"));
    }
}
