package com.xiaojihua.javabasic.chapter18IO;

import java.io.IOException;
import java.io.StringReader;

/**
 * 知识点：常用的IO使用之memory input
 * 使用StringReader从内存中读取每个char并输出
 */
public class C05MemoryInput {
    public static void main(String[] args) throws IOException {
        //利用C04BufferedInputFile将文件内容读取到内存，然后使用StringReader来读取
        StringReader sr = new StringReader(C04BufferedInputFile.read("H:\\code_80968\\studyproject\\src\\com\\xiaojihua\\javabasic\\chapter18IO\\C01DirList.java"));
        int c;
        //注意read返回的是int因此要进行强制类型转换成char再输出
        while((c = sr.read()) != -1){
            System.out.println((char)c);
        }
    }
}
