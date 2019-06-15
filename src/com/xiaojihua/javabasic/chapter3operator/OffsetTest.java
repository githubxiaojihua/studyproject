package com.xiaojihua.javabasic.chapter3operator;

/**
 * 知识点：
 * 1、位移操作符的使用
 * 2、位移操作符只能作用于byte,shot,int,long,char。其中
 * byte,shot,char在位移的时候会转换成int类型。
 * 3、位移的实际位数是取自有操作数的低5位
 */
public class OffsetTest {
    public static void main(String[] args) {
        int intNum = 1;
        long longNum = 1l;
        /**
         * 对int进行位移操作
         */
        System.out.print("int 类型位移前：");
        System.out.println(Integer.toBinaryString(intNum));
        //右操作数位int类型，位数为32位，但是移动的位数是34
        //实际上去的值是34的低5位00010，也就是2，实际上下面的操作等同于
        //intNum <<= 2;
        intNum <<= 34;
        System.out.print("int 类型位移后（31临界状态）：");
        System.out.println(Integer.toBinaryString(intNum));
        /**
         * 对long进行位移操作
         */
        System.out.print("long 类型位移前：");
        System.out.println(Long.toBinaryString(longNum));
        //long的位数为64位，因此取得时右操作数的低6位
        longNum <<= 65;
        System.out.println("long 类型位移后（63临界状态）：");
        System.out.println(Long.toBinaryString(longNum));
    }
}
