package com.xiaojihua.javabasic.chapter9interface;

import java.util.Arrays;

/**
 * 知识点1：策略模式
 * 创建能够根据传入参数对象的不同而具有不同行为的方法。比如本例中的Apple.process方法。
 * 方法包含只包含不变的逻辑，而策略则包含变化的行为，这里所说的策略就是传入的参数对象，它包含
 * 要执行的代码，在本例中main方法中有三种不同的策略应用到了String对象S上。
 * 知识点2：协变返回类型。
 * 知识点3：通过策略模式解耦代码
 */

/**
 * 定义策略接口
 */
interface Processor{
    String name();
    Object process(Object input);
}

/**
 * 策略实现类
 */
class Upcase implements Processor{
    public String name(){ return getClass().getSimpleName(); }
    public String process(Object input){//协变返回类型,另外要满足重写必须名称和参数列表与接口一直
        return ((String) input).toUpperCase();
    }
}

/**
 * 策略实现类
 */
class Downcase implements Processor{
    public String name(){ return getClass().getSimpleName(); }
    public String process(Object input){
        return ((String) input).toLowerCase();
    }
}

/**
 * 策略实现类
 */
class Splitter implements Processor{
    public String name(){ return getClass().getSimpleName(); }
    public String process(Object input){
        return Arrays.toString(((String) input).split(" "));
    }
}


public class C01Apply {
    //应用策略
    public static void process(Processor p, Object o){
        System.out.println(p.name());
        System.out.println(p.process(o));
    }
    public static String s = "disagreement with beliefs is by definition incorrect";
    public static void main(String[] args) {
        //使用三种不同的策略
        process(new Upcase(),s);
        process(new Downcase(),s);
        process(new Splitter(),s);
    }
}
