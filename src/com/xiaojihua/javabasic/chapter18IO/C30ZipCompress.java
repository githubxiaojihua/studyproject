package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class C30ZipCompress {
    public static void main(String[] args) throws IOException {
        FileOutputStream output = new FileOutputStream("H:\\text.zip");
        CheckedOutputStream cos = new CheckedOutputStream(output,new Adler32());
        ZipOutputStream zipOut = new ZipOutputStream(cos);
        BufferedOutputStream buffedOut = new BufferedOutputStream(zipOut);

        for(String file : args){
            BufferedReader reader = new BufferedReader(new FileReader(file));
            zipOut.putNextEntry(new ZipEntry(file));
            int i;
            while((i = reader.read()) != -1){
                buffedOut.write(i);
            }
            reader.close();
            buffedOut.flush();
        }
        buffedOut.close();
    }
}
