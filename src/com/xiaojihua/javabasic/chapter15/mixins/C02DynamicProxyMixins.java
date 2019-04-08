package com.xiaojihua.javabasic.chapter15.mixins;

import com.xiaojihua.javabasic.chapter15.C01TwoTuple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 知识点：
 * 使用动态代理实现混型
 * 由于java的动态代理是基于接口的，因此必须要求代理的对象实现接口。
 */
public class C02DynamicProxyMixins {
    public static void main(String[] args){
        //创建混型对象，TwoTuple的第二个参数应该是接口
        Object obj = DynamicProxy.newInstance(
                new C01TwoTuple<>(new BasicImp(),Basic.class),
                new C01TwoTuple<>(new TimeStempImp(),TimeStemp.class),
                new C01TwoTuple<>(new SerialNumberImp(),SerialNumber.class)
        );

        //将混型对象当作Basic使用
        Basic basic = (Basic)obj;
        basic.set("ProxyDynamic Mixins");
        System.out.println(basic.get());

        //将混型对象当作TimeStemp使用
        TimeStemp timeStemp = (TimeStemp)obj;
        System.out.println(timeStemp.getTimStemp());

        //将混型对象当作SerialNumber使用
        SerialNumber serialNumber = (SerialNumber)obj;
        System.out.println(serialNumber.getSerialNum());
    }
}

/**
 * 集InvocationHandler和Proxy.newInstance与一体
 */
class DynamicProxy implements InvocationHandler{
    //用于存放代理对象的方法以及相应的代理对象
    private Map<String, Object> proxyByMethod;

    /**
     * 通过构造函数初始化proxyByMethod
     * 使用C01TwoTuple类型的可变参数，接收代理的对象集合
     * Object代表被代理的实例，Class代表为实例所实现的接口
     * 通过构造方法就创造了方法名称和对应的实例之间的映射关系
     * @param tuples
     */
    public DynamicProxy(C01TwoTuple<Object,Class<?>> ... tuples){
        proxyByMethod = new HashMap<>();
        for(C01TwoTuple<Object,Class<?>> tuple : tuples){
            for(Method method : tuple.second.getMethods()){
                String methodName = method.getName();
                if(!proxyByMethod.containsKey(methodName)){
                    proxyByMethod.put(methodName,tuple.first);
                }
            }
        }
    }

    /**
     * 根据调用的方法，获取对应的实例对象
     * 然后进行反射调用
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = proxyByMethod.get(method.getName());
        return method.invoke(obj,args);
    }

    /**
     * 使用静态方法进行Proxy.newProxyInstance的调用，创建代理对象。
     * @param tuple
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object newInstance(C01TwoTuple<Object,Class<?>> ... tuple){
        //TwoTuple的第二个参数应该是接口
        Class[] interfaces = new Class[tuple.length];
        for(int i=0; i<tuple.length; i++){
            interfaces[i] = tuple[i].second;
        }
        ClassLoader cl = tuple[0].second.getClassLoader();
        return Proxy.newProxyInstance(cl,interfaces,new DynamicProxy(tuple));
    }
}
