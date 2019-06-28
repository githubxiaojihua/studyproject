package com.xiaojihua.javabasic.chapter19enum;

import static com.xiaojihua.javabasic.chapter19enum.C03Spiciness.*;

/**
 * 知识点：
 * enum的静态引入
 */
public class C02Burrito {
    private C03Spiciness s;

    public C02Burrito(C03Spiciness s){
        this.s = s;
    }

    public String toString(){
        return "C02Burrito:" + s;
    }

    public static void main(String[] args){
        //静态引入后的使用
        System.out.println(new C02Burrito(NOT));
        System.out.println(new C02Burrito(MILD));
        System.out.println(new C02Burrito(HOT));
    }
}

