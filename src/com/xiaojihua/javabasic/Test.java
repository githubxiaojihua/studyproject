package com.xiaojihua.javabasic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{

    public static void main(String[] args) throws Exception{
        String str = "I am 晓峰";
        byte[] bytes = str.getBytes("iso8859-1");
        String str1 = new String(bytes);
        System.out.println(str1);
    }



}



