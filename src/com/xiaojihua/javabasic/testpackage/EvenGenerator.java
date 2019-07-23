package com.xiaojihua.javabasic.testpackage;

public class EvenGenerator extends IntGenerator {
    private int number = 0;
    @Override
    public int next(){
        ++number;
        ++number;
        return number;
    }
}
