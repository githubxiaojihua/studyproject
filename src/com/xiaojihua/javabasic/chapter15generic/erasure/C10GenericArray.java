package com.xiaojihua.javabasic.chapter15generic.erasure;

/**
 * 知识点：
 * 1、通用泛型数组的使用。T[]
 *
 * 关于向下转型的知识点：
 * 1、子类对象可以视为父类对象，比如一个方法以父类对象为形参，
 * 那么可以传递子类对象为实参。
 * 2、父类对象不能作为子类对象。一个父类对象不能强制类型转换
 * 成子类对象，除非这个父类对象的引用本身就指向了一个子类对象。
 * @param <T>
 */
public class C10GenericArray<T> {
    private T[] ts;
    public C10GenericArray(int size){
        ts = (T[])new Object[size];
    }

    public void put(int index, T item){
        ts[index] = item;
    }

    public T get(int index){
        return ts[index];
    }

    /**
     * 实际调用的时候返回的Object[]
     * 因为T被擦除到了Object
     * @return
     */
    public T[] rep(){
        return ts;
    }

    public static void main(String[] args){
        C10GenericArray<Integer> genericArray = new C10GenericArray<>(10);

        /**
         * 不能强制类型转换为Integer[]
         * rep方法返回的是Object[]
         *
         * 但是通过type tag方式可以直接返回Integer
         * @see C11GenericArrayByTypeTag
         */
        Object[] objs = genericArray.rep();

        /**
         * get方法返回的是Integer，因为编译器自动插入了类型转换的代码，
         * 这是“在边界处的动作”中的知识.
         *
         * get是对象离开方法时候的边界。
         * set是对象进入方法时候的边界。
         * @see C06GenericHolder
         */
        Integer i = genericArray.get(0);

        /**
         * 父类对象不能强制类型转化为子类对象
         */
        Father f = new Father();
        Son s = (Son)f;

        /**
         * 当父类对象实际指向一个子类对象的时候
         * 是可以进行强制类型转化的，
         * 因为fs指向的就是Son类型的一块内存区域
         */
        Father fs = new Son();
        Son ss = (Son)fs;
    }
}

class Father{}
class Son extends Father{
}
