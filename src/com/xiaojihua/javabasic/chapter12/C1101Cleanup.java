package com.xiaojihua.javabasic.chapter12;

/**
 * 知识点：
 * 1、创建了需要清理的对象之后，应该立即跟着try finally。确保对象的清理
 * 2、如果此对象在构造函数阶段就有可抛出的异常，那么应该已单独的try catch快进行处理
 */
public class C1101Cleanup {
    public static void main(String[] args) {
        try{
            C11InputFile inputFile = new C11InputFile("abc.java");
            try{
                String s;
                int i;
                while((s = inputFile.getLine()) != null);
            }catch(Exception runE){
                System.out.println("caught a exception in main");
                runE.printStackTrace(System.out);
            }finally{
                inputFile.dispose();
            }
        }catch(Exception e){
            System.out.println("C11InputFile constructor fails");
        }
    }
}
