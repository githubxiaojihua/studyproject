package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.WeakHashMap;

/**
 * 知识点：
 * 1、WeakHashMap允许在持有key的同时允许GC进行回收key，
 * 如果key是强可到达的，比如本例使用了额外的数组存储key为3的倍数的key对象
 * 那么这些key对象将不会被GC回收。比如本例中之后会回收非3倍数的key对象，调用
 * 其finallize方法。
 * GC回收对象有个顺序：made finalizable, finalized, and then reclaimed.
 */
public class C22CanonicalMapping {
    public static void main(String[] args){
        WeakHashMap<Key,Value> weakHashMap = new WeakHashMap<>();
        int size = 10000;
        Key[] keys = new Key[size];
        for(int i=0; i<size; i++){
            Key key = new Key(Integer.toString(i));
            Value value = new Value(Integer.toString(i));
            if(i % 3 == 0){
                keys[i] = key;
            }
            weakHashMap.put(key,value);
        }
        System.out.println(weakHashMap.get(new Key(Integer.toString(4))));
        System.gc();
        weakHashMap.put(new Key(Integer.toString(4)),new Value(Integer.toString(4)));
        System.out.println(weakHashMap.get(new Key(Integer.toString(4))));

    }
}

class Element{
    private String ident;
    public Element(String ident){
        this.ident = ident;
    }

    @Override
    public String toString(){
        return ident;
    }

    @Override
    public int hashCode(){
        return ident.hashCode();
    }

    @Override
    public boolean equals(Object r){
        return (r instanceof Element) && (((Element) r).ident.equals(this.ident));
    }

    @Override
    public void finalize(){
        System.out.println("Finaling:" + getClass().getSimpleName() + " " + ident);
    }

}

class Key extends Element{
    public Key(String ident){
        super(ident);
    }
}

class Value extends Element{
    public Value(String ident){
        super(ident);
    }
}