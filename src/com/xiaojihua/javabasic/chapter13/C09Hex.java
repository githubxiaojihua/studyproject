package com.xiaojihua.javabasic.chapter13;

import com.xiaojihua.javabasic.util.C04BinaryFile;

import java.io.IOException;

/**
 * 知识点：
 * 1、将二进制转换为十六进制
 * 2、format格式化字符width前面加0代表用0填充不足的位数。
 * 3、使用了一个读取二进制文件到byte数组的工具类
 */
public class C09Hex {
    public static String format(byte[] data){
        StringBuilder formatedStr = new StringBuilder();
        int n = 0;
        for(byte bData : data){
            if(n % 16 == 0){
                //width=5在5前面加0代表用0填充不足的位数
                formatedStr.append(String.format("%05X: ", n));
            }
            formatedStr.append(String.format("%02X ", bData));
            n++;
            if(n % 16 == 0){
                formatedStr.append("\n");
            }
        }
        return formatedStr.toString();
    }

    public static void main(String[] args){
        byte[] data = null;
        try {
            //这里用两个反斜线和四个反斜线都可以
            data = C04BinaryFile.read("H:\\code_80968\\\\studyproject\\\\out\\\\production\\\\studyproject\\\\com\\\\xiaojihua\\\\javabasic\\\\chapter13\\\\C09Hex.class");
            System.out.println(format(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
