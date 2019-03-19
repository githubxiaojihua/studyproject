package com.xiaojihua.javabasic.chapter15.generalPurposeGenerator;

import com.xiaojihua.javabasic.chapter15.C04Generate;

/**
 * 知识点：
 * 1、在类上使用类型参数，以及在同时使用泛型方法。
 * 2、此类是一个通用的生成器，通过接收Class参数来创建实例
 * @param <T>
 */
public class C01BasicGenerator<T> implements C04Generate<T> {
    private Class<T> type;
    public C01BasicGenerator(Class<T> clazz){
        type = clazz;
    }

    @Override
    public T next(){
        try{
            //通过newInstance生成实例，type对应的类型应该是public的，并且有默认的构造方法
            return type.newInstance();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过一个静态方法生成一个生成器实例
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> C01BasicGenerator<T> create(Class<T> clazz){
        return new C01BasicGenerator<>(clazz);
    }
}
