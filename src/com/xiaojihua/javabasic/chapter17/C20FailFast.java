package com.xiaojihua.javabasic.chapter17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * 知识点：
 * 1、java容器有一个FailFast机制，用于检测容器的结构是否被修改了。
 * 当进行iterator遍历的时候，外部的容器结构更改了比如add,remove
 * ,set等（当前线程，或者其他线程引起的更改），这时候就会报
 * ConcurrentModificationException错误。
 *
 * 2、补充：The ConcurrentHashMap, CopyOnWriteArrayList,
 * and CopyOnWriteArraySet use techniques that avoid
 * ConcurrentModificationExceptions.
 */
public class C20FailFast {
    public static void main(String[] args){
        Collection<String> list = new ArrayList<>();
        //从list中获取了iterator，准备进行遍历
        Iterator<String> it = list.iterator();
        //更改了外部结构，那么在遍历的时候就会报错。
        list.add("abc");
        try{
            String s = it.next();
        }catch(ConcurrentModificationException exception){
            exception.printStackTrace();
        }

    }
}
