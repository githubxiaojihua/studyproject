package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

/**
 * 知识点：常用的IO使用之buffered file
 */
public class C04BufferedInputFile {
    public static String read(String fileName) throws IOException {
        /*
            通过在FileReader外包装一层BufferedReader来使用。
            因为使用到readLine因此选择BufferedReader来使用。
            关于BufferedReader和FileReader的关系可以参考笔记。

            使用FileReader是不需要指定charset的，使用系统默认的charset
            默认为System的file.encoding属性指定的。
            FileReader继承自InputStreamReader，而InputStreamReader
            是字节转成字符的桥梁，转换的时候需要指定编码方式，没有指定的话
            就选择系统默认的。

         */
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String str;
        while((str = reader.readLine()) != null){
            sb.append(str + "\n");
        }
        reader.close();

        return sb.toString();
    }

    public static void main(String[] args) throws IOException{
        System.out.println(System.getProperties());
        System.out.println(read("I:\\222.txt"));
    }
}
