package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 知识点：
 * 当有两个对象引用同一个第三对象并且进行序列化的时候，反序列化后会使用同一个
 * 第三对象（内存地址相同），但是如果是通过不同的stream来序列化的话，那么将会
 * 是不同的对象。
 */
//@SuppressWarnings("unchecked")
public class C37MyWorld {
    public static void main(String[] args) throws Exception{
        List<Animal> animals = new ArrayList<>();
        House house = new House();
        animals.add(new Animal("a",house));
        animals.add(new Animal("b",house));
        animals.add(new Animal("c",house));
        //输出原始animals对象地址
        System.out.println(animals);


        //==========================序列化=========================================
        /*
            用同一个stream 连续写两次，这时候反序列化的时候共用一个house对象（内存地址相同）
            但是与原始animals不管是Animal还是house都是不同的，因为是通过反序列化重新
            建立的对象
         */
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(out1);
        outputStream.writeObject(animals);
        outputStream.writeObject(animals);
        outputStream.close();

        /*
            用不同的stream 会序列化和反序列化为与上面不同的Animal和House，但是
            House是共享的，是同一个（内存地址是一样的）
         */
        ByteArrayOutputStream ou2 = new ByteArrayOutputStream();
        outputStream = new ObjectOutputStream(ou2);
        outputStream.writeObject(animals);
        outputStream.close();


        //==========================反序列化=========================================
        ByteArrayInputStream in1 = new ByteArrayInputStream(out1.toByteArray());
        ObjectInputStream inputStream = new ObjectInputStream(in1);
        List<Animal> list = (List)inputStream.readObject();
        System.out.println(list);
        list = (List) inputStream.readObject();
        System.out.println(list);
        inputStream.close();

        ByteArrayInputStream in2 = new ByteArrayInputStream(ou2.toByteArray());
        inputStream = new ObjectInputStream(in2);
        list = (List) inputStream.readObject();
        System.out.println(list);
    }
}

class House implements Serializable{}

class Animal implements Serializable{
    private String name;
    private House preferHouse;

    public Animal(String name, House preferHouse){
        this.name = name;
        this.preferHouse = preferHouse;
    }

    public String toString(){
        return name + "[" + super.toString() + "]," + preferHouse + "\n";
    }
}
