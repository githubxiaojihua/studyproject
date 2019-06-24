package com.xiaojihua.javabasic.chapter18IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class C3201FreeAlien {
    public static void main(String[] args) throws IOException {
        //注意new File的构造方法，是采用了两个参数的那个。
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(new File("..","X.file")));
        C32Alien alien = new C32Alien();
        out.writeObject(alien);
    }
}
