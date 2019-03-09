package com.xiaojihua.javabasic.chapter14.robots;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

/**
 * 知识点：
 * 1、通过动态代理来实现null object的自动生成，前提是围绕着接口编程。
 * 2、C01Null为nullobject的tag interface。
 * 3、C02Operation为命令模式的命令接口，关于命令模式需要单独学习，再此先不深究。
 * 4、C03Robot为机器人接口
 * 5、C04SnowRemovealRobot为机器人接口的一般实现。
 * 6、C05RobotInvocationHandler为动态代理的具体逻辑，里面实现了nullobject的自动生成
 * 7、本类通过Proxy来生成nullobject.
 */
public class C06DynamicProxyForNullRobot {

    /**
     * 静态方法实现代理生成
     * @param typeName
     * @return
     */
    public static C03Robot newNullRobot(Class<? extends C03Robot> typeName){
        return (C03Robot)Proxy.newProxyInstance(C06DynamicProxyForNullRobot.class.getClassLoader(), new Class[]{ C01Null.class, C03Robot.class},
                new C05RobotInvocationHandler(typeName));
    }

    public static void main(String[] args){
        List<C03Robot> rList = Arrays.asList(
                new C04SnowRemovalRobot("abc"),
                newNullRobot(C04SnowRemovalRobot.class)
        );
        for(C03Robot robot : rList){
            C03Robot.Test.test(robot);
        }

    }
}
