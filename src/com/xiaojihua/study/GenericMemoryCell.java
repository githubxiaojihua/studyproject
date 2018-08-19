package com.xiaojihua.study;

import java.util.Comparator;

/**
 * CaseInsensitiveCompare 就是函数对象
 * 具体使用是在main方法中的System.out.println(findMax(arr,new CaseInsensitiveCompare()));
 * @author Administrator
 *
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

class CaseInsensitiveCompare implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        // TODO Auto-generated method stub
        return o1.compareToIgnoreCase(o2);
    }

}


