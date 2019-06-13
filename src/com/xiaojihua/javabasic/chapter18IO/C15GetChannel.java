package com.xiaojihua.javabasic.chapter18IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 知识点：NIO
 * nio采用了更接近与操作系统底层的方式来提高速度，主要通过FileChannel和ByteBuffer来
 * 操作，ByteBuffer是面向byte.
 * FileInputStream\FileOutputStream\RandomAccessFile都提供了获取FileChannel
 * 的方法，
 * Nio是面向字节的，Reader\Writer没有提供相关的Channel方法
 * 写代码要多注重代码的可重用性，向现在这些FileChannel、File、ArrayList等等
 * 重用性都很高，基本每个类都会使用他们。多多阅读源码，看看这些类是怎么写的，进行学习。
 * <B>
 * 在读写文件的时候，在一次写的时候如果多次写入那么是往后追加的，
 * 如果重新打开文件，再一次写入那么会从头开始写入，会覆盖原有的内容。
 * </B>
 */
public class C15GetChannel {
    private static final int SIZE = 1024;//k

    public static void main(String[] args) throws Exception{
        //通过FileChannel进行写入
        FileChannel channel = new FileOutputStream("H:\\data.txt").getChannel();
        channel.write(ByteBuffer.wrap("some text ".getBytes()));//初始化ByteBuffer方案之一，底层依赖"some text ".getBytes()
        channel.close();//别忘记

        //通过FileChannel配合RandomAccessFile进行读写
        channel = new RandomAccessFile("H:\\data.txt","rw").getChannel();
        channel.position(channel.size());//跳到文件的最后
        channel.write(ByteBuffer.wrap("some more".getBytes()));//追加
        channel.close();//别忘记

        //通过FileChannel配合FileInputStream进行只读操作，只读操作一般不需要close
        channel = new FileInputStream("H:\\data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);//初始化ByteBuffer方法之二
        channel.read(buffer);
        buffer.flip();//当从ByteBuffer中提取数据之前一定要flip
        while(buffer.hasRemaining()){
            //获取byte转换成char
            System.out.println((char) buffer.get());
        }
    }
}
