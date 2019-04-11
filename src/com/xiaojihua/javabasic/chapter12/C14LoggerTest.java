package com.xiaojihua.javabasic.chapter12;

import com.xiaojihua.javabasic.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Logger类的使用。
 * 可以输出到console\file\Os logs等地方
 * 具体使用可以参考api文档，可以分级别打印Log
 */
public class C14LoggerTest {
    private static Logger logger = Logger.getLogger(Test.class.getName());
    public static void main(String[] args){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(new Date());
        logger.severe(date);//直接输出
    }
}
