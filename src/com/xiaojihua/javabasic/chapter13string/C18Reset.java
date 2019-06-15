package com.xiaojihua.javabasic.chapter13string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、Matcher.reset（）方法的使用
 * 当使用reset有参数的方法的时候matcher可以重新应用于新的字符序列
 * 无参数的reset()方法，是将matcher对象设置为当前input序列的开始位置
 * 重新开始匹配
 */
public class C18Reset {
    public static void main(String[] args){
        Matcher matcher = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while(matcher.find()){
            System.out.print(matcher.group() + " ");
        }
        System.out.println();
        //重新应用与新的字符序列
        matcher.reset("fix the rig with rags");
        while(matcher.find()){
            System.out.print(matcher.group() + " ");
        }
    }
}
