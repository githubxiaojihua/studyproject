package com.xiaojihua.javabasic.chapter21thread.shareResource;

/**
 * 用于存储生成的serialNumber并且检测是否存在重复生成。
 *  * 为什么要单独抽象出这么一个类来是值得思考的，
 *  * 这里实践了高内聚低耦合的设计思想
 */
public class C05SerialNumberCheck {
    private int[] array;
    private int index = 0;

    public C05SerialNumberCheck(int initSize){
        array = new int[initSize];
        for(int i=0; i<initSize; i++){
            array[i] = -1;
        }
    }

    synchronized public void add(int num){
        array[index] = num;
        index = ++index % array.length;
    }

    synchronized public boolean contains(int num){
        for(int i : array){
            if(i == num){
                return true;
            }
        }
        return false;
    }
}
