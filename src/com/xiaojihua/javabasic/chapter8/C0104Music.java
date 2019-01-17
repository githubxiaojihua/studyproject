package com.xiaojihua.javabasic.chapter8;

public class C0104Music {
    //多态，面向接口编程。虽然此时Instrument不是接口，只是父类，但是原理是一样的
    public static void tune(C0101Instrument i){
        i.play(C0103Note.C_CHARP);
    }

    public static void main(String[] args) {
        C0102Wind wind = new C0102Wind();
        tune(wind);
    }
}
