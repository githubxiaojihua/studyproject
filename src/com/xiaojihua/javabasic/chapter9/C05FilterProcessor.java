package com.xiaojihua.javabasic.chapter9;

/**
 * 知识点：
 * 适配器模式。无法修改想要使用的类，适配器是在不改变类的情况下，产生需要的类。
 * 比如：比如Filter类是不可修改的，但是还想让他能应用于Apply.process方法，这个方法需要一个Processor接口，
 * 恰好Filter类的方法跟Processor接口的行为类似，那么可以创建一个适配器，将Filter类转换成Processor接口。
 * 适配器模式使用了代理技术。
 */
class FilterAdapter implements Processor{
    private C03Filter filter;
    FilterAdapter(C03Filter filter){
        this.filter = filter;
    }
    @Override
    public C04WaveForm process(Object s){//代理filter的行为
        return filter.process((C04WaveForm) s);
    }
    @Override
    public String name(){//代理filter的行为
        return filter.name();
    }
}
public class C05FilterProcessor {
    public static void main(String[] args) {
        C04WaveForm waveForm = new C04WaveForm();
        //应用策略模式
        C01Apply.process(new FilterAdapter(new LowPass()), waveForm);
        C01Apply.process(new FilterAdapter(new HighPass()), waveForm);
    }
}
