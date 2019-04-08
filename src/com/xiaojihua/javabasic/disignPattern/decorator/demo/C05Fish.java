package com.xiaojihua.javabasic.disignPattern.decorator.demo;

public class C05Fish extends C03Change {
    public C05Fish(C01TheGreatestStage stage){
        super(stage);
    }

    @Override
    public void move(){
        System.out.println("Fish Move!");
    }
}
