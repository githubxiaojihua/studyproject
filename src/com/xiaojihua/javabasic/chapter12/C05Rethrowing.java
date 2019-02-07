package com.xiaojihua.javabasic.chapter12;

/**
 * 知识点：
 * 1、简单的重新抛出异常，可以在catche语句中直接throw e，这种方式抛出的异常
 * StackTrace打印的信息，不管是从哪里重新抛出的，都包含着所有的信息（也就是包含着
 * 原始抛出点）
 * 2、通过e.fillInStackTrace()方法可以重新装配StackTrace信息，改变原始异常对象
 * e，将其中的原始抛出点改为重新抛出的地方。
 */
public class C05Rethrowing {
    /**
     * f为原始抛出点
     * @throws Exception
     */
    public static void f() throws Exception{
        System.out.println("inside f()");
        throw new Exception("from f()");
    }

    /**
     * 普通重新抛出点
     * @throws Exception
     */
    public static void g() throws Exception{
        try{
            f();
        }catch(Exception e){
            //System.out.println("inside g()");
            //e.printStackTrace(System.out);
            throw e;
        }
    }

    /**
     * 使用e.fillInStackTrace方法重新抛出，改变了原始抛出点
     * @throws Exception
     */
    public static void h() throws Exception{
        try{
            f();
        }catch(Exception e){
            //System.out.println("inside h()");
            //e.printStackTrace(System.out);
            throw (Exception)e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        //以下两块最好分开输出观察，可能是jdk8以后异常的处理是多线程的，因此输出的顺序
        //并非按照写的顺序

        /**
         * 此处调用g()方法f()抛出原始异常，输出的StackTrace为 f,g,main
         */
        try{
            g();
        }catch(Exception e){
            System.out.println("inside main");
            e.printStackTrace();
        }
        System.out.println("==========================================");

        /**
         * 此处调用h()方法f()抛出原始异常，但是在h中调用了fillInStackTrace()方法重新抛出了
         * 异常，因此，输出的StackTrace为 h,main.
         */
        try{
            h();
        }catch(Exception e){
            System.out.println("inside main");
            e.printStackTrace();
        }
    }

}
