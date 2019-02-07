package com.xiaojihua.javabasic.chapter13;

/**
 * 知识点：
 * 1、String.format使用，他接收和Formatter.format一样的参数，返回一个String
 * 当字符串只使用一次的时候可以使用这个方法。
 * 比如如下实例：格式化异常提示信息。
 * String.format内部也使用了Formatter类：
 * return new Formatter().format(format, args).toString();
 */
public class C08DataBaseException extends Exception {
    public C08DataBaseException(int transactionId, int queryId, String message){
        super(String.format("(t%d, q%d) %s",transactionId, queryId, message));
    }

    public static void main(String[] args){
        try{
            throw new C08DataBaseException(12, 11, "this is a DataBaseException");
        }catch(C08DataBaseException e){
            e.printStackTrace(System.out);
        }
    }
}
