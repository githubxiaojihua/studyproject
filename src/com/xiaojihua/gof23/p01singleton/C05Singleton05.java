package com.xiaojihua.gof23.p01singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 单例模式---懒汉式
 * 用于测试反射和反序列化对单例模式的破解
 *
 *
 */
public class C05Singleton05 implements Serializable {
    //类初始化时，不初始化这个对象（延时加载，真正用的时候再创建）。
    private static C05Singleton05 s;
    //私有化构造函数
    private C05Singleton05(){
        if(s != null){
            throw new RuntimeException();
        }
    }

    //方法同步，调用效率低！
    public static synchronized C05Singleton05 getInstance(){
        if(s == null){
            s = new C05Singleton05();
        }
        return s;
    }

    //反序列化时，如果定义了readResolve()则直接返回此方法指定的对象。而不需要单独再创建新对象！
    private Object readResolve() throws ObjectStreamException {
        return s;
    }
}
