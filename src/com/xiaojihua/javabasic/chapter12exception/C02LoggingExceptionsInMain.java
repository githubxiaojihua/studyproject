package com.xiaojihua.javabasic.chapter12exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * 知识点：
 * 1、在自定义异常外进行日志的设置
 */
public class C02LoggingExceptionsInMain {
    private static Logger logger = Logger.getLogger("C02LoggingExceptionInMain");
    public static void exceptionHandl(Exception e){
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.info("caught:" + trace.toString());
    }

    public static void main(String[] args) {
        try{
            throw new NullPointerException();
        }catch (NullPointerException e){
            exceptionHandl(e);
            System.out.println(e);

        }
    }
}
