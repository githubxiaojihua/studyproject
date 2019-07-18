package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 知识点：
 * 设置线程的默认uncaughtExceptionHandler，
 * 当没有为当前线程指定属于自己的Handler的时候，系统就会选择默认Handler
 */
public class C09SettingDefaultHandler {
    public static void main(String[] args){
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new ExceptionThread());
    }
}
