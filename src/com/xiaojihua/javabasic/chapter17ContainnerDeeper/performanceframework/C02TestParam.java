package com.xiaojihua.javabasic.chapter17ContainnerDeeper.performanceframework;

/**
 * 测试参数类
 */
public class C02TestParam {
    //通过使用public final，以及在构造函数中进行初始化的方法
    //来达到，private 并提供get和set方法的目的
    public final int size;//用于设置被测试容器的size
    public final int loop;//指定循环的次数
    public C02TestParam(int size, int loop){
        this.size = size;
        this.loop = loop;
    }

    /**
     * 通过一个int数组初始化本类的数组
     * @param args
     * @return
     */
    public static C02TestParam[] array(int ... args){
        int size = args.length / 2;
        C02TestParam[] array = new C02TestParam[size];
        for(int i=0; i<size; i++){
            array[i] = new C02TestParam(args[2*i], args[2*i+1]);
        }
        return array;
    }

    /**
     * 通过一个String[]初始化本类数组
     * @param strs
     * @return
     */
    public static C02TestParam[] array(String[] strs){
        int[] value = new int[strs.length];
        for(int i=0; i<value.length; i++){
            value[i] = Integer.decode(strs[i]);
        }
        return array(value);
    }
}
