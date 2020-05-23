package com.xiaojihua.gof23.p17template;

/**
 * 模版方法的使用
 *
 */
public abstract class C01BankTemplateMethod {
    /**
     * 具体方法
     */
    public void tackNumber(){
        System.out.println("取号排队！");
    }

    /**
     * 抽象方法，具体的业务办理
     * 也叫钩子方法，由具体的子类实现
     */
    public abstract void transact();

    /**
     * 具体方法
     */
    public void evaluate(){
        System.out.println("反馈评分！");
    }

    /**
     * 模版方法，通常步骤都是固定的因此一般设置为final
     * 不允许子类设置
     */
    public final void process(){
        this.tackNumber();
        this.transact();
        this.evaluate();
    }
}
