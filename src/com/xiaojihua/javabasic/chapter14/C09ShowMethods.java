package com.xiaojihua.javabasic.chapter14;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;
//静态引入需要具体到某一个方法（方法必须是static的）
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、反射机制部分方法的使用
 * 从命令行接收参数，第一个参数是类的全限定名称，第二个参数可选，是需要在类中搜索的方法。
 * 如果第一个参数没有指定则给出提示。如果只给出第二个参数则列出类中所有的方法包括继承而来的方法。
 */
public class C09ShowMethods {
    private static String usage = "ShowMethods qualified.class.name \n" +
            "To show all methods in class or \n" +
            "ShowMethod qualified.class.name word\n" +
            "To seacher method of 'word'\n";

    public static void main(String[] args){
        if(args.length < 1){
            System.out.println(usage);
            System.exit(0);
        }
        //用于替换方法的全限定名中的包名
        Pattern pattern = Pattern.compile("\\w+\\.");
        try {
            /**
             * 根据参数加载类。使用泛型？来标识可以可以是任何类型的。加不加？其实作用是一样的，但是加上
             * 就代表知道Class可以使用泛型，并且明确指定可以接收任何类型。
             *
             * Class.forName()所加载的类，在程序编译期间是未知的，因此只有通过反射机制
             * 才能在运行时动态的提取所加载的类的相关信息
             */
            Class<?> clazz = Class.forName(args[0]);
            //获取方法对象列表
            Method[] methods = clazz.getMethods();
            //获取构造方法列表
            Constructor[] cons = clazz.getConstructors();
            if(args.length == 1){
                //循环遍历方法，并将method.toStirng()中多余的部分替换掉
                for(Method method : methods){
                    /**
                     * method.toString()返回方法的完整特征签名。比如：
                     * public static void com.xiaojihua.javabasic.chapter14.C08FamilyAndExactType.test(java.lang.Object)
                     */
                    print(pattern.matcher(method.toString()).replaceAll(""));
                }
                for(Constructor con : cons){
                    print(pattern.matcher(con.toString()).replaceAll(""));
                }
            }else{
                for(Method method : methods){
                    //使用String的indexOf方法来判断方法名称中是否含有参数中指定的名称
                    if(method.toString().indexOf(args[1]) != -1){
                        System.out.println(pattern.matcher(method.toString()).replaceAll(""));
                    }
                }
                for(Constructor con : cons){
                    if(con.toString().indexOf(args[1]) != -1){
                        System.out.println(pattern.matcher(con.toString()).replaceAll(""));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
