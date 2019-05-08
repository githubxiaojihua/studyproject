package com.xiaojihua.javabasic.chapter17.hashCode;

import com.xiaojihua.javabasic.chapter17.C05Countries;
import java.util.*;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、使用list实现一个比较慢的Map，这是为了与HashMap进行对比
 * 2、再次演示了从AbstractMap实现自定义Map的方法，仅仅需要重写
 * get\put\以及entrySet方法即可，其他的方法都是现成的，且大部分
 * 都是基于entrySet
 * 3、Think in java中有原话是这样的：Although this solution
 * is very simple, and appears to work in the trivial
 * test in SlowMap.main( ), it is not a correct implementation
 * because a copy of the keys and values is made. A correct
 * implementation of entrySet( ) will provide a view into
 * the Map, rather than a copy, and this view will allow
 * modification of the original map (which a copy doesn’t).
 * 也就是说entrySet所返回的是Map中key和value的copy，但是正确的做法是返回view，并且通过
 * 此view可以直接修改底层的map数据。
 * 这里所说的copy实际上指的是返回的entrySet中装的是一个个新new的MapEntry对象，如果更改这个MapEntry对象
 * 的value，只是更改了对象本身的value，并没有对底层Map中对应的value。
 * 另外java中的参数传递方式为值传递，即使参数是对象引用，那传递的也是存储的值。
 * @param <K>
 * @param <V>
 */
public class C06SlowMap<K,V> extends AbstractMap<K,V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();

    /**
     * 注意get方法的参数是Object类型的
     * @param key
     * @return
     */
    @Override
    public V get(Object key){
        if(!keys.contains(key)){
            return null;
        }
        return values.get(keys.indexOf(key));
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
        return value;
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> entrySet = new HashSet<>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();
        while(ki.hasNext()){
            /*
             * C05MapEntry的构造函数参数（值传递），如果是对象（基本类型都是直接传递的值，对象类型传递的是对象
             * 引用所存储的实际对象内存地址的值）传递的是，
             */
            entrySet.add(new C05MapEntry<>(ki.next(),vi.next()));
        }
        return entrySet;
    }

    public static void main(String[] args){
        C06SlowMap<String,String> slowMap = new C06SlowMap<>();
        slowMap.putAll(C05Countries.capital(15));
        print(slowMap);
        print(slowMap.get("CHAD"));
        print(slowMap.entrySet());
    }
}
