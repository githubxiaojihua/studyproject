package com.xiaojihua.javabasic.chapter13string;

public class C04StringOperations {
    public static void main(String[] args) {
        String s = "abcdefghigklmnopqrstuvwxyz";
        System.out.println(s.length());
        char c = s.charAt(1);
        System.out.println(c);
        char[] newChars = new char[s.length()];
        //指定开始和结束位置，将指定的char序列放到新的char数组中
        s.getChars(1,s.length(), newChars, 0);
        System.out.println(newChars);
        char[] newChars2 = s.toCharArray();
        System.out.println(newChars2);
        String s1 = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        System.out.println(s.equals(s1));
        System.out.println(s.equalsIgnoreCase(s1));
        //0相等，负数表示s比s1小的数值，同理正数
        System.out.println(s.compareTo(s1));//output:32
        System.out.println(s.contains("abc"));
        //判断s与给定的参数字符串是否相等
        System.out.println(s.contentEquals("abcdefghigklmnopqrstuvwxyz"));
        //判断s与给定的字符串 指定部分是否相等。toffset:s开始的位置,s1目标字符串,ooffset目标字符串开始的位置，len比对的长度
        System.out.println(s.regionMatches(0,s1,0,3));
        System.out.println(s.regionMatches(true,0,s1,0,3));
        System.out.println(s.startsWith("abc"));
        System.out.println(s.endsWith("xyz"));
        System.out.println(s.indexOf("b"));
        System.out.println(s.indexOf('b'));
        //从后遍历寻找指定字符所在的位置
        System.out.println(s.lastIndexOf('b'));
        System.out.println(s.substring(5));
        System.out.println(s.substring(5,20));
        System.out.println(s.subSequence(5,20));
        System.out.println(s.concat("aeiou"));
        System.out.println(s.replace("abc","cba"));
        System.out.println(s.toUpperCase());
        System.out.println(s.toLowerCase());
        System.out.println(s.trim());
        /**
         * intern是String类的native方法，主要用来维护String常量池
         * 字符串常量池初始为空当某字符串对象调用intern时候，如果常量
         * 池中存在此字符串（equals)，那么将直接返回此常量的引用，否则
         * 就将此字符串增加到字符串常量池中
         * 具体的intern方法使用可以参考笔记中的技术文章
         */
        System.out.println(s.intern());
        //新new的对象值为放入到字符串池中
        String stmp = new String("abcdefghigklmnopqrstuvwxyz");
        //s已经在字符串常量池中
        System.out.println(s == stmp);//false
        //stmp.intern()是返回一个引用，指向字符串常量池中与stmp的值相等的常量，是一个新的引用，这个要注意
        //并非改变了stmp引用，stmp应用是不变的
        System.out.println(s == stmp.intern());
        System.out.println(s == stmp);

    }
}
