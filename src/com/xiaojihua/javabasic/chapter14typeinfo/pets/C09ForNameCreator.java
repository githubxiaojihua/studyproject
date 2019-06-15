package com.xiaojihua.javabasic.chapter14typeinfo.pets;

import java.util.ArrayList;
import java.util.List;

/**
 * 模版设计模式的实现类
 * 实现了抽象类中指定的抽象方法
 */
public class C09ForNameCreator extends C08PetCreator {
    private static List<Class<? extends C02Pet>> types = new ArrayList<>();
    private static String[] typeNames = {
            "com.xiaojihua.javabasic.chapter14typeinfo.pets.C02Pet",
            "com.xiaojihua.javabasic.chapter14typeinfo.pets.C03Dogs",
            "com.xiaojihua.javabasic.chapter14typeinfo.pets.C04Mutt",
            "com.xiaojihua.javabasic.chapter14typeinfo.pets.C05Pug",
            "com.xiaojihua.javabasic.chapter14typeinfo.pets.C06Cat",
            "com.xiaojihua.javabasic.chapter14typeinfo.pets.C07Manx"};

    /**
     * 通过forName来加载类，并返回Class类的引用
     */
    private static void loader(){
        for(String typeName : typeNames){
            try {
                types.add((Class<? extends C02Pet>)Class.forName(typeName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //使用静态代码块进行初始化
    static{
        loader();
    }

    /**
     * 实现抽象访方法
     * @return
     */
    @Override
    public List<Class<? extends C02Pet>> types() {
        return types;
    }
}
