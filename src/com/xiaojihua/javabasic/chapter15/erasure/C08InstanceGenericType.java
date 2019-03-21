package com.xiaojihua.javabasic.chapter15.erasure;

/**
 * 知识点：
 * 1、这个类要结合C07FactoryConstraint来看，实际上本类是使用Class作为了
 * 工厂方法，通过Class.newInstance()来创建实例，但是这要求被创建的实例必
 * 须有默认的构造方法。而C07FactoryConstraint使用的是通用的工厂设计模式。
 */
public class C08InstanceGenericType {
    public static void main(String[] args){
        ClassAsFactory<Employee> caf = new ClassAsFactory<>(Employee.class);
        System.out.println("ClassAsFactory<Empolyee> success!");
        try{
            //由于Integer类没有默认的构造方法因此会抛出异常
            ClassAsFactory<Integer> cafInt = new ClassAsFactory<>(Integer.class);
        }catch(Exception e){
            System.out.println("ClassAsFactory<Integer> failed!");
        }
    }
}

/**
 * 将class当作工厂类来使用
 * 构造方法中的Class<T> clazz也叫做type tag（类型标签）
 * 是为了进行泛型实例化而单独增加的一个变量，在此处是作为
 * 入参来使用的，也可以作为成员变量来使用。
 * 比如声明一个Class<T> clazz，然后赋值给他。
 * @param <T>
 */
class ClassAsFactory<T>{
    public T t;
    public ClassAsFactory(Class<T> clazz){
        try{
            t = clazz.newInstance();
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }
}

class Employee{}
