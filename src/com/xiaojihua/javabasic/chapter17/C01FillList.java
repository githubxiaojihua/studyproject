package com.xiaojihua.javabasic.chapter17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 知识点：
 * 1、通过Collectins的两个静态方法来填充容器。
 * nCopies主要是创建一个列表。fill是填充已有列表
 */
public class C01FillList {

    public static void main(String[] args){
        //通过构造方法接收一个list来初始化列表，一般的List都有这个方法
        List<StringObject> strings = new ArrayList<>(
                Collections.nCopies(4,new StringObject("Hello"))
        );
        System.out.println(strings);

        Collections.fill(strings, new StringObject("World"));
        System.out.print(strings);
    }
}

class StringObject{
    private String value;
    public StringObject(String value){
        this.value = value;
    }
    public String toString(){
        return super.toString() + " " + value;
    }
}
