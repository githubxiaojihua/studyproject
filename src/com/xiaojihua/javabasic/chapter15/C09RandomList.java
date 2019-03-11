package com.xiaojihua.javabasic.chapter15;

import java.util.ArrayList;
import java.util.Random;

/**
 * 泛型的应用：可以盛放任何对象
 * 随机选取所盛放对象
 * @param <T>
 */
public class C09RandomList<T> {
    private ArrayList<T> arrayList = new ArrayList<>();
    private Random random = new Random(47);//使用47作为seed，初始Random。他与默认构造方法的区别在于，每次生成的随机数是相同的。
    public void add(T item){ arrayList.add(item); }
    public T select(){
        return arrayList.get(random.nextInt(arrayList.size()));//生成0-指定范围的随机码
    }

    public static void main(String[] args) {
        C09RandomList<String> randomList = new C09RandomList<>();
        for(String s : "The quick brown fox jumped over the lazy brown dog".split(" ")){
            randomList.add(s);
        }
        for(int i = 0; i < 11; i++){
            System.out.println(randomList.select());
        }
    }
}
