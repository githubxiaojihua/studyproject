package com.xiaojihua.gof23.p16strategy;

/**
 * 负责和具体的策略类交互
 * 这样的话，具体的算法和直接的客户端调用分离了，使得算法可以独立于客户端独立的变化。
 * 如果使用spring的依赖注入功能，还可以通过配置文件，动态的注入不同策略对象，动态的切换不同的算法.
 * @author Administrator
 *
 */
public class C06Context {
    // 当前正在应用的strategy
    private C01Strategy strategy;

    // 可以使用构造方法注入
    public C06Context(C01Strategy strategy) {
        this.strategy = strategy;
    }

    // 也可以使用set方法注入
    public void setStrategy(C01Strategy strategy) {
        this.strategy = strategy;
    }

    // 应用策略
    public void printPrice(double p){
        System.out.println("您该报价：" + strategy.getPricy(p));
    }
}
