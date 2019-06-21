package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;
import java.util.Random;

public class C31Worm implements Serializable {
    private static Random random = new Random(47);
    private char c;
    private Data[] data;
    private C31Worm next;

    public C31Worm(char c, int i){
        System.out.println("Worm Constructor:" + i);
        this.c = c;
        this.data = new Data[]{new Data(random.nextInt(10)),
                new Data(random.nextInt(10)),new Data(random.nextInt(10))};
        if(--i > 0){
            this.next = new C31Worm((char)(c+1), i);
        }
    }

    public C31Worm(){
        System.out.println("Default Constractor.");
    }

    public String toString(){
        StringBuilder str = new StringBuilder(":");
        str.append(c).append("(");
        for(Data d : data){
            str.append(d);
        }
        str.append(")");
        if(next != null){
            str.append(next);
        }
        return str.toString();

    }

    public static void main(String[] args) throws Exception {
        C31Worm worm = new C31Worm('a',6);
        System.out.println("w" + worm);

        String outFile = "H:\\serializable.out";
        ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream(outFile));
        out1.writeObject("write worm1:");
        out1.writeObject(worm);
        out1.close();

        ObjectInputStream in1 = new ObjectInputStream(new FileInputStream(outFile));
        String s1 = (String) in1.readObject();
        C31Worm worm1 = (C31Worm)in1.readObject();
        System.out.println(s1);
        System.out.println("w1" + worm1);
        in1.close();

        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(byteArrayOut);
        out2.writeObject("write worm2:");
        out2.writeObject(worm);
        out2.flush();

        ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(byteArrayOut.toByteArray());
        ObjectInputStream in2 = new ObjectInputStream(byteArrayIn);
        String s2 = (String) in2.readObject();
        C31Worm worm2 = (C31Worm) in2.readObject();
        System.out.println(s2);
        System.out.println("w2" + worm2);
        out2.close();
        in2.close();

    }
}

class Data implements Serializable{
    private int i;
    public Data(int i){
        this.i = i;
    }
    public String toString(){
        return Integer.toString(i);
    }
}
