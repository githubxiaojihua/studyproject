package com.xiaojihua.javabasic.chapter18IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 知识点：
 * 标准IO：System.out，System.in，System.err为标准IO。
 * System.out是标准的输出IO流，已经经过PrintStream进行了装饰。
 * System.err是标准的错误输出IO流，也经过了PrintStream进行了装饰。
 * System.in是标准的输入IO流，未经过任何装饰，因此在使用的时候需要装饰
 * 比如本例中，通过InputStreamReader来适配成Reader,然后通过
 * BufferedReader来装饰，因此可以使用readLine
 */
public class C11Echo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = reader.readLine()) != null && !s.equals("")){
            System.out.println(s);
        }
    }
}
