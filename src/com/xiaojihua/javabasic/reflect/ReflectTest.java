package com.xiaojihua.javabasic.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 知识点：java的反射机制
 * 常用的类：ClassLoader\Class\Constructor\Method\Field
 */
public class ReflectTest {
    public static Car initByDefaultConst() throws Exception {
        //通过类加载器获取Car类
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.xiaojihua.javabasic.reflect.Car");
        //获取类的默认构造器，并通过默认构造器进行初始化
        Constructor cons = clazz.getDeclaredConstructor((Class[])null);
        Car car = (Car) cons.newInstance();

        Field colorField = clazz.getDeclaredField("color");
        colorField.setAccessible(true);
        colorField.set(car,"黑色");

        Method setBrand = clazz.getMethod("setBrand",String.class);
        setBrand.invoke(car,"宝马");

        /*Method setColor = clazz.getMethod("setColor",String.class);
        setColor.invoke(car,"红色");
*/
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed",int.class);
        setMaxSpeed.invoke(car,200);

        return car;
    }

    public static void main(String[] args) throws Exception {
        Car car = initByDefaultConst();
        car.introduce();
    }
}
