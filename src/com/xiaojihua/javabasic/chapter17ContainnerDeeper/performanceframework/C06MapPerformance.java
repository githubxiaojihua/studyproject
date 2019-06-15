package com.xiaojihua.javabasic.chapter17ContainnerDeeper.performanceframework;

import java.util.*;

/**
 * 知识点：
 * 性能测试框架的使用
 * 1、对于HashMap,TreeMap,LinkedHashMap,IdentityHashMap,WeakHashMap,HashTable进行性能
 * 测试。在编程的时候首选HashMap，TreeMap是进行有序存储，IdentityHashMap是通过==而非equals来
 * 比较相等，HashTable（代码里面用了synchironized）是遗留代码用HashMap代替，
 */
public class C06MapPerformance {
    private static List<C01Test<Map<Integer,Integer>>> tests = new ArrayList<>();
    static{
        tests.add(new C01Test<Map<Integer, Integer>>("put") {
            @Override
            int test(Map<Integer, Integer> containner, C02TestParam param) {
                int size = param.size;
                int loop = param.loop;
                for(int i=0; i<loop; i++){
                    for(int j=0; j<size; j++){
                        containner.put(j,j);
                    }
                }
                return size * loop;
            }
        });

        tests.add(new C01Test<Map<Integer, Integer>>("get") {
            @Override
            int test(Map<Integer, Integer> containner, C02TestParam param) {
                int loop = param.loop;
                int size = param.size * 2;
                for(int i=0; i<loop; i++){
                    for(int j=0; j<size; j++){
                        containner.get(j);
                    }
                }
                return loop * size;
            }
        });

        tests.add(new C01Test<Map<Integer, Integer>>("iterable") {
            @Override
            int test(Map<Integer, Integer> containner, C02TestParam param) {
                int loop = param.loop * 10;
                for(int i=0; i<loop; i++){
                    Iterator it = containner.entrySet().iterator();
                    while(it.hasNext()){
                        it.next();
                    }
                }
                return loop * containner.size();
            }
        });
    }

    public static void main(String[] args){
        C03Tester.run(new TreeMap<>(),tests);
        C03Tester.run(new HashMap<>(),tests);
        C03Tester.run(new LinkedHashMap<>(),tests);
        //C03Tester.run(new IdentityHashMap<>(),tests);//这个貌似有点问题，耗时太长
        C03Tester.run(new WeakHashMap<>(),tests);
        C03Tester.run(new Hashtable<>(),tests);
    }

}
