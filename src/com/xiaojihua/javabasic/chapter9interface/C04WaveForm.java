package com.xiaojihua.javabasic.chapter9interface;

public class C04WaveForm {
    private static long counter;
    private long id = counter++;
    public String toString(){ return "WaveForm" + id; }
}
