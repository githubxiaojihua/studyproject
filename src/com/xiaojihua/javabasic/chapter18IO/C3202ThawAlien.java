package com.xiaojihua.javabasic.chapter18IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class C3202ThawAlien {
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("X.file"));
        Object alien = in.readObject();
        System.out.println(alien.getClass());
    }
}
