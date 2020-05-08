package com.xiaojihua.gof23.p01singleton;

/**
 * 验证单例
 */
public class C05Client {

    public static void main(String[] args){
        C01Singleton01 s1 = C01Singleton01.getInstance();
        C01Singleton01 s2 = C01Singleton01.getInstance();
        System.out.println(s1==s2);

        C02Singleton02 s3 = C02Singleton02.getInstance();
        C02Singleton02 s4 = C02Singleton02.getInstance();
        System.out.println(s3==s4);

        C03Singleton03 s5 = C03Singleton03.getInstance();
        C03Singleton03 s6 = C03Singleton03.getInstance();
        System.out.println(s5==s6);

        C04Singleton04 s7 = C04Singleton04.INSTANCE;
        C04Singleton04 s8 = C04Singleton04.INSTANCE;
        System.out.println(s7==s8);

    }
}
