package com.xiaojihua.javabasic.chapter14.pets;

import java.util.HashMap;
import java.util.Map;

public class C13TypeCounter extends HashMap<Class<?>,Integer> {
    private Class<?> baseType;
    public C13TypeCounter(Class<?> baseType){
        this.baseType = baseType;
    }

    public void typeCounts(Object obj){
        Class<?> type = obj.getClass();
        if(!baseType.isAssignableFrom(type)){
            throw new RuntimeException(obj + ",type:" + obj.getClass() +" is not " + baseType);
        }
        typeCount(type);
    }

    private void typeCount(Class<?> type){
        Integer value = get(type);
        put(type, value == null ? 1 : value + 1);
        Class<?> superClass = type.getSuperclass();
        if(baseType.isAssignableFrom(superClass)){
            typeCount(superClass);
        }
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<Class<?>,Integer> entry : entrySet()){
            builder.append(entry.getKey().getSimpleName())
                    .append(":")
                    .append(entry.getValue())
                    .append(",");
        }
        return builder.toString();
    }

}
