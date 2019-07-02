package com.xiaojihua.javabasic.chapter19enum;

import com.xiaojihua.javabasic.util.OSExecution;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * enum类在创建的时候，编译器会自动创建一个继承自Enum的类，并且会增加一些 静态方法到这个类中。
 * 比如：Explore在编译的时候会生成一个final class Explore extends Enum<Explore>。里面增加了
 * values(),valueOf(一个参数的)等方法，Enum是没有values方法的。
 * 另外编译成的类是final的这也就是enum类不能继承的一个原因，而enum内部的每一个实例被编译成
 * static final的。
 *
 * 本例通过反射来比较enum类Explore与Enum中的方法异同，并且通过反编译工具来查看编译完成以后的
 * Explore类。
 */
public class C07Reflection {

    /**
     * 分析enum类Explore和Enum，由于Explore也是继承自Enum因此参数写成
     * Class<? extends Enum></>
     * @param clazz
     * @return
     */
    private static Set<String> analycis(Class<? extends Enum> clazz){
        print("------analycis " + clazz + "------------");
        print("Interfaces");
        //返回clazz所直接继承的接口
        for(Type t : clazz.getGenericInterfaces()){
            print(t);
        }
        //返回clazz的父类
        print("Base:" + clazz.getSuperclass());
        print("methods:");
        Set<String> methods = new TreeSet<>();
        for(Method m : clazz.getMethods()){
            methods.add(m.getName());
        }
        print(methods);
        return methods;
    }

    public static void main(String[] args){
        Set<String> explorMethods = analycis(Explore.class);
        Set<String> enumMethods = analycis(Enum.class);

        print("is explorMethods contains all enumMethods?" + explorMethods.containsAll(enumMethods));

        explorMethods.removeAll(enumMethods);
        print("explorMethods remove all enumMethods:" + explorMethods);

        //使用工具类反编译Explore
        OSExecution.command("javap target\\classes\\com\\xiaojihua\\javabasic\\chapter19enum\\Explore");

    }
}

enum Explore{
    HERE, THERE;
}
