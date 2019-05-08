package com.xiaojihua.javabasic.chapter17.hashCode;

import java.util.*;

/**
 * 知识点：
 * 1、改进C06SlowMap。当对C06SlowMap使用C13Maps的test方法的时候，
 * 其中对于clear和remove方法的调用是没有效果的。因为entrySet()方法
 * 所存储的是key和value的copy，并没有直接指向底层的key和value 的
 * List，而clear和remove底层都调用了entrySet()返回的EntrySet了的
 * Iterator的remove方法，因此无法更改底层的数据。通过改进后就可以对
 * 底层的List进行更改。能通过test方法。
 * @param <K>
 * @param <V>
 */
public class C07SlowMap2<K,V> extends AbstractMap<K,V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();

    @Override
    public V get(Object o){
        if(!keys.contains(o)){
            return null;
        }else{
            return values.get(keys.indexOf(o));
        }
    }

    @Override
    public V put(K key, V value){
        V oldValue = get(key);
        if(!keys.contains(key)){
            keys.add(key);
            values.add(value);
        }else{
            values.set(keys.indexOf(key),value);
        }
        return oldValue;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    private class MapEntry implements Map.Entry<K,V>{
        public int index;
        public MapEntry(int index){
            if(index >= keys.size()){
                index = keys.size() - 1;
            }
            this.index = index;
        }



        @Override
        public K getKey(){
            return keys.get(index);
        }

        @Override
        public V setValue(V value){
            V oldValue = values.get(index);
            values.set(index, value);
            return oldValue;
        }

        @Override
        public V getValue(){
            return values.get(index);
        }

    }

    public class EntrySet extends AbstractSet<Map.Entry<K,V>>{

        @Override
        public int size(){
            return keys.size();
        }

        @Override
        public Iterator<Map.Entry<K,V>> iterator(){
            return new Iterator<Map.Entry<K,V>>() {
                private boolean modified = false;
                MapEntry mapEntry = new MapEntry(-1);

                @Override
                public boolean hasNext() {
                    return mapEntry.index < size() - 1;
                }

                @Override
                public MapEntry next() {

                    if( hasNext()){
                       mapEntry.index++;
                    }
                    modified = false;
                    return mapEntry;
                }

                @Override
                public void remove(){
                    if(!modified){
                        keys.remove(mapEntry.index);
                        values.remove(mapEntry.index);
                    }
                    //remove完成以后一定要回退一格，因为底层是通过List实现的
                    //remove一个元素以后后面的元素会前移，但是此时index已经+1了
                    //所以就会导致前移这个删除不了，从而导致隔一个删一个
                    mapEntry.index--;
                    modified = true;
                }
            };
        }



    }
}
