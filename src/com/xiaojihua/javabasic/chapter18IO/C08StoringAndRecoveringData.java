package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

/**
 * 知识点：典型的IO操作之基本数据类型的存储和恢复
 *
 * PrintWriter和PrintStream是用来输出人类可读的样式，DataOutputStream，是用来存储基本数据类型
 * 并且在将来的某个时刻通过DataInputStream来恢复所存储的基本类型。
 * 前者是面向可读输出，后者是面向存储的输出。
 *
 * 另外使用DataInputStream读取由DataOutputStream输出的文件的时候要明确的知道要读取的数据的准确类型
 * 否则会读取错误。比如第27行本来应该读取UTF却读取了Double.
 *
 * writeUTF用来以UTF-8的编码方式输出java字符串，只有通过readUTF才能正确读取。
 */
public class C08StoringAndRecoveringData {
    private static String file = "G:\\xjhdevelopment\\data.out";
    public static void main(String[] args) throws IOException{
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        out.writeDouble(3.1415926);
        out.writeUTF("this is PI.");
        out.writeDouble(1.23456);
        out.writeUTF("this is double.");
        out.close();

        DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        System.out.println(input.readDouble());
        //System.out.println(input.readDouble());
        System.out.println(input.readUTF());
        System.out.println(input.readDouble());
        System.out.println(input.readUTF());
    }
}
