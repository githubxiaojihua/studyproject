package com.xiaojihua.javabasic.java8test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、函数型接口，就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
 * 2、Predicate <T> 接口是一个函数式接口，它接受一个输入参数 T，返回一个布尔值结果，
 * 该接口用于测试对象是 true 或 false。而可以使用lamda表达式来作为它的一个实现。
 */
public class C03FunctionalInterface {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        print("输出所有数：");
        //根据Predicated函数接口的，test方法的签名规则，采用lambda表达式实现
        eval(list,n -> true);

        print("输出所有偶数：");
        //根据Predicated函数接口的，test方法的签名规则，采用lambda表达式实现
        eval(list, n -> n%2==0);

        print("输出所有大于3的数：");
        //根据Predicated函数接口的，test方法的签名规则，采用lambda表达式实现
        eval(list, n -> n > 3);

    }

    /**
     * 使用函数型接口编程
     * @param list
     * @param predicate
     */
    public static void eval(List<Integer> list, Predicate<Integer> predicate){
        for(Integer integer : list){
            if(predicate.test(integer)){
                print(integer + " ");
            }
        }
    }
}
