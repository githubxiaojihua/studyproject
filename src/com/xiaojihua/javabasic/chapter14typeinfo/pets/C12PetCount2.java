package com.xiaojihua.javabasic.chapter14typeinfo.pets;

import com.xiaojihua.javabasic.util.MapData;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 知识点：
 * 1、动态的instanceof的用法
 * 2、嵌套类集成LinkedHashMap的使用访法
 * 3、MapData类的使用：是一个自定义的工具类，继承了LinkedHashMap，并可以通过不同的
 * map方法来初始化，并且返回实例引用
 */
public class C12PetCount2 {

    /**
     * 嵌套类实现PET计数器
     */
    public static class PetCounter extends LinkedHashMap<Class<? extends C02Pet>, Integer> {
        //初始化数据
        PetCounter(){
            super(MapData.map(C11LiteralCreator.allTypes,0));
        }

        //计数
        public void count(C02Pet pet){
            for(Map.Entry<Class<? extends C02Pet>,Integer> entry : entrySet()){
                if(entry.getKey().isInstance(pet)){
                    put(entry.getKey(),entry.getValue() + 1);
                }
            }
        }

        public String toString(){
            StringBuilder builder = new StringBuilder();
            for(Map.Entry<Class<? extends C02Pet>,Integer> entry : entrySet()){
                builder.append(entry.getKey().getSimpleName())
                        .append(":")
                        .append(entry.getValue())
                        .append(",");
            }
            return builder.toString();
        }
    }

    public static void main(String[] args){
        PetCounter counter = new PetCounter();
        for(C02Pet pet : new C11LiteralCreator().petList(20)){
            counter.count(pet);
        }
        System.out.println(counter);
    }
}
