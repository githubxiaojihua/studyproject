package com.xiaojihua.javabasic.chapter12;

/**
 * 知识点：
 * finally和return的位置关系
 * finally仍然在最后执行，不管return在什么位置
 */
public class C07FinalAndReturn {
    public static void f(int i){
        System.out.println("inside f()");
        try{
            System.out.println("point 1");
            if(i == 1){
                return;
            }
            System.out.println("point 2");
            if(i == 2){
                return;
            }
            System.out.println("point 3");
            if(i == 3){
                return;
            }
        }finally{
            System.out.println("finally clean up");
        }
    }

    public static void main(String[] args) {
        for(int i = 1; i <= 4; i++){
            f(i);
        }
    }
}
