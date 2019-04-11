package com.xiaojihua.javabasic;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{
    private static Logger logger = Logger.getLogger(Test.class.getName());
    public static void main(String[] args){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(new Date());
        logger.severe(date);
    }
}



