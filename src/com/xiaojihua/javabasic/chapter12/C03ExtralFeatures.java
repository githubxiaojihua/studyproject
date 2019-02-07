package com.xiaojihua.javabasic.chapter12;

/**
 * 知识点：
 * 1、自定义异常的使用
 */
class MyException extends Exception{
    private int value;

    public MyException(){}
    public MyException(String message){
        super();
    }
    public MyException(String message, int value){
        this(message);
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    /**
     * 此方法相当与Exception的toString方法
     * @return
     */
    @Override
    public String getMessage(){
        return "Detail:" + super.getMessage();
    }
}
public class C03ExtralFeatures {
    public static void f() throws MyException {
        System.out.println("throw MyException in f()");
        throw new MyException();
    }
    public static void g() throws MyException{
        System.out.println("throw MyException in g()");
        throw new MyException("throwed in g()");
    }
    public static void h() throws MyException{
        System.out.println("throw MyException in h()");
        throw new MyException("throwed in h()", 20);
    }

    public static void main(String[] args) {
        try{
            f();
        }catch(MyException e){
            e.printStackTrace(System.out);
        }

        try{
            g();
        }catch(MyException e){
            e.printStackTrace(System.out);
        }

        try{
            h();
        }catch(MyException e){
            e.printStackTrace(System.out);
            System.out.println(e.getValue());
        }
    }
}
