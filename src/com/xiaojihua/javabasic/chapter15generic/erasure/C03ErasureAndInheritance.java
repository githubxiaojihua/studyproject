package com.xiaojihua.javabasic.chapter15generic.erasure;

/**
 * 知识点：
 * 1、声明了泛型的类，在实际使用中也可以不使用泛型。
 * 2、如果不使用泛型那么将会擦除到Object
 * 3、@SuppressWarnings("unchecked")注解的使用，
 * 用来关闭警告，最好用在出现警告的方法上，而并不是
 * 整个类中，如果注销掉，那么将鼠标放到第21行会有小
 * 黄灯出现，代表警告，另外在本编辑区域右边的滚动条
 * 上也会多显示一条黄杠杠。
 */
public class C03ErasureAndInheritance {
    /**
     *
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args){
        //使用未使用泛型的子类，set和get操作的都是Object
        Driverd2 driverd2 = new Driverd2();
        Object obj = driverd2.getT();
        driverd2.setT(obj);

        //使用 应用了泛型的子类，set和get操作的都是实际指定的类型
        Driverd1<String> driverd1 = new Driverd1<>();
        String strObje = driverd1.getT();
        driverd1.setT(strObje);
    }
}

/**
 * 声明泛型类
 * @param <T>
 */
class GenericBase<T>{
    private T t;
    public T getT(){ return t; }
    public void setT(T tt){
        t = tt;
    }
}

/**
 * 泛型子类
 * @param <T>
 */
class Driverd1<T> extends GenericBase<T>{}

/**
 * 非泛型子类
 */
class Driverd2 extends GenericBase{}