package com.xiaojihua.javabasic.chapter8;

public class Music {
    //多态，面向接口编程。虽然此时Instrument不是接口，只是父类，但是原理是一样的
    public static void tune(Instrument i){
        i.play(Note.C_CHARP);
    }

    public static void main(String[] args) {
        Wind wind = new Wind();
        tune(wind);
    }
}
