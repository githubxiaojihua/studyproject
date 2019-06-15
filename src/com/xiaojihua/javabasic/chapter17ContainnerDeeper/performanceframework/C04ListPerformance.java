package com.xiaojihua.javabasic.chapter17ContainnerDeeper.performanceframework;

import com.xiaojihua.javabasic.chapter16array.C08CounttingGenerator;
import com.xiaojihua.javabasic.chapter16array.C10Generated;
import com.xiaojihua.javabasic.util.CountingIntegerList;

import java.util.*;

/**
 * 使用性能测试框架，进行测试，主要看测试框架的结构和使用。因为其综合了设计模式。
 * 性能测试的结论：
 * 1、在进行随机访问的时候（get,set测试）ArrayList以及Arrays.asList的速度比较高，而且即使是
 * 较大数量起伏也不大。但是linkedList在进行随机访问的时候比较慢，而且对于较大数量时间起伏比较大。
 * 2、当进行基于ListIterator进行对于在中间位置插入操作时（iteradd测试），可以发现linkedList
 * 相对ArrayList比较快，对于较大数量的起伏比较稳定。
 * 3、非末尾的插入和删除（insert，remove测试）。
 */
public class C04ListPerformance {
    static Random rand = new Random();
    static int reps = 1000;

    static List<C01Test<List<Integer>>> tests =
            new ArrayList<C01Test<List<Integer>>>();
    static List<C01Test<LinkedList<Integer>>> qTests =
            new ArrayList<C01Test<LinkedList<Integer>>>();
    /*
       使用静态代码，来初始化tests，每个test对象都通过匿名内部类来实现
     */
    static {
        tests.add(new C01Test<List<Integer>>("add") {
            int test(List<Integer> list, C02TestParam tp) {
                int loops = tp.loop;
                int listSize = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < listSize; j++)
                        list.add(j);
                }
                return loops * listSize;
            }
        });
        tests.add(new C01Test<List<Integer>>("get") {
            int test(List<Integer> list, C02TestParam tp) {
                int loops = tp.loop * reps;
                int listSize = list.size();
                for(int i = 0; i < loops; i++)
                    list.get(rand.nextInt(listSize));
                return loops;
            }
        });
        tests.add(new C01Test<List<Integer>>("set") {
            int test(List<Integer> list, C02TestParam tp) {
                int loops = tp.loop * reps;
                int listSize = list.size();
                for(int i = 0; i < loops; i++)
                    list.set(rand.nextInt(listSize), 47);
                return loops;
            }
        });
        tests.add(new C01Test<List<Integer>>("iteradd") {
            int test(List<Integer> list, C02TestParam tp) {
                final int LOOPS = 1000000;
                int half = list.size() / 2;
                ListIterator<Integer> it = list.listIterator(half);
                for(int i = 0; i < LOOPS; i++)
                    it.add(47);
                return LOOPS;
            }
        });
        tests.add(new C01Test<List<Integer>>("insert") {
            int test(List<Integer> list, C02TestParam tp) {
                int loops = tp.loop;
                for(int i = 0; i < loops; i++)
                    list.add(5, 47); // Minimize random-access cost
                return loops;
            }
        });
        tests.add(new C01Test<List<Integer>>("remove") {
            int test(List<Integer> list, C02TestParam tp) {
                int loops = tp.loop;
                int size = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while(list.size() > 5)
                        list.remove(5); // Minimize random-access cost
                }
                return loops * size;
            }
        });
        // Tests for queue behavior:
        qTests.add(new C01Test<LinkedList<Integer>>("addFirst") {
            int test(LinkedList<Integer> list, C02TestParam tp) {
                int loops = tp.loop;
                int size = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < size; j++)
                        list.addFirst(47);
                }
                return loops * size;
            }
        });
        qTests.add(new C01Test<LinkedList<Integer>>("addLast") {
            int test(LinkedList<Integer> list, C02TestParam tp) {
                int loops = tp.loop;
                int size = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    for(int j = 0; j < size; j++)
                        list.addLast(47);
                }
                return loops * size;
            }
        });
        qTests.add(
                new C01Test<LinkedList<Integer>>("rmFirst") {
                    int test(LinkedList<Integer> list, C02TestParam tp) {
                        int loops = tp.loop;
                        int size = tp.size;
                        for(int i = 0; i < loops; i++) {
                            list.clear();
                            list.addAll(new CountingIntegerList(size));
                            while(list.size() > 0)
                                list.removeFirst();
                        }
                        return loops * size;
                    }
                });
        qTests.add(new C01Test<LinkedList<Integer>>("rmLast") {
            int test(LinkedList<Integer> list, C02TestParam tp) {
                int loops = tp.loop;
                int size = tp.size;
                for(int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while(list.size() > 0)
                        list.removeLast();
                }
                return loops * size;
            }
        });
    }

    /**
     * 通过集成C03Tester，覆盖initialize方法，用于重新设置获取容器的逻辑
     */
    static class ListTester extends C03Tester<List<Integer>> {
        public ListTester(List<Integer> container,
                          List<C01Test<List<Integer>>> tests) {
            super(container, tests);
        }
        // Fill to the appropriate size before each test:
        @Override
        public List<Integer> initialize(int size){
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }

        /**
         * 要使用本类的initialize方法必须，重新定义本类的run方法
         * 如果使用父类的run方法，那么返回的实际上是父类，那么调用的initiallize
         * 方法也是父类的了。本类的initialize方法的重写就没有作用了
         * @param list
         * @param tests
         */
        public static void run(List<Integer> list,
                               List<C01Test<List<Integer>>> tests) {
            new ListTester(list, tests).timedTest();
        }
    }
    public static void main(String[] args) {
        if(args.length > 0)
            C03Tester.defaultParams = C02TestParam.array(args);
        /*
            基于Arrays.asList方法生成的List由于是不能改变尺寸的，否则会报
            UnsupportedOperationException。因为asList方法返回的是实现了
            AbstractList的内部类，而此内部类并没有实现相关改变尺寸的方法。

            因此这样的list只能进行第1和2两项测试
         */
        C03Tester<List<Integer>> arrayTest =
                //containner可以为null，通过initialize来设置
                new C03Tester<List<Integer>>(null, tests.subList(1, 3)){
                    // This will be called before each test. It
                    // produces a non-resizeable array-backed list:
                    @Override
                    public List<Integer> initialize(int size) {
                        Integer[] ia = C10Generated.array(Integer.class,
                                new C08CounttingGenerator.Integer(), size);
                        return Arrays.asList(ia);
                    }

                };
        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        C03Tester.defaultParams= C02TestParam.array(
                10, 5000, 100, 5000, 1000, 1000, 10000, 200);
        if(args.length > 0)
            C03Tester.defaultParams = C02TestParam.array(args);

        ListTester.run(new ArrayList<Integer>(), tests);
        ListTester.run(new LinkedList<Integer>(), tests);
        ListTester.run(new Vector<Integer>(), tests);

        C03Tester.fieldWidth = 12;
        C03Tester<LinkedList<Integer>> qTest =
                new C03Tester<LinkedList<Integer>>(
                        new LinkedList<Integer>(), qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();
    }
}
