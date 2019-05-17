package com.xiaojihua.javabasic.chapter17.performanceframework;

import java.util.*;

/**
 * 知识点：
 * 性能测试框架的使用
 * 1、HashSet、TreeSet、LinkedHashSet的性能比较
 * 2、HashSet的速度比TreeSet快很多，尤其实在插入和查找方面，
 * TreeSet的作用在于他的元素是有序的。但是在遍历的时候（iterate）
 * TreeSet的速度略好与HashSet.LinkedHash表现比较适中，他保留了
 * 插入顺序。
 */
public class C05SetPerformance {
    private static List<C01Test<Set<Integer>>> testList = new ArrayList<>();
    static{
        testList.add(new C01Test<Set<Integer>>("add") {
            @Override
            int test(Set<Integer> containner, C02TestParam param) {
                int size = param.size;
                int loops = param.loop;
                for(int i=0; i<loops; i++){
                    containner.clear();
                    for(int j=0; j<size; j++){
                        containner.add(j);
                    }
                }
                return size * loops;
            }
        });

        testList.add(new C01Test<Set<Integer>>("contains") {
            @Override
            int test(Set<Integer> containner, C02TestParam param) {
                int size = param.size * 2;
                int loops = param.loop;
                for(int i=0; i<loops; i++){
                    for(int j=0; j<size; j++){
                        containner.contains(j);
                    }
                }
                return size* loops;
            }
        });

        testList.add(new C01Test<Set<Integer>>("iterate") {
            @Override
            int test(Set<Integer> containner, C02TestParam param) {
                int loops = param.loop * 2;
                for(int i=0; i<loops; i++){
                    Iterator<Integer> it = containner.iterator();
                    while(it.hasNext()){
                        it.next();
                    }
                }
                return loops * containner.size();
            }
        });
    }

    public static void main(String[] args){
        C03Tester.fieldWidth = 10;
        C03Tester.run(new TreeSet<>(), testList);
        C03Tester.run(new HashSet<>(), testList);
        C03Tester.run(new LinkedHashSet<>(), testList);

    }
}
