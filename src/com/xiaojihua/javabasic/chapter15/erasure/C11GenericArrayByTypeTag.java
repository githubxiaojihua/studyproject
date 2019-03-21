package com.xiaojihua.javabasic.chapter15.erasure;

import java.lang.reflect.Array;

/**
 * 知识点：
 * 通过使用type Tag来实现 rep()方法返回具体的参数类型。
 * 本例需要结合
 * @see C10GenericArray
 * 来看在C10GenericArray中rep方法只能返回Object类型的数组
 * 无法返回Integer类型的数组
 * 但是通过本例可以返回Integer类型的数组
 *
 * type tag 实际上就是一个辅助字段（Class类型的）
 * @param <T>
 */
public class C11GenericArrayByTypeTag<T> {
    private T[] t;

    /**
     * 通过type tag ，保存传入的具体类型参数class，
     * 通过Array.newInstance生成数组，这也是推荐的方式
     * @param typeTag
     * @param size
     */
    public C11GenericArrayByTypeTag(Class<T> typeTag, int size){
        //通过Class<T>相当于恢复了对T的擦除
        t = (T[])Array.newInstance(typeTag, size);
    }

    public void put(T item, int index){
        t[index] = item;
    }

    public T get(int index){
        return t[index];
    }

    public T[] rep(){
        return t;
    }

    public static void main(String[] args){
        C11GenericArrayByTypeTag<Integer> c11 = new C11GenericArrayByTypeTag<>(Integer.class,10);
        //通过使用type tag可以做到rep方法返回具体的类型
        Integer[] intArr = c11.rep();
        Integer one = c11.get(0);

    }
}
