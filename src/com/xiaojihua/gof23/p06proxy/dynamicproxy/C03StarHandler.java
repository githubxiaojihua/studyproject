package com.xiaojihua.gof23.p06proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 使用jdk动态代理机制生成代理
 * 实现invocationhandler接口
 * 持有代理对象的引用
 */
public class C03StarHandler implements InvocationHandler {
    private C01Star star;

    public C03StarHandler(C01Star star) {
        this.star = star;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        System.out.println("调用方法之前调用----");
        System.out.println("面谈，签合同，预付款，订机票");

        if(method.getName().equals("sing")){
            obj = method.invoke(star, args);
        }

        System.out.println("调用方法之后调用----");
        System.out.println("收尾款");
        return obj;
    }
}
