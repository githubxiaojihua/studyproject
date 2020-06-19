package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 使用Zip，压缩多个文件。
 * ZIP文件支持设置密码，但是java的ZIP libray不支持。
 */
public class C30ZipCompress {
    public static void main(String[] args) throws IOException {
        String zipFile = "H:\\text.zip";

        /*
            创建一个基于多文件的ZIP压缩文件，
            FileOutputStream -> CheckedOutputStream -> ZipOutputStream - > BufferedOutputStream
            其中跳过ChecedOutputStream，直接使用FileOutputStream来创建ZipOutputStream
            也可以，因为ZipOutputStream的构造参数为OutputStream。
            使用CheckedOutputStream的作用是生成Checksum（校验和），关于校验和的解释：
            A checksum is the outcome of running an algorithm, called a
            cryptographic hash function, on a piece of data, usually a
            single file. Comparing the checksum that you generate from
            your version of the file, with the one provided by the
            source of the file, helps ensure that your copy of the
            file is genuine and error free.

            CheckedOutputStream和CheckInputStream支持 Adler32和CRC32两种Checksum
            算法，其中Adler32相对快一点。
         */
        FileOutputStream output = new FileOutputStream(zipFile);
        CheckedOutputStream cos = new CheckedOutputStream(output,new Adler32());
        ZipOutputStream zipOut = new ZipOutputStream(cos);
        BufferedOutputStream buffedOut = new BufferedOutputStream(zipOut);
        //可以setComment但是没有getComment方法
        zipOut.setComment("A test of java testing");

        for(String file : args){
            print("write file:" + file);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            //添加待压缩文件，准备开始压缩文件，并将stream定位到相应entry的开始位置
            zipOut.putNextEntry(new ZipEntry(file));
            //避免乱码。reader.readLine使用系统默认的编码方式从字节转成字符，因为在new reader的时候
            //使用的是FileReader默认使用的就是系统编码方式。
            //然后使用s.getBytes将字符转成字节写出，达到了编码和解码的统一。
            //这里原来用的是reader.read()返回的是int类型，然后buffedOut.write进行写入，这样会造成
            //乱码。
            String s;
            while((s = reader.readLine()) != null){
                buffedOut.write(s.getBytes());
            }
            reader.close();//别忘记
            buffedOut.flush();//buffered一般都要flush.
        }
        buffedOut.close();//别忘记 ，只需要关闭最外层的就行
        //获取CheckedOutputStream 的 checksum
        print("checksum:" + cos.getChecksum().getValue());

        //读取ZIP文件
        /*
            FileInputStream -> CheckedInputStream -> ZipInputStream -> BufferedReader
            同理CheckedInputStream可以不用，直接使用FileInputStream来创建
            ZipInputStream。
            使用CheckedInputStream的作用是与CheckedOutputStream的checksum进行
            对比来确保文件的读取正确性。
         */
        FileInputStream input = new FileInputStream(zipFile);
        CheckedInputStream cis = new CheckedInputStream(input,new Adler32());
        ZipInputStream zipInput = new ZipInputStream(cis);
        BufferedReader reader = new BufferedReader(new InputStreamReader(zipInput));
        ZipEntry entry;
        /*
            读取zip文件的时候使用getNextEntry方法准备开始读取zip文件，并将
            stream定位到文件的起始位置。
         */
        while((entry = zipInput.getNextEntry()) != null){
            print("read file:" + entry);
            int i;
            /*
                BufferedReader.read是用0-65536范围的int来读取一个char.
                书中使用了BufferedInputStream.read，这个方法是用0-255范围
                的int来读取一个char.
                也就是说两者的区别在于一个用两个字节来存储一个char，另一个用
                一个字节来存储一个char
             */
            while((i = reader.read()) != -1){
                //输出是数字
                printnb(i);
            }
            print();
        }
        print("checksum:" + cis.getChecksum().getValue());
        reader.close();//只需要关闭最外层的就行

        /*
            另外一种读取ZIP压缩文件的较简单的方式是通过ZipFile来实现。
            ZipEntry不支持Adler32算法
         */
        ZipFile zf = new ZipFile(zipFile);
        Enumeration entries = zf.entries();
        while(entries.hasMoreElements()){
            ZipEntry entry1 = (ZipEntry) entries.nextElement();
            print("readfile throgh ZipFile:");
            /*
                通过ZipFile读取文件内容。中文会有乱码，原因与
                C29文件的44行一样
             */
            BufferedReader reader1 = new BufferedReader(
                    new InputStreamReader(zf.getInputStream(entry1)));
            String s;
            while((s = reader1.readLine()) != null){
                print(s);
            }
            reader1.close();

        }

    }
}
