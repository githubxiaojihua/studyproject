package com.xiaojihua.javabasic.chapter14typeinfo.pets;

public class C14CPetCount3 {
    public static void main(String[] args){
        C13TypeCounter typeCounter = new C13TypeCounter(C02Pet.class);
        for(C02Pet pet : new C11LiteralCreator().petList(20)){
            typeCounter.typeCounts(pet);
        }
        System.out.println(typeCounter);
    }
}
