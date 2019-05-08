package com.xiaojihua.javabasic.chapter17.hashCode;

import java.util.Map;

public class C05MapEntry<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;

    public C05MapEntry(K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey(){
        return key;
    }

    @Override
    public V getValue(){
        return value;
    }

    @Override
    public V setValue(V value){
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public int hashCode(){
        return ((key == null) ? 0 : key.hashCode()) ^
                ((value == null) ? 0 : value.hashCode());
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof C05MapEntry)){
            return false;
        }
        C05MapEntry entry = (C05MapEntry)o;
        return (key == null ? entry.getKey() == null : key.equals(entry.getKey())) &&
                (value == null ? entry.getValue() == null : value.equals(entry.getValue()));
    }

    @Override
    public String toString(){
        return key + "=" + value;
    }
}
