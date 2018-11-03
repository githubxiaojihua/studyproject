package com.xiaojihua.javabasic.java8test;

import java.util.Arrays;

public class Java8Test {
    public static void main(String[] args) {
        String seprator = ",";
        Arrays.asList("a","b","c","d").forEach( (String e) -> System.out.println(e + seprator));
    }
}
