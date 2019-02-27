package com.xiaojihua.javabasic.chapter14.pets;

import java.util.HashMap;

/**
 * 知识点：
 * 1、instanceof的使用。
 * 2、模版模式的使用：定义了一个抽象类C08PetCreator，里面提供了创建pet数组和list的方法，
 * 但同时将pet的类型决定函数types()作为抽象方法交由子类实现，因此可以生成针对不同types实现
 * 的子类，实现了模版模式。C09ForNameCreator作为实现类提供了ForName的方式来实现types方法。
 * 当然可以创建其他方式的types()方法实现子类。
 * 3、本类中嵌套类Counter的使用：继承并扩展了HashMap，这种方法需要记住。
 */
public class C10PetCount {

    /**
     * 嵌套类集成并扩展HashMap
     * 作为计数器使用
     */
    private static class Counter extends HashMap<String,Integer>{
        public void count(String key){
            if(get(key) == null){
                put(key,1);
            }
            put(key,get(key) + 1);
        }
    }


    private static void petCounter(C08PetCreator creator){
        Counter counter= new Counter();
        for(C02Pet pet : creator.petArray(20)){
            System.out.println(pet.getClass().getSimpleName());
            if(pet instanceof C02Pet){
                counter.count("C02Pet");
            }
            if(pet instanceof C03Dogs){
                counter.count("C03Dogs");
            }
            if(pet instanceof C04Mutt){
                counter.count("C04Mutt");
            }
            if(pet instanceof C05Pug){
                counter.count("C05Pug");
            }
            if(pet instanceof C06Cat){
                counter.count("C06Cat");
            }
            if(pet instanceof C07Manx){
                counter.count("C07Manx");
            }
        }
        System.out.println(counter);
    }

    public static void main(String[] args){
        petCounter(new C09ForNameCreator());
    }
}
