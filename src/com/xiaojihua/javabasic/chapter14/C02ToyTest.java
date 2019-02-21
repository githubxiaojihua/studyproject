package com.xiaojihua.javabasic.chapter14;

/**
 * 知识点：
 * 1、Class类的使用。每一个类编译后都会生成一个Class对象，存储在.class文件中。
 * 2、Class类可以获得在程序运行期间类型信息。
 */

interface HasBatteries{}
interface Waterproof{}
interface Shoots{}

class Toy{
    Toy(){}
    Toy(int i){}
}

class FancyToy extends Toy implements HasBatteries,Waterproof,Shoots{
    static{
        System.out.println("当使用forName加载的时候会初始化哦1");
    }
    FancyToy(){
        super(1);
        System.out.println("当使用forName加载的时候会初始化哦2，此行不会打印。");
    }
}
public class C02ToyTest {
    /**
     * 通过Class对象方法，输出相关的类型信息
     * @param cc
     */
    public static void printClassInfo(Class cc){
        //获得类名以及是否为接口
        System.out.println("Class name:" + cc.getName() + ",is Interface?[" + cc.isInterface() + "]");
        //获得简单类名
        System.out.println("Simple name:" + cc.getSimpleName());
        //获得全限定类名
        System.out.println("Canonical name:" + cc.getCanonicalName());
    }

    public static void main(String[] args){
        Class cc = null;
        try {
            /**
             * 加载类。参数需要类的全限定类名
             * 通过forName来进行类加载的时候会进行类的初始化(注意是初始化并非实例化)
             * forName返回一个加载类Class对象的引用
             */
            cc = Class.forName("com.xiaojihua.javabasic.chapter14.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find FancyToy");
            System.exit(1);
        }
        printClassInfo(cc);
        /**
         * 遍历加载类所实现的接口，并打印接口信息
         */
        for(Class intface : cc.getInterfaces()){
            printClassInfo(intface);
        }

        //获取加载类的父类
        Class obj = cc.getSuperclass();
        Object supObj = null;
        try {
            //newInstance()通过默认构造器生成类的实例，返回的是object引用，但实际指向了Toy类
            supObj = obj.newInstance();//所实例化的类必须提供默认构造器，可以进行类型转化
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        printClassInfo(supObj.getClass());

        /**
         * 通过类字面常量来生成类的Class对象的引用。
         * 通过类字面常量来生成类的Class对象引用，并不会进行类的初始化
         * 普通的类、接口、数组、基本类型都可以使用
         */
        Class cd = FancyToy.class;
        printClassInfo(cd);
    }

}
