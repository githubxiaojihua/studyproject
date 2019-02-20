package com.xiaojihua.javabasic;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("a\\u030A", Pattern.CANON_EQ);
        Matcher matcher = pattern.matcher("\\u00E5");
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}

