package com.xiaojihua.javabasic.chapter15.erasure;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 知识点：
 * 1、泛型的擦除，在本例中虽然t是Class<T>的引用，但实际上只是Class的引用。
 * 2、@SuppressWarning("unchecked")的使用，用于消除makerArray方法的警
 * 告。另外也就是本编辑框右边的黄杠杠。
 * 3、Array.newInstance()的使用，这种方式是在泛型代码中生成数组的对剑方
 * 式。
 * @param <T>
 */
public class C04ArrayMaker<T> {
    private Class<T> t;
    public C04ArrayMaker(Class<T> type){
        t = type;
    }

    /**
     * 消除warning
     * @param size
     * @return
     */
    @SuppressWarnings("unchecked")
    public T[] makerArray(int size){
        //由于擦除机制，t实际上只是Class类型(也可以理解为Class<Object>)，因此Array.newInstance()返回的也只是Object
        return (T[])Array.newInstance(t, size);
    }

    public static void main(String[] args){
        C04ArrayMaker<String> am = new C04ArrayMaker<>(String.class);
        String[] strAm = am.makerArray(10);
        System.out.println(Arrays.toString(strAm));
    }

}

