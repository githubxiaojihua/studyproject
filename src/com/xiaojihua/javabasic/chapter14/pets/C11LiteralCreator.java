package com.xiaojihua.javabasic.chapter14.pets;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 知识点：
 * 1、C08PetCreator模版类的第二个子类，通过使用字面量来实现types()
 * 2、Collections.unmodifiableList()方法的使用，返回一个目标list的
 * 一个不可修改的视图引用，对此引用可以进行查询，但是不能进行修改，如果
 * 进行过修改的话就会产生UnsupportedOperationException异常
 * 3、Arrays.asList(T ... a)的使用
 */
public class C11LiteralCreator extends C08PetCreator {

    //返回一个不可修改的list
    public static final List<Class<? extends C02Pet>> allTypes =
            Collections.unmodifiableList(Arrays.asList(C02Pet.class,
                    C03Dogs.class,C04Mutt.class,C05Pug.class,C06Cat.class,C07Manx.class));

    //返回子types
    public static List<Class<? extends C02Pet>> types = allTypes.subList(allTypes.indexOf(C04Mutt.class),allTypes.size());


    @Override
    public List<Class<? extends C02Pet>> types() {
        //allTypes.add(null);
        return types;
    }

    /*public static void main(String[] args) {
        C11LiteralCreator l = new C11LiteralCreator();
        l.types();
    }*/


}
