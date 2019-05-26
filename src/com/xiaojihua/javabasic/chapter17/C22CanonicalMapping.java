package com.xiaojihua.javabasic.chapter17;

import java.util.WeakHashMap;

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

        System.gc();

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