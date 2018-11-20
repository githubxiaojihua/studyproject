package com.xiaojihua.javabasic.chapter10;

import sun.security.krb5.internal.crypto.Des;

/**
 * 知识点：匿名内部类的使用
 */
public class Parcel {
    public Contents contents(){
        return new Contents() {
            private int i = 11;
            @Override
            public int value() {
                return i;
            }
        };
    }

    public Destination destination(final String dest){
        return new Destination() {
            private String lable = dest;
            @Override
            public String readLable() {
                return lable;
            }
        };
    }

    public static void main(String[] args) {
        Parcel parcel = new Parcel();
        Contents contents = parcel.contents();
        Destination destination = parcel.destination("aaa");
        System.out.println(destination.readLable());
    }
}
