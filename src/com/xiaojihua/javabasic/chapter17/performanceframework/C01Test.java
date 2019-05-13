package com.xiaojihua.javabasic.chapter17.performanceframework;

/**
 * 知识点：
 * 1、建立一个容器性能测试框架，基于泛型
 * 2、使用了</b>模版方法</b>设计模式
 * @param <C>
 */
public abstract class C01Test<C> {
    String name;
    public C01Test(String name){
        this.name = name;
    }

    /**
     * 模版方法。
     * 对于容器containner，进行param指定参数的测试
     * @param containner 要进行测试的容器
     * @param param 存放测试的参数size和loops
     * @return
     */
    abstract int test(C containner,C02TestParam param);
}
