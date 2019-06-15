package com.xiaojihua.javabasic.chapter17ContainnerDeeper.hashCode;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 知识点：
 * 1、hashcode的使用。C01GrandHog没有实现自己的hashcode（采用默认从Object集成过来的，但是
 * Object中的hashcode方法是为每一个对象都生成了一个不重复的code，默认是对象的地址），
 * 因此作为Key存储到HashMap中的时候，每一个C01GrandHog都有自己的hashcode，而HashMap就是
 * 通过hashcode来实现高效的插入和检索的，因此如果在构造一个同样的C01GrandHog对象，仍然是
 * 找不到对应的value的，这一点可以从输出看到。
 * 2、如果要用自定义类作为HashMap或者LinkedHashMap的key那么必须同时重写hashCode()和equals()方法
 * 因为要判定key是否相等就需要equals方法。而默认的Object中的equals方法是比较两个对象的地址。
 */
public class C03SpringDetector {
    public static <T extends C01GrandHog> void test(Class<T> type) throws Exception{
        Map<C01GrandHog,C02Pretication> map = new HashMap<>();
        Constructor<T> con = type.getConstructor(int.class);
        for(int i=0; i<10; i++){
            map.put(con.newInstance(i),new C02Pretication());
        }

        //从输出看到map已经被填满了
        System.out.println("Map:" + map);
        //构造了一个一模一样的对象
        C01GrandHog grandHog = con.newInstance(3);
        //在map中进行检索
        if(map.containsKey(grandHog)){
            System.out.println(map.get(grandHog));
        }else{
            System.out.println("Key not find:" + grandHog);
        }

    }

    public static void main(String[] args) throws Exception{
        //使用没有实现hashCode和equals方法的自定义类作为key，找不到key
        test(C01GrandHog.class);
        //使用实现了hashCode和equals方法的自定义类作为key，能找到key
        test(C04GrandHog2.class);
    }
}
