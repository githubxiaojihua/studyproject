package com.xiaojihua.javabasic;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{

    public static void main(String[] args) throws Exception{

            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
            System.out.println(sqlDate);
    }

    public void test(Father f1, Father f2){
        System.out.println(f1 + " " + f2);
    }

    public static Father randomGen(){
        Random random = new Random(47);
        switch(random.nextInt(2)){
            default:
            case 0: return new Son1();
            case 1: return new Son2();
        }
    }





}

class Father{}
class Son1 extends Father{}
class Son2 extends Father{}



