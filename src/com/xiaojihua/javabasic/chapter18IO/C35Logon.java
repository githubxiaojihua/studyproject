package com.xiaojihua.javabasic.chapter18IO;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * transient关键字的使用。当序列化的对象有一部分敏感信息不想被序列化的时候，
 * 可以使用Externalizable进行自定义序列化，也可以使用Serializable配合
 * transient关键字来实现而且更加优雅。
 *
 * 被transient修饰的字段是不会被序列化的。
 */
public class C35Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;

    public C35Logon(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String toString(){
        return "logon info:\n username:" + username + "\n date:" + date + "\n password:" + password ;
    }

    public static void main(String[] args) throws Exception {
        C35Logon logon = new C35Logon("hulk","abc234");
        System.out.println("logon = " + logon);

        String path = "H:\\logon.out";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(logon);
        out.close();
        TimeUnit.MILLISECONDS.sleep(1);

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        logon = (C35Logon) in.readObject();
        Date date1 = new Date();
        System.out.println("read Object at " + date1);
        System.out.println("logon = " + logon);

    }


}
