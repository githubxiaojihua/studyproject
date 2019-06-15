package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 知识点：
 * 1、不同set实现，对于存储元素的要求
 *
 * HashSet是无需的
 * TreeSet是按照Comparable的比较结果来排序的
 * LinkedHashSet是按照插入顺序来排序的
 */
public class C09TypesForSet {

    /**
     * 泛型实现填充set，避免代码重复
     * @param set
     * @param clazz
     * @param <T>
     */
    public static <T> void fill(Set<T> set, Class<T> clazz){
        try{
            for(int i=0; i<10; i++){
                //注意这个int.class的使用，如果换成Integer.class是会报错的，找不到对应的方法
                set.add(clazz.getDeclaredConstructor(int.class).newInstance(i));
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

    /**
     * 泛型实现，避免代码重复
     * @param set
     * @param clazz
     * @param <T>
     */
    public static <T> void test(Set<T> set, Class<T> clazz){
        //通过填充三次来观察不同的表现
        fill(set,clazz);
        fill(set,clazz);
        fill(set,clazz);
        System.out.println(set);
    }

    public static void main(String[] args){
        //使用对应的类型
        test(new HashSet<HashType>(), HashType.class);
        test(new LinkedHashSet<HashType>(), HashType.class);
        test(new TreeSet<TreeType>(), TreeType.class);

        //使用非对应的类型，会导致set性质不被满足，甚至是报错
        test(new HashSet<SetType>(), SetType.class);
        test(new HashSet<TreeType>(), TreeType.class);
        test(new LinkedHashSet<SetType>(), SetType.class);
        test(new LinkedHashSet<TreeType>(), TreeType.class);

        try {
            test(new TreeSet<SetType>(), SetType.class);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            test(new TreeSet<HashType>(), HashType.class);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

/**
 * 普通的set类型，只需要实现equals方法即可，
 * 因为set接口要求元素不能重复，因此必须有equals方法，
 * Object对象本身自带equals，但是在一些情况下需要
 * 重写，而且一般重写equals也必须要重写hashCode
 */
class SetType{
    public int i;
    public SetType(int i){
        this.i = i;
    }

    @Override
    public boolean equals(Object object){
        return (object instanceof SetType) ? ((SetType) object).i == i ? true : false : false;
    }
    @Override
    public String toString(){ return Integer.toString(i); }
}

/**
 * 应用于HashSet，LinkedHashSet的类型，
 * 必须要实现hashCode，因为以上两个set需要使用HashCode方法
 * 来定位元素
 */
class HashType extends SetType{
    public HashType(int i){
        super(i);
    }

    @Override
    public int hashCode(){
        return i;
    }
}

/**
 * 应用于TreeSet的类型，
 * 要求实现Comparable接口
 */
class TreeType extends SetType implements Comparable<TreeType>{
    public TreeType(int i){
        super(i);
    }

    @Override
    public int compareTo(TreeType o){
        //不要使用O.i - i，以为如果i为有符号int的时候容易溢出，比如i是一个比较大的负数，一减就相当于加正数了。
        return o.i < i ? -1 : (o.i == i ? 0 : 1);
    }
}
