package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

/**
 * 知识点：
 * 用Serializable来实现自定义的序列化（也算是对Externalizable的一种
 * 代替。
 *
 * 通过对实现Serializable接口的类，增加如下两个方法可以做到：
 * private void writeObject(ObjectOutputStream stream)
 * throws IOException;
 *
 * private void readObject(ObjectlnputStream stream)
 * throws IOException, ClassNotFoundException
 *
 * 当增加这两个方法后，在调用ObjectOutputStream\ObjectInputStream方法进行
 * 序列化的时候，JVM会通过反射来判断当前对象是否增加了如上两个方法，如果有则
 * 不走默认的writeObject\readObject方法，而走上面增加的两个。
 * 同时ObjectOutputStream\ObjectInputStream提供了defaultWriteObject\
 * defaultReadObject方法来调用默认方法。
 */
public class C36SerialCtl implements Serializable {
    private String a;
    private transient String b;

    public C36SerialCtl(String aa, String bb){
        a = "Not Transient " + aa;
        b = "Transient " + bb;
    }

    public String toString(){
        return a + "\n" + b;
    }

    /**
     * 增加自定义的序列化操作
     * @param outputStream
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        //调用默认的序列化方法，忽略transient字段
        //将向stream中写入非static和非transient字段
        outputStream.defaultWriteObject();
        //自定义序列化 transient字段
        outputStream.writeObject(b);
    }

    /**
     * 增加自定义的反序列化操作
     * @param inputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream inputStream) throws IOException,ClassNotFoundException{
        //注意这里要注意下面这两句的调用顺序，如果将第一句注掉，那么将导致，aa字段为null
        //b的值反而成了aa的值了，因为只读了第一个写出的对象。
        //调用默认的反序列化方法
        inputStream.defaultReadObject();
        //读入序列化的transient字段
        b = (String) inputStream.readObject();
    }

    public static void main(String[] args) throws Exception{
        C36SerialCtl sc = new C36SerialCtl("abc","ccc");
        System.out.println("before:\n" + sc);
        //使用ByteArray来代替输出文件
        ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();

        ObjectOutputStream out = new ObjectOutputStream(arrayOut);
        out.writeObject(sc);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(arrayOut.toByteArray()));
        sc = (C36SerialCtl) in.readObject();
        System.out.println("after:\n" + sc);
    }

}
