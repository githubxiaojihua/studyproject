package com.xiaojihua.javabasic.chapter16array;

/**
 * 泛型数组的使用
 * @param <T>
 */
public class C06ArrayOfGeneric<T> {
    T[] arrayT;
    public C06ArrayOfGeneric(int size){
        //这种是不合法的
        //arrayT = new T[size];
        //创建泛型数组
        arrayT = (T[])new Object[size];
    }

    //下面的也是不合法的
    /*public <U> U[] makerArray(){
        return new U[10];
    }*/
}
