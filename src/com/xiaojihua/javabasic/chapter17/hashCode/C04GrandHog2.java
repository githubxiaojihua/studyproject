package com.xiaojihua.javabasic.chapter17.hashCode;

public class C04GrandHog2 extends C01GrandHog {
    public C04GrandHog2(int number){
        super(number);
    }

    @Override
    public int hashCode(){
        return this.number;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof C04GrandHog2 && (((C04GrandHog2) o).number == this.number);
    }
}
