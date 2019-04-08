package com.xiaojihua.javabasic.chapter15.mixins;

import java.util.Date;

/**
 * 知识点：
 * 混型：通过混合多个类的能力（方法），生成一个能代表所有被混合类型结果类，这个结果类可以
 * 拥有所有混合类的能力（方法）。
 *
 * 在java中使用接口和继承是混型的通用实现路径。而其内部基本上是使用代理实现的
 */
public class C01MixinsByInterface {
    public static void main(String[] args){
        Mixins mixin1 = new Mixins(), mixin2 = new Mixins();
        mixin1.set("mixin1");
        mixin2.set("mixin2");
        System.out.println(mixin1.get() + "," + mixin1.getTimStemp() + "," + mixin1.getSerialNum());
        System.out.println(mixin2.get() + "," + mixin2.getTimStemp() + "," + mixin2.getSerialNum());
    }
}

/**
 * TimeStemp接口以及实现类
 */
interface TimeStemp{
    long getTimStemp();
}

class TimeStempImp implements TimeStemp{
    private final long timeStemp;
    public TimeStempImp(){
        timeStemp = new Date().getTime();
    }

    @Override
    public long getTimStemp(){
        return timeStemp;
    }
}

/**
 * SerialNumber接口以及实现类
 */
interface SerialNumber{
    long getSerialNum();
}

class SerialNumberImp implements SerialNumber{
    private static long count = 0;
    private final long ID = count++;

    @Override
    public long getSerialNum(){
        return ID;
    }
}

/**
 * Basic接口以及实现类
 */
interface Basic{
    void set(String val);
    String get();
}

class BasicImp implements Basic{
    private String val;

    @Override
    public void set(String val){
        this.val = val;
    }

    @Override
    public String get(){
        return val;
    }
}

/**
 * 混型
 * 综合了BasicImp、TimeStempImp、SerialNumberImp的功能。
 */
class Mixins extends BasicImp implements TimeStemp, SerialNumber{
    /*
        通过代理和接口来集成相关功能
     */
    private TimeStemp timeStemp = new TimeStempImp();
    private SerialNumber serialNumber = new SerialNumberImp();

    /**
     * 将相关方法映射到被合适的实例中
     * @return
     */
    @Override
    public long getTimStemp(){
        return timeStemp.getTimStemp();
    }

    /**
     * 将相关方法映射到被合适的实例中
     * @return
     */
    @Override
    public long getSerialNum(){
        return serialNumber.getSerialNum();
    }
}