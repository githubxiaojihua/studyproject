package com.xiaojihua.javabasic.chapter17ContainnerDeeper.hashCode;

public class C01GrandHog {
    protected int number;//因为子类也要使用
    public C01GrandHog(int number){
        this.number = number;
    }

    @Override
    public String toString(){
        return "GrandHog #" + number;
    }
}
