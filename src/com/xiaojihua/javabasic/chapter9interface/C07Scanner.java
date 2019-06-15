package com.xiaojihua.javabasic.chapter9interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * Scanner类的使用
 */
public class C07Scanner {
    private static Scanner scanner;

    /**
     * 使用方式1：
     * 从标准输入流中读取int类型并且输出
     */
    public static void method1(){
        scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.println("从标准输入流中读取int类型值：" + i);
    }

    /**
     * 使用方式2：
     * 从File文件中读取long类型并输出
     * @throws FileNotFoundException
     */
    public static void method2() throws FileNotFoundException {
        scanner = new Scanner(new File("D://myNumber.txt"));
        System.out.println("从文件中读取long类型值：");
        while(scanner.hasNextLong()){
            long lo = scanner.nextLong();
            System.out.println(lo);
        }
    }

    /**
     * 使用方式3：
     * 通过Scanner提供的策略模式，自定义一个策略模式进行读取
     */
    public static void method3(){
        System.out.println("使用自定义策略读取值：");
        scanner = new Scanner(new ReadStrategy(10));
        while(scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        method1();
        method2();
        method3();
    }


}

class ReadStrategy implements Readable{
    private static char[] upCase = "ABCDEFGHIGKLMNOPQRSTUVWXYZ".toCharArray();
    private static char[] downCase = "abcdefghigklmnopqrstuvwxyz".toCharArray();
    private static char[] vowel = "aeiou".toCharArray();
    private static Random random = new Random(47);

    private int count;

    ReadStrategy(int count){
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if(count-- == 0){
            return -1;
        }
        cb.append(upCase[random.nextInt(upCase.length)]);
        for(int i = 0; i < 4; i++){
            cb.append(downCase[random.nextInt(downCase.length)]);
            cb.append(downCase[random.nextInt(downCase.length)]);
        }
        cb.append(" ");
        return 1;
    }
}
