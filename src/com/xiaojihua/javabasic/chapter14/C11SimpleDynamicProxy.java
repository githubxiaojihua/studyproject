package com.xiaojihua.javabasic.chapter14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 知识点：
 * 1、通过java的动态代理机制重写C10SimpleProxyDemo.java
 * 2、动态代理主要通过InvocationHandler接口以及Proxy类来组合完成。InvocationHandler接口的
 * 实现类用于拦截被代理对象的所有方法，并在此基础上进行代理操作。Proxy主要用于根据目标对象以及
 * InvocationHandler的实现类生成动态代理对象。
 */
public class C11SimpleDynamicProxy {
    public static void customer(Interface proxied){
        proxied.doSomething();
        proxied.somethingElse("abc");
    }
    public static void main(String[] args){
        RealObject rb = new RealObject();
        customer(rb);
        /**
         * 生成代理对象，注意代理的类型应该是被代理对象实现的接口，
         * 另外生成的代理对象默认是Object的，需要将其进行强制类型
         * 转换。
         * 所需要的参数包括classloader，这个可以通过已加载的类来获得，
         * 被代理对象所实现的接口这个可以通过被代理对象来生成，也可以
         * 直接传入class数组:new Class[]{Interface.class}，最后就是
         * 传入InvocationHandler的实现类，并且将目标对象通过构造函数
         * 传递给他。
         */
        Interface proxy = (Interface)Proxy.newProxyInstance(
                Interface.class.getClassLoader(),rb.getClass().getInterfaces(),
                new DynamicInvocationHandler(rb));
        //传入代理对象，完成额外业务的嵌入。
        customer(proxy);
    }
}

/**
 * 被代理对象的额外处理逻辑，放到InvocationHandler接口
 * 的实现类中。
 * 通过Proxy生成的代理对象上的每一次方法调用，都会转到
 * 此实现类的invoke方法中。
 */
class DynamicInvocationHandler implements InvocationHandler{
    private Interface proxied;//存储被代理对象（target Object)
    //通过初始化程序来记录被代理对象
    public DynamicInvocationHandler(Interface proxied){
        this.proxied = proxied;
    }

    /**
     * 每一次在Proxy上调用的方法，都会转到这里进行处理，然后再
     * 返回。
     * @param proxy Proxy通过静态方法生成的Proxy对象
     * @param method 在Proxy上调用的方法
     * @param args 方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         * 注意下面这句不能写成，
         * System.out.println("proxy:" + proxy.toString())，这样会
         * 造成死循环导致内存溢出。
         * 主要是因为所有调用proxy上的方法都会转到此方法来处理，而toString()
         * 也属于proxy中的方法，因此就会造成死循环。
         */
        System.out.println("proxy:" + proxy.getClass());
        System.out.println("method:" + method.toString());
        if(args != null){
            System.out.println("arg:" + args.toString());
        }
        //处理完成以后返回方法的执行结果。
        return method.invoke(proxied,args);
    }
}
