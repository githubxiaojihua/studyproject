package com.xiaojihua.javabasic.chapter9;

public class WaveForm {
    private static long counter;
    private long id = counter++;
    public String toString(){ return "WaveForm" + id; }
}
