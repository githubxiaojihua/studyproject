package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

/**
 * 知识点：
 * 标准IO，重定向标准IO流
 * 因为标准IO流都是基于字节流的，因此需要操作InputStream、OutputStream
 */
public class C13Redirecting {
    public static void main(String[] args) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("" +
                "H:\\code_80968\\studyproject\\src\\com\\xiaojihua\\javabasic\\chapter18IO\\C13Redirecting.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("H:\\text.out")));
        PrintStream oldOut = System.out;

        System.setIn(in);
        System.setOut(out);
        System.setErr(out);

        String s;

        //将System.in适配转换成了BufferedReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while((s = reader.readLine()) != null){
            System.out.println(s);
        }

        out.close();
        //记得还原
        System.setOut(oldOut);


    }
}
