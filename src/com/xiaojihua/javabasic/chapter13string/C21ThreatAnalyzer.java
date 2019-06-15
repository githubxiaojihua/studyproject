package com.xiaojihua.javabasic.chapter13string;

import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * 知识点：
 * 1、使用Scanner配合正则表达式，来扫描文本中的特定内容，并遍历使用
 */
public class C21ThreatAnalyzer {
    public static String threatData = "58.27.82.161@02/10/2005\n" +
            "204.45.234.40@02/11/2005\n" +
            "58.27.82.161@02/11/2005\n" +
            "58.27.82.161@02/12/2005\n" +
            "58.27.82.161@02/12/2005\n" +
            "[Next log section with different data format]";
    public static void main(String[] args){
        Scanner scanner = new Scanner(threatData);
        //正则表达式匹配ip@日期
        String pattern = "(\\d+[.]\\d+[.]\\d+[.]+\\d+)@(\\d{2}/\\d{2}/\\d{4})";
        /**
         * 功能类似Pattern相关类的使用，但是也有区别
         */
        while(scanner.hasNext(pattern)){
            //必须使用next(pattern)来匹配下一个string匹配项
            scanner.next(pattern);
            //然后通过使用match()方法将这个匹配项变为可用
            MatchResult result = scanner.match();
            //然后再使用所匹配的结果
            String ip = result.group(1);
            String date = result.group(2);
            System.out.format("Threat on %s from %s\n", date, ip);
        }
    }
}
