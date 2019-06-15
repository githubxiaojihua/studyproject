package com.xiaojihua.javabasic.chapter12exception;

/**
 * 知识点：
 * 1、getStackTrace()方法的使用，其返回了一个StackTraceElement的数组。
 * 数组0位置代表了stacktrace的最顶端方法。
 * 2、StackTraceElement getMethodName方法的使用
 */
public class C04WhoCalled {
    public static void f(){
        try{
            throw new Exception();
        }catch(Exception e){
            for(StackTraceElement ele : e.getStackTrace()){
                System.out.println(ele.getMethodName());
            }
        }
    }

    public static void g(){
        f();
    }

    public static void h(){
        g();
    }

    public static void main(String[] args) {
        f();
        System.out.println("===================================");
        g();
        System.out.println("===================================");
        h();
    }
}
