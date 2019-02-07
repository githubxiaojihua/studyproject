package com.xiaojihua.javabasic.chapter13;

import java.util.Formatter;

/**
 * 知识点：
 * 1、格式字符串的使用 格式： %[argument_index$][flags][width][.precision]conversion
 * 第一个可选项为参数的索引，第二个可选项为左对齐标志，默认为右对齐，第三个可选项为最大宽度
 * 第四个可选项为精度（对于字符串则为长度，不能用于整数），最后一个是必选项转换为的格式化类型
 * 比如s代表字符串d代表数字f代表浮点型。
 */
public class C07Receipt {
    private double total = 0;
    private Formatter formatter = new Formatter(System.out);

    /**
     * 打印标题，item为左对齐，注意回车符
     */
    public void printTitle(){
        formatter.format("%-15s %5s %10s\n", "Item", "Qut", "price");
        formatter.format("%-15s %5s %10s\n", "----", "----", "----");
    }

    /**
     * 打印具体内容，name为左对齐，qut转化为数字width为5,price转化为小数width
     * 为10精度为2.注意有回车符
     * @param name
     * @param qut
     * @param price
     */
    public void print(String name, int qut, double price){
        formatter.format("%-15.15s %5d %10.2f\n", name, qut, price);
        total += price;
    }

    public void printTotal(){
        formatter.format("%-15s %5s %10.2f\n", "Tax", "", total * 0.06);
        formatter.format("%-15s %5s %10s\n", "----", "----", "----");
        formatter.format("%-15s %5s %10.2f\n", "Total", "", total * 1.06);
    }

    public static void main(String[] args) {
        C07Receipt c07Receipt = new C07Receipt();
        c07Receipt.printTitle();
        c07Receipt.print("zhangsan", 12, 11.2);
        c07Receipt.print("lisi", 3, 5.6);
        c07Receipt.print("wangwu", 23, 10.56);
        c07Receipt.printTotal();
    }
}
