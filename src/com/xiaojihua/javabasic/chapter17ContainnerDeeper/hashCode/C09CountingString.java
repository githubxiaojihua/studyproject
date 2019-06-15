package com.xiaojihua.javabasic.chapter17ContainnerDeeper.hashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 知识点：
 * 1、hashCode函数的构建建议和步骤：
 *    1）建立一个非零的int类型，可以是17
 *    2）对于在equls()方法中使用的每个关键字段，计算他的int类型的hash Code,用变量C来代表
 *    不同类型字段的c的计算方法为：
 *    boolean------------------------------>c=(f?0:1)
 *    byte,char,short,int------------------>c=(int)f
 *    long--------------------------------->c=(int)(f^(f>>>32))
 *    float-------------------------------->c=Float.floatToIntBits(f)
 *    double------------------------------->long l=Double.doubleToLongBits(f);
 *                                          c=(int)(l^(l>>>32))
 *    在equals中用到的Object----------------->c=f.hashCode()
 *    Array-------------------------------->针对类型对数组中的每一个元素，履行上面的规则，最后求和算c
 *    3)合并在上一步求取的c：result = 37 * result + c;
 *    4）返回result。
 *    5）确保相同的实例有相同的hashcode.
 *
 *    Float.floatToIntBits()是按照IEEE754规定的，浮点位布局，返回指定浮点值的表示形式，
 *    也就是用32位的二进制位来标识一个指定的浮点值，每个位置用来标识什么是规定好的，最后将32位的二进制
 *    转换成int返回。
 */
public class C09CountingString {
    private static List<String> strList = new ArrayList<>();
    private int id = 0;
    private String str;

    public C09CountingString(String str){
        this.str = str;
        strList.add(str);
        for(String strTmp : strList){
            if(strTmp.equals(str)){
                this.id++;
            }
        }
    }

    @Override
    public String toString(){
        return " String:" + this.str + ", id:" + id + ", hashCode:" + this.hashCode();
    }

    @Override
    public boolean equals(Object o){
        return o instanceof C09CountingString ? false :
                ((C09CountingString)o).str.equals(str) ? ((C09CountingString)o).id == id : false;
    }

    /**
     * 根据hashcode的计算方法，计算hashcode
     * @return
     */
    @Override
    public int hashCode(){
        int result = 17;
        result = 37 * result + id;
        result = 37 * result + str.hashCode();
        return result;
    }

    public static void main(String[] args){
        Map<C09CountingString,Integer> map = new HashMap<>();
        C09CountingString[] cStrs = new C09CountingString[5];
        for(int i=0; i<cStrs.length; i++){
            cStrs[i] = new C09CountingString("hi");
            map.put(cStrs[i], i);
        }
        System.out.println(map);
        for(C09CountingString str : cStrs){
            System.out.println("Looking up for:" + str + "=");
            System.out.println(map.get(str));
        }
    }
}
