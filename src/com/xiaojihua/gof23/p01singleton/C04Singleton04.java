package com.xiaojihua.gof23.p01singleton;

/**
 * 单例模式---枚举实现
 * 线程安全
 * 调用效率高
 * 不能延迟加载
 */
public enum C04Singleton04 {
    //这个枚举元素，本身就是单例对象！
    INSTANCE;
    //添加自己需要的操作！
    public void singletonOperation(){}
}
