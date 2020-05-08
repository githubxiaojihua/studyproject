package com.xiaojihua.gof23.p01singleton;

/**
 * 单例模式---懒汉式
 * 线程安全，在公共方法中增加了synchronized关键字
 * 调用效率不高
 * 可以延迟加载
 *
 *
 */
public class C02Singleton02 {
    //类初始化时，不初始化这个对象（延时加载，真正用的时候再创建）。
    private static C02Singleton02 s;
    //私有化构造函数
    private C02Singleton02(){}

    //方法同步，调用效率低！
    public static synchronized C02Singleton02 getInstance(){
        if(s == null){
            s = new C02Singleton02();
        }
        return s;
    }
}
