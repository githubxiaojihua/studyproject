package com.xiaojihua.javabasic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{

    private static final int  MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args) throws Exception{
        File file = new File(".\\studyproject.iml");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
    }



}



