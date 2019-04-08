package com.xiaojihua.javabasic.disignPattern.decorator.demo;

public class C04Birde extends C03Change {
    public C04Birde(C01TheGreatestStage stage){
        super(stage);
    }

    @Override
    public void move(){
        System.out.println("Birde move");
    }
}
