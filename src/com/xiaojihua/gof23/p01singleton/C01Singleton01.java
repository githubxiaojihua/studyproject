package com.xiaojihua.gof23.p01singleton;

/**
 * 单例模式---饿汉式，
 * 线程安全，因为加载类的时候是天然线程安全的，不会发生创建多次对象的情况。
 * 调用效率高
 * 不能延时加载，如果初始化此类的资源消耗较高，那么会造成资源浪费。
 *
 */
public class C01Singleton01 {
    //类初始化时，立即加载这个对象（没有延时加载的优势）。加载类时，天然的是线程安全的！
    private static C01Singleton01 s = new C01Singleton01();
    //私有化构造函数
    private C01Singleton01(){}

    //方法没有同步，调用效率高！
    public static C01Singleton01 getInstance(){
        return s;
    }
}
