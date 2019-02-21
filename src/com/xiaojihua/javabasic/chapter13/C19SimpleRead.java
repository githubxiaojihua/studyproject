package com.xiaojihua.javabasic.chapter13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * 知识点：
 * 1、javaio的部分类的使用
 * 2、在字符串中""\n就代表了换行，不用使用\\n，而在正则表达式中需要用\\来
 * 使用相关的匹配
 */
public class C19SimpleRead {
    public static BufferedReader reader = new BufferedReader(new StringReader("Sir Robin of Camelot\n22 1.61803"));
    public static void main(String[] args){
        try {
            System.out.println("hi,what's your name?");
            String name = reader.readLine();
            System.out.println(name );
            System.out.println("How old are you? what is your faravours double?");
            String numbers = reader.readLine();
            System.out.println(numbers);
            String[] numberArr = numbers.split(" ");
            int age = Integer.parseInt(numberArr[0]);
            double favorateD = Double.parseDouble(numberArr[1]);
            System.out.format("my name is %s.\n", name);
            System.out.format("after 5 years I'm %d years old\n", age + 5);
            System.out.format("my farors double is %f.\n", favorateD/2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
