package com.xiaojihua.gof23.p04prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 测试深克隆：使用序列化和反序列化来实现深克隆，此时使用C01Sheep对象测试（浅克隆对象测试，需要实现序列化接口）
 * 不止克隆对象本身，对于对象所引用的对象，也克隆。
 *
 */
public class C03DeepClone02 {
    public static void main(String[] args) throws Exception {
        Date birtyday = new Date(123123123123123l);
        C01Sheep s1 = new C01Sheep("少利",birtyday);
        System.out.println("s1(修改前)："+s1);

        // 序列化和反序列化克隆对象
        // 将对象通过流写入到内存中，然后从内存中读取到一个byte数组中
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(s1);
        byte[] bytes = bos.toByteArray();
        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        C01Sheep s2 = (C01Sheep) ois.readObject();//克隆好的对象！

        //修改Date对象
        birtyday.setTime(8907789729873l);
        System.out.println("s1(修改后)："+s1);
        System.out.println("修改s1的birtyday:" + birtyday);
        System.out.println("s2："+s2);
    }
}
