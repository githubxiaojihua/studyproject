package com.xiaojihua.javabasic;

import java.io.*;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.xiaojihua.javabasic.chapter14.C08FamilyAndExactType");
            Method[] methods = clazz.getMethods();
            for(Method method : methods){
                System.out.println(method.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

