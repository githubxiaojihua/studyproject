package com.xiaojihua.gof23.p01singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 验证单例
 */
public class C07Client02 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {
        C05Singleton05 s1 = C05Singleton05.getInstance();
        C05Singleton05 s2 = C05Singleton05.getInstance();
        System.out.println(s1==s2);

        // 测试反射破解单例模式
        // 解决方法是在单例类的构造方法中进行判断并抛出异常
        /*Class<C05Singleton05> aClass = (Class<C05Singleton05>) Class.forName("com.xiaojihua.gof23.p01singleton.C05Singleton05");
        Constructor<C05Singleton05> declaredConstructor = aClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        C05Singleton05 c05Singleton05 = declaredConstructor.newInstance();
        System.out.println(s1==c05Singleton05);*/

        // 测试反序列化破解单例模式
        // 解决的方法是在单例类中重写readResolve方法
        FileOutputStream out = new FileOutputStream("d:/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(s1);
        oos.close();;
        out.close();

        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/a.txt"));
        C05Singleton05 s3 =  (C05Singleton05)ois.readObject();
        System.out.println(s1==s3);

    }
}
