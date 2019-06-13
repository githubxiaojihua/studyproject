package com.xiaojihua.javabasic.util;

/**
 * 自定义内部程序执行异常
 */
public class OSExecutionException extends RuntimeException {
    public OSExecutionException(String message){
        super(message);
    }
}
