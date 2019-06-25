package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 使用Externalizable进行自定义反序列化的时候，需要分别在writeExternal
 * 和readExternal方法中将需要序列化的字段进行人工指定，否则反序列化的时候
 * 将初始化s为null，i为0。因为反序列化的时候会先进行对象的初始化然后再调用
 * 默认构造方法。要正确序列化和反序列化必须在writeExternal方法中序列化相应
 * 字段，在readExternal方法中反序列化相应字段然后赋值给相应字段。
 *
 * 另外如果从一个Externalizable对象继承，那么要调用基类中的writeExternal
 * 和readExternal方法来保证基类相关组件的序列化和反序列化。
 */
public class C34Blip3 implements Externalizable {
    private String s;
    private int i;

    /**
     * 在默认构造方法中不进行s和i的初始化
     */
    public C34Blip3(){
        print("C34Blip3 default Constructor.");
    }

    /**
     * 在非默认构造方法中进行了s和i的初始化
     * @param s
     * @param i
     */
    public C34Blip3(String s, int i){
        print("C34Blip3(String, int)");
        this.s = s;
        this.i = i;
    }

    public String toString(){
        return s + i;
    }

    @Override
    public void writeExternal(ObjectOutput output) throws IOException {
        print("C34Blip3.writeExternal");
        //要想正确初始化必须将s和i writeObject
        output.writeObject(s);
        output.writeObject(i);
    }

    @Override
    public void readExternal(ObjectInput input) throws IOException,ClassNotFoundException{
        print("C34Blip3.readExternal");
        //同时这里也必须反序列化，并且进行赋值
        s = (String) input.readObject();
        i = (int)input.readObject();
    }

    public static void main(String[] args) throws IOException,ClassNotFoundException{
        String path = "H:\\blip3.out";
        print("Constructor using C34Blip3(String,int)");
        C34Blip3 b3 = new C34Blip3("A string" , 47);
        print(b3);

        print("write Object:");
        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(path));
        outputStream.writeObject(b3);
        /*
            别忘记关闭。
            一般来讲，文件的关闭可以有如下规律：
            1、在中间位置打开的一般都需要关闭。
            2、原stream引用改变，那么改变之前，原引用要关闭。
            3、在文件最后的打开可以不关闭。
            4、其它的都关闭
         */
        outputStream.close();

        print("read Object:");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
        b3 = (C34Blip3) inputStream.readObject();
        print(b3);

    }
}
