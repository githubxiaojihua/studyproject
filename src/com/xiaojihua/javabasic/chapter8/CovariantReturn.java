package com.xiaojihua.javabasic.chapter8;

/**
 * 知识点：协变返回类型：导出类的被覆盖方法的返回值可以是基类被覆盖方法返回值的某种导出类。
 * 对应到本类：
 * WheatMill类的process方法的返回值，可以是Mill类process方法返回值Grain的某个导出类。
 * 这里涵盖了覆盖和斜边返回类型两个知识点。
 * 能够被覆盖的都是基类的对外接口（public或者proteced的方法，private不属于覆盖的范围）
 */
class Grain{
    public String toString(){ return "Grain"; }
}

/**
 * Grain的导出类
 */
class Wheat extends Grain{
    public String toString(){ return "Wheat"; }
}

class Mill{
    Grain process(){ return new Grain(); }
}

/**
 * Mill的导出类
 */
class WheatMill extends Mill{
    Wheat process(){ return new Wheat(); }
}

public class CovariantReturn {
    public static void main(String[] args) {
        Mill mill = new Mill();
        System.out.println(mill.process());
        WheatMill wheatMill = new WheatMill();
        System.out.println(wheatMill.process());
    }
}
