package com.xiaojihua.datastructure;

import java.util.Comparator;

/**
 * CaseInsensitiveCompare 就是函数对象
 * 具体使用是在main方法中的System.out.println(findMax(arr,new CaseInsensitiveCompare()));
 * @author Administrator
 *
 * 定义函数对象应该先定义一个接口。
 * 然后定义一个接口的实现类，或者使用匿名内部类，作为参数传递
 * 比如下面的 findMax(AnyType[] arr, Comparator<? super AnyType> cmp)，引用的是接口
 * 但是实际传给他的就是 findMax(arr,new CaseInsensitiveCompare())
 */
public class GenericMemoryCell {

    public static <AnyType> AnyType findMax(AnyType[] arr, Comparator<? super AnyType> cmp){

        int maxIndex = 0;

        for(int i=1; i<arr.length; i++){
            if(cmp.compare(arr[i], arr[maxIndex]) > 0 ){
                maxIndex = i;
            }
        }

        return arr[maxIndex];

    }

    public static void main(String[] args) {

        String[] arr = {"ZEBRA","alligator","crocodile"};
        System.out.println(findMax(arr,new CaseInsensitiveCompare()));
    }

}

/**
 * 函数对象的实现类
 */
class CaseInsensitiveCompare implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        // TODO Auto-generated method stub
        return o1.compareToIgnoreCase(o2);
    }

}


