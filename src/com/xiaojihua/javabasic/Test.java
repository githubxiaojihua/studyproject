package com.xiaojihua.javabasic;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{
    public static void main(String[] args) {
        try{
            throw new LoggingException();
        }catch(LoggingException e){
            //err和out是两个标准输出，使用Logger的是使用err
            //可以将err换成out来观察他们的输出，格式不一样。
            System.err.println("Caught:" + e);
        }
    }
}

class LoggingException extends Exception{
    //声明Logger对象，该对象输出到System.err
    private static Logger logger = Logger.getLogger("LoggingException");
    public LoggingException(){
        //通过调用printStackTrace的重载方法，并且配合PrintWriter将
        //Exception的stack trace信息输出到一个StringWriter中
        //并且最终可以通过toString方法将信息输出懂啊logger中。
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        //通过调用logger的等级方法，往logger中写信息，这里调用了severe（严重）
        logger.severe(trace.toString());

    }

}