package com.xiaojihua.javabasic.chapter14typeinfo.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 模板设计模式中的模板类
 * 除了抽象方法用于子类实现外，其他的均定义好
 *
 * 作用：pet生成器
 */
public abstract class C08PetCreator {
    private Random random = new Random(47);
    public abstract List<Class<? extends C02Pet>> types();//模版方法

    /**
     * 随机生成一个pet
     * @return
     */
    public C02Pet randomPet(){
        C02Pet pet = null;
        try {
            pet = types().get(random.nextInt(types().size())).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return pet;
    }

    /**
     * 生成pet数组
     * @param size
     * @return
     */
    public C02Pet[] petArray(int size){
        C02Pet[] petArray = new C02Pet[size];
        for(int i = 0; i < size; i++){
            petArray[i] = randomPet();
        }
        return petArray;
    }

    /**
     * 生成pet列表
     * @param size
     * @return
     */
    public List<C02Pet> petList(int size){
        List<C02Pet> pl = new ArrayList<C02Pet>();
        Collections.addAll(pl, petArray(size));
        return pl;
    }
}
