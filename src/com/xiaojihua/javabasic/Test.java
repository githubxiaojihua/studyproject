package com.xiaojihua.javabasic;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{

    public static void main(String[] args) throws Exception{
        File file = new File(".\\studyproject.iml");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
    }

}
