package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.BitSet;
import java.util.Random;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 *  知识点：
 *  BitSet的使用：当想高效的存储非常多的  on-off信息的时候比较有用。BitSet在空间的使用上效率较高，
 *  但是在运行效率上对于数组较慢。
 *
 *  BitSet默认的长度为64位，但是可以进行自动扩展，也就是说最小位数为64位。
 *  在初始情况下每一位都被初始化为false，当调用set(int)的时候相应索引的位置就会变成true.
 *
 *  BitSet可以用EnumSet来代替。根据实际情况进行选择
 *
 */
public class C26Bits {

    /**
     * 打印BitSet，将BitSet每一位换成01字符串进行打印
     * @param bitSet
     */
    public static void printBit(BitSet bitSet){
        print("BitSet:" + bitSet);//只是打印为tue的索引位置
        StringBuilder strBuilder = new StringBuilder();
        for(int i=0; i<bitSet.length(); i++){
            //如果索引位置为true则换成1否则换成0
            strBuilder.append(bitSet.get(i) ? "1" : "0");
        }
        print("bit pattern:" + strBuilder.toString());
    }

    public static void main(String[] args){
        Random random = new Random(47);

        //byte，存储byte类型的二进制表示
        byte b = (byte)random.nextInt();
        BitSet byteSet = new BitSet();
        for(int i = 7; i>=0; i--){
            if( ((1<<i) & b) != 0){
                byteSet.set(i);
            }else{
                byteSet.clear(i);
            }
        }
        print("byte value:" + b);
        printBit(byteSet);

        //short，存储short类型的二进制表示
        short shortNum = (short) random.nextInt();
        BitSet shortSet = new BitSet();
        for(int i=15; i>=0; i--){
            if(((1<<i) & shortNum) != 0){
                shortSet.set(i);
            }else{
                shortSet.clear(i);
            }
        }
        print("short value:" + shortNum);
        printBit(shortSet);

        //int，存储int类型的二进制表示
        int intNum = random.nextInt();
        BitSet intSet = new BitSet();
        for(int i=31; i>=0; i--){
            if(((1<<i) & intNum) != 0){
                intSet.set(i);
            }else{
                intSet.clear(i);
            }
        }
        print("int value:" + intNum);
        printBit(intSet);

        //以下语句代表BitSet可以进行自动扩展
        BitSet bit127 = new BitSet();
        bit127.set(127);
        print("BitSet set 127:" + bit127);

        BitSet bit255 = new BitSet(64);
        bit255.set(255);
        print("BitSet(64) set 255 ");

        BitSet bit1023 = new BitSet(512);
        bit1023.set(1023);
        bit1023.set(1024);
        print("BitSet(512) set 1023 and set1024:" + bit1023);


    }
}
