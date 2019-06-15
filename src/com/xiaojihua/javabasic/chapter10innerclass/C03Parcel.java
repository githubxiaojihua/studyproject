package com.xiaojihua.javabasic.chapter10innerclass;

/**
 * 知识点：匿名内部类的使用
 */
public class C03Parcel {
    public C01Contents contents(){
        return new C01Contents() {
            private int i = 11;
            @Override
            public int value() {
                return i;
            }
        };
    }

    public C02Destination destination(final String dest){
        return new C02Destination() {
            private String lable = dest;
            @Override
            public String readLable() {
                return lable;
            }
        };
    }

    public static void main(String[] args) {
        C03Parcel parcel = new C03Parcel();
        C01Contents contents = parcel.contents();
        C02Destination destination = parcel.destination("aaa");
        System.out.println(destination.readLable());
    }
}
