package com.xiaojihua.gof23.p01singleton;

/**
 * 单例模式---静态内部类
 * 线程安全
 * 调用效率高
 * 可以延迟加载
 */
public class C03Singleton03 {

    private static class SingletonClassInstance{
        private static final C03Singleton03 instance = new C03Singleton03();
    }

    private C03Singleton03(){}

    //方法没有同步，调用效率高！
    public static C03Singleton03 getInstance(){
        return SingletonClassInstance.instance;
    }
}
