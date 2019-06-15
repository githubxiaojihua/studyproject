package com.xiaojihua.javabasic.chapter17ContainnerDeeper.hashCode;

import java.util.Random;

public class C02Pretication {
    //用不着每个实例都有一个random，共享就行
    private static Random random = new Random();
    boolean bool = random.nextDouble() > 0.5;
    public String toString(){
        if(bool){
           return "Six more weeks of Winter!";
        }else{
            return "Early Spring!";
        }
    }
}
