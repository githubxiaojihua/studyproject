package com.xiaojihua.javabasic.chapter15;

import com.xiaojihua.javabasic.chapter14.pets.C02Pet;
import com.xiaojihua.javabasic.chapter14.pets.C03Dogs;
import com.xiaojihua.javabasic.chapter14.pets.C06Cat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 知识点：
 * 1、A checked container will throw a ClassCastException
 * at the point you try to insert an improper object, as
 * opposed to a pre-generic (raw) container which would
 * inform you that there was a problem when you pulled
 * the object out. In the latter case, you know there’s a
 * problem but you don’t know who the culprit is, but with
 * checked containers you find out who tried to insert
 * the bad object.。
 * 2、相关的静态方法有：
 * Collections.checkedCollection()
 * Collections.checkedList()
 * Collections.checkedMap()
 * Collections.checkedSet()
 * Collections.checkedSortedMap()
 * Collections.checkedSortedSet()
 */
public class C26CheckedList {


    @SuppressWarnings("unchecked")
    private static void oldStyleMethod(List petList){
        petList.add(new C06Cat());
    }

    public static void main(String[] args){
        List<C03Dogs> dogs = new ArrayList<>();

        /*
         * 非checkedlist在插入不兼容类型的时候不会提示有问题，也不抛出错误，在从list中获取时才出错
         * 在这种情况下当出错后要定位错误是困难的
         */
        oldStyleMethod(dogs);
        List<C03Dogs> checkedDogs = Collections.checkedList(new ArrayList<>(),C03Dogs.class);
        try{
            //checkedlist在插入不兼容类型的时候就会抛出错误。很容易知道错误的原因
            oldStyleMethod(checkedDogs);
        }catch(ClassCastException e){
            System.out.println("ClassCastException");
        }

        /*
         * checkedlist对导出类支持良好
         */
        List<C02Pet> pets = Collections.checkedList(new ArrayList<>(), C02Pet.class);
        pets.add(new C03Dogs());
        pets.add(new C06Cat());



    }
}
