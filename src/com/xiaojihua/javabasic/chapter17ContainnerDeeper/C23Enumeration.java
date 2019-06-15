package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 知识点：
 * 1、对于老api中的Vector以及Enumeration的简介。
 * 2、Vector可以当作ArrayList来使用，但是被ArrayList代替了
 * 3、Enumeration用法类似于Iterator，其本身也是一个接口，
 * 常用的两个方法包括：hasMoreElements和nextElement
 * 4、通过Vector的elements方法可以获取Enumeration，通过Collections.enumeration（）
 * 方法也可以获得Enumeration
 */
public class C23Enumeration {
    public static void main(String[] args){
        Vector<String> vector = new Vector<>(C05Countries.names(10));
        Enumeration<String> enumeration = vector.elements();
        while(enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }
        //通过enumeration来适配ArrayList的Iterator成为一个Enumeration
        enumeration = Collections.enumeration(new ArrayList<>());
    }
}
