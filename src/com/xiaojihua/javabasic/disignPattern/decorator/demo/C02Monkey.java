package com.xiaojihua.javabasic.disignPattern.decorator.demo;

public class C02Monkey implements C01TheGreatestStage {
    @Override
    public void move(){
        System.out.println("Monkey move!");
    }
}
