package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

/**
 * 知识点：常用的IO使用之formated memeory input(格式化内存输入)
 * 意思是：本例以内存中的String为基础，使用DataInputStream进行格式化读取。
 * 所谓的格式化读取（formated）指的是采用DataInputStream的readByte\readchar...等
 * 方法来读取。
 * DataInputStream是专门用于读取基础数据（byte,shot,int,long,string等）通常配合
 * DataOutputStream来使用
 */
public class C06FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        try{
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(
                    C04BufferedInputFile.read("H:\\code_80968\\studyproject\\src\\com\\xiaojihua\\javabasic\\chapter18IO\\C01DirList.java").getBytes()
            ));
            /*
                只能通过捕获EOFException异常来判断是否到达了文章的末尾。
             */
            while(true){
                /*
                    readByte是按照字节读取，然后编码成char（使用utf-8进行编码，这是系统
                    默认的，因为没有指定所以默认，可以从System的properties中的file.encoding属性得知，代表
                    uinicode字符集），这时候如果内容是英文的那么能够正常的输出出来，如果是
                    汉字那么就无法正常输出，因为此内存中的String是通过String.getBytes，根据
                    utf-8（默认的编码方式）换算成byte的，一个汉字有两个字节来标识，如果只
                    读取一个字节来表示那么只能转换出一半来。
                    如果换成readChar()，由于utf-8对于英文是一个字节表示，但是读取了两个字节
                    进行转化会导致不仅英文无法显示，就连中文也无法显示。
                 */
                System.out.println((char) dis.readByte());
            }
        }catch(EOFException e){
            System.out.println("the end of stream");
        }

    }
}
