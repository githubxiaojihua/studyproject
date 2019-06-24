package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

/**
 * 知识点：
 * 通过Exeternalizable来控制序列化过程。可以在序列化和反序列化的过程中
 * 进行自定义的操作。
 * Externalizable继承处Serializable接口，并且多了writeExternal()和
 * readExternal()方法，来在序列化和反序列化的过程中进行自定义操作。
 *
 * Blip1和Blip2都能够被正常序列化，但是Blip2却不能被反序列化，原因是Blip2
 * 的构造函数不是public的。
 * 使用Externalizable接口进行可控制的序列化，在进行反序列化的时候会进行
 * 对象的初始化，包括字段的初始化，然后再调用默认构造函数（如果没有默认构造
 * 函数就会报错）。
 */
public class C33Blips {
    public static void main(String[] args) throws Exception{
        System.out.println("Contructor Blips");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("Blips.out"));
        System.out.println("Write Blip1");
        out.writeObject(b1);
        System.out.println("Write Blip2");
        out.writeObject(b2);
        out.close();

        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Blips.out"));
        System.out.println("Read Blip1");
        b1 = (Blip1) in.readObject();
        System.out.println("Read Blip2");
        b2 = (Blip2) in.readObject();
        in.close();
    }
}

class Blip1 implements Externalizable{
    public Blip1(){
        System.out.println("Blip1 Constructor!");
    }

    /**
     * 继承自Externalizable，重写writeExternal方法来
     * 自定义序列化时的动作
     * @param out
     */
    @Override
    public void writeExternal(ObjectOutput out){
        System.out.println("Blip1 writeExternal.");
    }

    /**
     * 继承自Externalizable，重写readExternal方法来
     * 自定义反序列化时的动作
     * @param input
     */
    @Override
    public void readExternal(ObjectInput input){
        System.out.println("Blip1 readExternal.");
    }

}

class Blip2 implements Externalizable{
    /**
     * 注意构造方法的访问权限不是public的
     */
    Blip2(){
        System.out.println("Blip2 Constractor!");
    }

    @Override
    public void writeExternal(ObjectOutput out){
        System.out.println("Blip2 writeExternal.");
    }

    @Override
    public void readExternal(ObjectInput input){
        System.out.println("Blip2 readExternal.");
    }
}
