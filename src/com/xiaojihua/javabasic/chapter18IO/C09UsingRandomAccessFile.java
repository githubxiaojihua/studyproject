package com.xiaojihua.javabasic.chapter18IO;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 知识点：典型的IO操作之使用RandomAccessFile
 *
 * 使用RandomAccessFile相当于组合使用了DataInputStream和DataOutputStream。
 * RandomAccessFile可以随机定位文件位置并且进行读取，但是前提是知道文件
 * 的数据存储结构
 */
public class C09UsingRandomAccessFile {
    private static String file = "G:\\xjhdevelopment\\abc.dat";

    public static void display() throws IOException{
        RandomAccessFile randomAccess = new RandomAccessFile(file,"r");//用只读的模式打开
        for(int i=0; i<7; i++){
            System.out.println(randomAccess.readDouble());
        }
        System.out.println(randomAccess.readUTF());
        randomAccess.close();//别忘记
    }

    public static void main(String[] args) throws IOException{
        RandomAccessFile randomAccess = new RandomAccessFile(file,"rw");//用读写的模式打开，没有单独用写的模式打开
        for(int i=0; i<7; i++){
            randomAccess.writeDouble(i);
        }
        randomAccess.writeUTF("THIS IS GOOD");
        randomAccess.close();//别忘记
        display();

        randomAccess = new RandomAccessFile(file,"rw");
        //每个double为8个直接64位，因此如果想修改第6个double的值应该定位的5*8的字节位置
        randomAccess.seek(5*8);
        randomAccess.writeDouble(3.221);
        randomAccess.close();//别忘记
        display();


    }
}
