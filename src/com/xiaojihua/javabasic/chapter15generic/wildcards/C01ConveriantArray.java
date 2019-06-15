package com.xiaojihua.javabasic.chapter15generic.wildcards;

/**
 * 知识点：
 * 1、数组是协变的。
 * 2、fruits虽然是Fruit类型的数组，但是实际指向了Apple类型的内存区域，
 * 数组内部存储的也是Apple类型的元素。将数组元素赋值为Apple及其子类是
 * 没有问题的，但是赋值为Fruit或者是Oracle虽然编译器不报错（因为数组协变的）
 * ，但是在运行期间还是会报错(ArrayStoreException)。
 *
 *
 */
public class C01ConveriantArray {
    public static void main(String[] args){

        Fruit[] fruits = new Apple[10];
        fruits[0] = new Apple();//ok
        fruits[1] = new Jonthon();//ok

        try{
            fruits[3] = new Fruit();//ArrayStoreException
        }catch(Exception e){
            System.out.println(e);//如果不是trycatch的话，出现错误后程序就会终止，下面的语句不会执行了
        }

        try{
            fruits[4] = new Orange();//ArrayStoreException
        }catch(Exception e){
            System.out.println(e);
        }

        //这种方法也是协变的一种表示形式，可以运行，不会像上面一样报错
        //Fruit[] fruits = { new Fruit(), new Apple(), new Orange(), new Jonthon()};
    }
}

class Fruit{}
class Apple extends Fruit{}
class Jonthon extends Apple{}
class Orange extends Fruit{}
