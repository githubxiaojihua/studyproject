package com.xiaojihua.javabasic.chapter13string;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Formatter;

/**
 * 知识点：
 * 1、Formatter类的使用，初始话的时候必须指明输出的地方。
 */
public class C06Formatter {
    private String name;
    private Formatter formatter;
    public C06Formatter(String name, Formatter formatter){
        this.name = name;
        this.formatter = formatter;
    }
    public void move(int x, int y){
        formatter.format("name:%s x:%d, y:%d",name ,x, y);
    }

    public static void main(String[] args) {
        PrintStream alias = System.out;
        File file = new File("d://myNumber.txt");
        C06Formatter formatter1 = new C06Formatter("NAME1",new Formatter(System.out));
        C06Formatter formatter2 = new C06Formatter("NAME2",new Formatter(alias));
        try {
            C06Formatter formatter3 = new C06Formatter("NAME3",new Formatter(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        formatter1.move(1,2);
        formatter2.move(3,5);

        //也可以输出到文件中
    }
}
