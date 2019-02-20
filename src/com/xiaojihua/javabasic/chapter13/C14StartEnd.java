package com.xiaojihua.javabasic.chapter13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知识点：
 * 1、start,end的使用，start是匹配的group的部分在总的字符串中的起始位置，
 * end代表结束位置+1，索引从0开始
 * 2、matches和lookingAt的使用，无需
 */
public class C14StartEnd {
    public static final String input = "As long as there is injustice, whenever a\n" +
            "Targathian baby cries out, wherever a distress\n" +
            "signal sounds among the stars ... We’ll be there.\n" +
            "This fine ship, and this fine crew ...\n" +
            "Never give up! Never surrender!";

    private static class Display{
        private boolean regexPrint = false;
        private String regex;
        Display(String reg){
            regex = reg;
        }
        public void display(String message){
            if(!regexPrint){
                System.out.println("regex:" + regex);
                regexPrint = true;
            }
            System.out.println(message);
        }
    }

    public static void  exime(String input, String regex){
        Display display = new Display(regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()){
            display.display("group:" + matcher.group() + ",start:" + matcher.start() + ",end:" + matcher.end());
        }

        //虽然上面经过了很多的find()，但是不影响matches和lookingAt的使用，无需重置matcher对象。
        if(matcher.matches()){
           display. display("matches start:" + matcher.start() + ",end:" + matcher.end());
        }

        if(matcher.lookingAt()){
            display. display("lookingAt start:" + matcher.start() + ",end:" + matcher.end());
        }
    }

    public static void main(String[] args){
        for(String input : input.split("\n")){
            System.out.println("input:" + input);
            for(String regex : new String[]{"\\w*ere\\w*", "\\w*ever", "T\\w+", "Never.*?!"}){
                exime(input, regex);
            }
        }
    }
}
