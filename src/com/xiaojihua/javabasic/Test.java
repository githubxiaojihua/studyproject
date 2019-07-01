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

public class Test implements A<String>{

    public static void main(String[] args) throws Exception{
        for(Type t : Test.class.getGenericInterfaces()){
            System.out.println(t);
        }

       System.out.println(Test.class.getGenericSuperclass());

        for(Class<?> c : Test.class.getInterfaces()){
            System.out.println(c.getName());
        }
    }

    public String ab(){
        return "ddd";
    }


}

interface A <B>{
    B ab();
}



