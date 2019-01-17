package com.xiaojihua.javabasic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test {
    public static void main(String[] args) {
        try{
            throw new RuntimeException("aaa");
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
    }
}