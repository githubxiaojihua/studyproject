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

       File file = new File("Test.java");
       System.out.println(file.getPath());
       System.out.println(file.getAbsolutePath());
       System.out.println(file.getCanonicalPath());
       System.out.println(file.getParent());

    }



}



