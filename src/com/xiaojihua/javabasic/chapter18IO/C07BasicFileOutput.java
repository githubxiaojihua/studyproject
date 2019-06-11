package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

/**
 * 知识点：典型的IO操作之基本输出
 *
 */
public class C07BasicFileOutput {
    //文件可以不存在，将自动创建
    private static  String outFile = "G:\\xjhdevelopment\\text.out";
    public static void main(String[] args) throws IOException{
        /*
            依次嵌套使用StringReader\BufferedReader
         */
        BufferedReader reader = new BufferedReader(new StringReader(C04BufferedInputFile.read("" +
                "G:\\study\\studyproject\\src\\com\\xiaojihua\\javabasic\\chapter18IO\\C07BasicFileOutput.java")));
        //嵌套使用FileWriter\BufferedWriter\PrintWriter来进行输出
        PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
        /*
            下面是PrintWriter的快捷使用方法，无需人工指定嵌套使用的decorater(装饰器)
            系统自动增加BufferedWriter
         */
        //PrintWriter print = new PrintWriter(outFile);
        int count = 0;//记录行号
        String s;
        while((s = reader.readLine()) != null){
            count++;
            //不使用LineNumberReader来记录行号，LineNumberReader一般不使用。
            print.println(count + ":" + s);
        }
        reader.close();
        print.close();//必须进行close否则buffedwriter中的缓存不会进行flush
        System.out.println(C04BufferedInputFile.read(outFile));
    }
}
