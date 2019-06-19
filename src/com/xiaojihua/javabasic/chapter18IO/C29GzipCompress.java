package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * 知识点：
 * 使用GZIPOutputStream和GZIPInputStream来用GZIP格式压缩单个文件。
 * 当只有一个文件需要进行压缩的时候可以用Gzip。
 * GZIP相关的类只能压缩一个文件
 */
public class C29GzipCompress {
    public static void main(String[] args) throws IOException {
        /*if(args.length == 0){
            System.out.println("Usage C29GzipCompress file\n" +
                    "to compress one file.");
            System.exit(1);
        }*/

        /*
            通过reader来读取文件内容
         */
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("H:\\out.txt")));
        /*
            通过outputstream来输出内容到test.gz。
            GZIPOutputStream是面向字节的，接受的参数是字节流
            test.gz如果不存在的话会自动创建。文件可以不存在，将自动创建，
            但是如果路径不存在的话是不能自动创建的，需要File.mkdirs();
         */
        BufferedOutputStream output = new BufferedOutputStream(
                new GZIPOutputStream(new FileOutputStream("H:\\test.gz")));

        //写入数据，由于有汉字因此使用如下写法，与书中写的不一样，书中写的是51-54行
        String s;
        while( (s = reader.readLine()) != null){
            s += "\n";
            output.write(s.getBytes());
        }

        /*
            通过以下语句写入的时候如果文件中有汉字，那么会出现乱码，主要原因是：
            H:\\out2.txt是用系统默认的字符编码来实现写的，这一点可以从TextFile.java
            文件中看出来。下面的语句reader.read()方法api中的说明是：
            The character read, as an integer in the range 0 to 65535 (0x00-0xffff),
            or -1 if the end of the stream has been reached。
            也就是说用0到65535（转成二进制是 11111111 11111111)来读取一个字符。
            现在系统的默认编码是utf-8，是一个多字节编码方式（也就是说一个字符可能被
            编为一个字节也有可能被编为两个字节。
            因此如果都作为两个字节来读取并写入的话，汉字是肯定有乱码的。
            但是用上面的4条语句就没有问题，因为作到了，编码和解码一致。
         */
        /*int i;
        while((i = reader.read()) != -1){
            output.write(i);
        }*/


        reader.close();//别忘记
        output.close();//别忘记

        //使用GZIPInputStream来读取GZIP格式的数据，并输出
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(new FileInputStream("H:\\test.gz"))));
        String s1;
        while((s1 = reader2.readLine()) != null){
            System.out.println(s1);
        }
        reader2.close();//别忘记
    }
}
