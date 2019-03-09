package com.xiaojihua.javabasic.chapter14.reflect;

import java.lang.reflect.Field;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、反射机制部分方法的使用
 * 2、虽然在修改final成员的时候程序没有报错，但是
 * 修改是不会成功的。可以从运行结果上来看。
 */
public class C04ModifyPrivateFields {
    public static void main(String[] args) throws Exception {
        WithPrivateFields w = new WithPrivateFields();
        //根据字段名称获取字段
        Field i = w.getClass().getDeclaredField("i");
        //设置可访问为true
        i.setAccessible(true);
        //读取和修改。如果知道确切的字段类型可以调用更确定的方法，比如setInt
        print(i.getInt(w));
        i.setInt(w,22);
        print(w);

        //对于final字段的修改虽然程序不会报错，但是也不会成功
        Field s = w.getClass().getDeclaredField("s");
        s.setAccessible(true);
        print(s.get(w));
        s.set(w,"I am a final String2");
        print(w);

        Field s2 = w.getClass().getDeclaredField("s2");
        s2.setAccessible(true);
        print(s2.get(w));
        s2.set(w,"I am a String2");
        print(w);

    }
}

class WithPrivateFields{
    private int i = 1;
    private final String s = "I am a final String";
    private String s2 = "I am a String";

    public String toString(){
        return i + "," + s + "," + s2;
    }
}
