package com.xiaojihua.javabasic.chapter17.performanceframework;

import java.util.List;

/**
 * 本测试框架中的具体测试类，具体使用不同C01Test对容器进行测试。
 * 组合使用C01Test和C02TestParam，进行性能测试
 * @param <C>
 */
public class C03Tester<C> {
    //输出信息的默认列宽
    public static int fieldWidth = 8;
    //默认的测试参数
    public static C02TestParam[] defaultParams = C02TestParam.array(
            10,5000,100,5000,1000,5000,10000,500
    );

    public C container;

    /**
     * 子类可以通过重写此方法来自定义测试容器，
     * 在timedTest()方法方法中，会调用此方法，并以此方法的返回值作为
     * 实际的测试对象。
     * @param size
     * @return
     */
    public C initialize(int size){
        return container;
    }

    private String headLine = "";
    //测试类列表
    private List<C01Test<C>> tests;

    private static String stringField(){
        return "%" + fieldWidth + "s";
    }
    private static String numberField(){
        return "%" + fieldWidth + "d";
    }

    private static int sizeWidth = 5;
    private static String sizeField = "%" + sizeWidth +"s";

    //实际用于测试的参数类列表
    private C02TestParam[] paramList = defaultParams;

    public C03Tester(C containner, List<C01Test<C>> tests){
        this.container = containner;
        this.tests = tests;
        if(containner != null){
            this.headLine = containner.getClass().getSimpleName();
        }
    }

    public C03Tester(C containner, List<C01Test<C>> tests, C02TestParam[] paramList){
        this(containner, tests);
        this.paramList = paramList;
    }

    public void setHeadline(String newHeadline) {
        headLine = newHeadline;
    }

    private void displayHeader(){
        int width = fieldWidth * tests.size() + sizeWidth;
        int dashLength = width - headLine.length() + 1;
        StringBuilder header = new StringBuilder(width);
        for(int i=0; i<dashLength/2; i++){
            header.append("-");
        }
        header.append(" ");
        header.append(headLine);
        header.append(" ");
        for(int i=0; i<dashLength/2; i++){
            header.append("-");
        }

        System.out.println(header.toString());
        System.out.format(sizeField,"size");
        for(C01Test test : tests){
            System.out.format(stringField(), test.name);
        }

        System.out.println();

    }

    /**
     * 使用不同的参数来调用
     * test.test方法。
     */
    public void timedTest(){
        displayHeader();
        for(C02TestParam param : paramList){
            System.out.format(sizeField,param.size);
            for(C01Test test : tests){
                C kcontainner = initialize(param.size);
                long start = System.nanoTime();
                int reps = test.test(kcontainner,param);
                long duaration = System.nanoTime() - start;
                //调用不同的模版方法
                long timePerRep = duaration/reps;
                System.out.format(numberField(), timePerRep);
            }
            System.out.println();
        }
    }

    /**
     * 使用C03Testter.timedTest方法的简单途径。
     * 通过使用静态泛型方法，减少本类在初始化时候的键入量。
     * @param containner
     * @param tests
     * @param <C>
     */
    public static <C> void run(C containner, List<C01Test<C>> tests){
        new C03Tester<>(containner,tests).timedTest();
    }

    public static <C> void run(C containner, List<C01Test<C>> tests, C02TestParam[] paramList){
        new C03Tester<>(containner,tests,paramList).timedTest();
    }
}
