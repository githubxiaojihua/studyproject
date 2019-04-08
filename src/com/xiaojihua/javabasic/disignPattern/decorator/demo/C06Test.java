package com.xiaojihua.javabasic.disignPattern.decorator.demo;

/**
 * 装饰器的一个应用实例：
 * C01THGreatestStage是齐天大圣的接口类
 * C02Monkey是具体的构件类
 * C03Change是装饰器类
 * C04Birde和C05Fish是具体的装饰器类
 *
 */
public class C06Test {
    public static void main(String[] args){
        //声明一个构建类
        C01TheGreatestStage stage = new C02Monkey();
        /*
            对构建类增加birde相关功能（C04Birde扩展了接口的move方法）
            如果要使用birde或者是fish的特有方法那么就需要声明C04Birde
            或者C05Fish类型的变量。这种模式下属于半透明的装饰模式。
            而接口类型的引用属于全透明的装饰模式。
         */
        C01TheGreatestStage birde = new C04Birde(stage);
        //对构建类增加fish相关功能
        C01TheGreatestStage fish = new C05Fish(birde);
        fish.move();

        //半透明模式
        C04Birde birde1 = new C04Birde(stage);
        birde1.move();
    }
}
