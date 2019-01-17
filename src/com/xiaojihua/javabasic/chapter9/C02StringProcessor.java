package com.xiaojihua.javabasic.chapter9;

import java.util.Arrays;

/**
 * 知识点：抽象类也可以作为public的主类，并且可以存在main方法并且运行，但是不能实例化，要实例化可以这样：
 * StringProcessor stringProcessor = new StringProcessor() {
 *             @Override
 *             public String process(Object s) {
 *                 return null;
 *             }
 *         };
 */
public abstract class C02StringProcessor implements Processor {
    public String name(){
        return getClass().getSimpleName();
    }
    public abstract String process(Object s);
    public static String s = "if she weighs the same as a duck,she's made of wood";

    public static void main(String[] args) {
        C01Apply.process(new Upcase1(), s);
        C01Apply.process(new Downcase1(), s);
        C01Apply.process(new Splitter1(), s);
    }

}

class Upcase1 extends C02StringProcessor {
    public String process(Object s){
        return ((String) s).toUpperCase();
    }
}

class Downcase1 extends C02StringProcessor {
    public String process(Object s){
        return ((String) s).toLowerCase();
    }
}

class Splitter1 extends C02StringProcessor {
    public String process(Object s){
        return Arrays.toString(((String)s).split(" "));
    }
}
