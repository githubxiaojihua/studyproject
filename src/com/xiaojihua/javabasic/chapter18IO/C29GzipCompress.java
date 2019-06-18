package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class C29GzipCompress {
    public static void main(String[] args) throws IOException {
        /*if(args.length == 0){
            System.out.println("Usage C29GzipCompress file\n" +
                    "to compress one file.");
            System.exit(1);
        }*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("H:\\out2.txt"),"GBK"));
        BufferedOutputStream output = new BufferedOutputStream(
                new GZIPOutputStream(new FileOutputStream("H:\\test.gz")));
        int i = 0;
        while( (i = reader.read()) != -1){
            output.write(i);
        }
        reader.close();
        output.close();

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(new FileInputStream("H:\\test.gz")),"gbk"));
        String s;
        while((s = reader2.readLine()) != null){
            System.out.println(s);
        }
        reader2.close();

    }
}
