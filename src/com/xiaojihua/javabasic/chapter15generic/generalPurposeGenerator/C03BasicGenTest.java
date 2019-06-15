package com.xiaojihua.javabasic.chapter15generic.generalPurposeGenerator;

public class C03BasicGenTest {
    public static void main(String[] args){
        C01BasicGenerator<C02CountClass> gen = C01BasicGenerator.create(C02CountClass.class);
        for (int i=0; i<5; i++){
            System.out.println(gen.next());
        }
    }
}
