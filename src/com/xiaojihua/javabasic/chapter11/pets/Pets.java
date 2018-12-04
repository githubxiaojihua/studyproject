package com.xiaojihua.javabasic.chapter11.pets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 工具：
 * 生成指定数量的pet 列表
 * 知识点：
 * 借鉴了生成器模式
 * H:\code_80968\studyproject\src\com\xiaojihua\javabasic\genericity\coffee\CoffeeGenerator.java
 */
public class Pets {
    private static Random random = new Random(47);
    private static Class[] types = {Cymirc.class,Hamster.class,Manx.class,Mouse.class,Mutt.class,Pug.class,Rat.class};

    public static List<Pet> arrayList(int num)  {
        List<Pet> pets = new ArrayList<>();
        for(int i = 0; i < num ; i++){
            try {
                pets.add(next());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return pets;
    }

    public static Pet next() throws IllegalAccessException, InstantiationException {
        return (Pet) types[random.nextInt(types.length)].newInstance();
    }
}
