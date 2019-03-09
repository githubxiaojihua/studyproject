package com.xiaojihua.javabasic.chapter14.robots;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * 用内部类来表示具体的null object。
 */
public class C05RobotInvocationHandler implements InvocationHandler {
    private String nullName;
    private NullRobot nullRobot = new NullRobot();

    /**
     * 通过参数来设置不同的nullName
     * @param robotType
     */
    C05RobotInvocationHandler(Class<? extends C03Robot> robotType){
        this.nullName = robotType.getSimpleName();
    }

    /**
     * 通过内部类来表示具体的null object
     * 实现了Null和robot接口
     * 方便用instanceof来探测
     */
    class NullRobot implements C01Null,C03Robot{
        @Override
        public String name(){
            return nullName;
        }
        @Override
        public String modole(){
            return "nullModoles";
        }
        @Override
        public List<C02Operation> operations(){
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        return method.invoke(nullRobot, args);
    }
}
