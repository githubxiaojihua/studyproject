package com.xiaojihua.javabasic.util;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 工具类，一个任意长度的Map.
 * 不能对其做set操作，因为没有实现，这也是从AbstractMap实现的好处
 * 可以选择性实现写方法，如果是只读的那么，则按照如下方式实现
 * setValue方法即可。
 */
public class C13CounttingMapData extends AbstractMap<Integer,String> {
    private static String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
    private int size;
    public C13CounttingMapData(int size){
        if(size < 0){
            this.size = 0;
        }
        this.size = size;
    }

    /**
     * 嵌套类实现Entry
     */
    public static class Entry implements Map.Entry<Integer,String>{
        int index;
        public Entry(int index){
            this.index = index;
        }
        @Override
        public Integer getKey(){
            return Integer.valueOf(index);
        }

        @Override
        public String getValue(){
            return chars[index%chars.length] + Integer.toString(index/chars.length);
        }

        /**
         * map中的key是跟set属性一直的，因为map不允许
         * key重复，因此判断Entry是否相等就判断key是否相等
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o){
            return Integer.valueOf(index).equals(o);
        }

        @Override
        public int hashCode(){
            return Integer.valueOf(index).hashCode();
        }

        @Override
        public String setValue(String value){
            throw new UnsupportedOperationException();
        }

    }

    /**
     * 实现AbstractMap要求的方法
     * @return
     */
    @Override
    public Set<Map.Entry<Integer,String>> entrySet(){
        Set<Map.Entry<Integer,String>> entries = new LinkedHashSet<>();
        for(int i=0; i<size; i++){
            entries.add(new Entry(i));
        }
        return entries;
    }

    public static void main(String[] args){
        System.out.println(new C13CounttingMapData(60));
    }
}
