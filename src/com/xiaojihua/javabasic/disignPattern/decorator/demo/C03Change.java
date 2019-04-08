package com.xiaojihua.javabasic.disignPattern.decorator.demo;

public class C03Change implements C01TheGreatestStage {
    private C01TheGreatestStage theGreatestStage;
    public C03Change(C01TheGreatestStage theGreatestStage){
        this.theGreatestStage = theGreatestStage;
    }

    @Override
    public void move(){
        theGreatestStage.move();
    }
}
