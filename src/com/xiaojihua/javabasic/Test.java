package com.xiaojihua.javabasic;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{

    public static void main(String[] args) throws Exception{

        String name  = "I am 君山";
        byte[] bytes = name.getBytes("utf16");
        String newName = new String(bytes,"utf16");
        System.out.println(newName);

        /*for(byte c : name.getBytes("utf-8")){
            System.out.print(String.format("%02X ", c));
        }
        System.out.println();
        char[] chars = name.toCharArray();
        ByteBuffer byteBuffer = ByteBuffer.allocate(chars.length*2);
        for(char c : chars){
          byteBuffer.putChar(c);
        }
        for(byte b : byteBuffer.array()){
            System.out.print(String.format("%02X ", b));
        }*/

    }



}



