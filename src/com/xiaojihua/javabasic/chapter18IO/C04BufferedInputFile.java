package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

public class C04BufferedInputFile {
    public static String read(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"GBK"));
        StringBuilder sb = new StringBuilder();
        String str;
        while((str = reader.readLine()) != null){
            sb.append(str + "\n");
        }
        reader.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException{
        System.out.println(read("H:\\肖吉华\\01工作任务\\博兴\\正式文件\\33.txt"));
    }
}
