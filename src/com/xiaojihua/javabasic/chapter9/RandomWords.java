package com.xiaojihua.javabasic.chapter9;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * 知识点：策略模式的一个应用。
 * 策略模式：编写一个执行某些动作的操作，然后接收一个自己编写的接口。意思告诉客户端程序员：你可以用你想用
 * 的任何对象来调用我的方法，只要你的对象实现我的接口。这样此方法就具有很高的复用性。
 *
 * Scanner类的构造器接收一个Readable接口，这使得Scanner类不必将其参数限定为某个具体的类，使Scanner类可以作用于
 * 更多的类。
 * Scanner类是先将内容读取到CharBuffer中然后再根据分隔符默认是空格进行分割读取
 */
public class RandomWords implements Readable {
    private static Random random = new Random(47);
    private static final char[] capitals = "ABCDEFGHIGKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] lowers = "abcdefghigklmnopqrstuvwxyz".toCharArray();
    private static final char[] vowels = "aeiou".toCharArray();
    private int count;
    RandomWords(int count){ this.count = count; }
    @Override
    public int read(CharBuffer cb) throws IOException {
        if(count-- == 0){
            return -1;
        }
        cb.append(capitals[random.nextInt(capitals.length)]);
        for(int i = 0; i < 4; i++){
            cb.append(lowers[random.nextInt(lowers.length)]);
            cb.append(vowels[random.nextInt(vowels.length)]);
        }
        cb.append(" ");//增加分隔符
        return 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new RandomWords(10));
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }

}
