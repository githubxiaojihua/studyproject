package com.xiaojihua.javabasic.chapter15;

/**
 * 知识点：
 * 1、一个类不能同时实现同一个泛型接口的不同类型参数，由于擦除机制这两个接口被认为是同一个接口。
 * 2、SubEmployee类编译不通过，因为擦出机制使得它继承而来的Payable<Employee>接口和直接实现的
 * Payable<SubEmployee>接口被认为是相同的，这样就相当于其implement了两次相同的接口。
 * 但是如果将类型参数去掉，却能编译通过
 */
public class C17Hourly  {
    public static void main(String[] args){

    }
}

interface Payable<T>{}

class Employee implements Payable<Employee>{}

/**
 * 由于擦除机制，被认为是重复实现了相同接口
 */
//class SubEmployee extends Employee implements Payable<SubEmployee>{}