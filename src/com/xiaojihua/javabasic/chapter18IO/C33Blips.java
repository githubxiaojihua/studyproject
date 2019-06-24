package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

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

    @Override
    public void writeExternal(ObjectOutput out){
        System.out.println("Blip1 writeExternal.");
    }

    @Override
    public void readExternal(ObjectInput input){
        System.out.println("Blip1 readExternal.");
    }

}

class Blip2 implements Externalizable{
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
