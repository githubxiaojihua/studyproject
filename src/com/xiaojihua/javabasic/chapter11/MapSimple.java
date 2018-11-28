package com.xiaojihua.javabasic.chapter11;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：hashMap的基本操作
 */
public class MapSimple {
    public static void main(String[] args) {
        Random rand = new Random(47);
        Map<Integer,Integer> m = new HashMap<Integer,Integer>();
        for(int i = 0; i < 1000 ; i++){
            int r = rand.nextInt(20);
            Integer integer = m.get(r);
            m.put(r , integer == null ? 1 : integer + 1);
        }
        print(m.toString());
        print(m.containsKey(1));//是否包含key
        print(m.containsValue(2));//是否包含value
        print(m.keySet());//返回key的set
        print(m.values());//返回value的Collection
        print(m.entrySet());
        //利用entryset来遍历map，entryset也是一个set，是一个键值对的set
        for(Map.Entry<Integer,Integer> entry : m.entrySet()){
            print(entry.getKey() + ":" + entry.getValue());
        }
    }
}
