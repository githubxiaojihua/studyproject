package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.*;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、集合的UnsupportedOperationException异常。当集合是通过
 * Arrays.asList()方法来进行构建的话（也就是说集合的底层是Array）,
 * Arrays.asList()返回的是一个不可修改有固定大小的List，底层是
 * Array(可以通过asList的源码来看，其返回的List是一个嵌套类，这个
 * 嵌套类实现了abstractList接口，没有提供相应的add和remove相关
 * 的方法，因此是固定大小的)，这里还牵扯到一个知识点就是optional operation
 * （可选方法），Collection中有些方法是可选的（与增加和删除相关的），
 * 也就是说虽然这些方法在Collection接口中，但是实现了是可以不实现的。
 *
 * 当返回一个不可改变大小的list后针对此list做改变大小的操作将会报
 * UnsupportedOperationException异常。
 *
 * 2、Collections.unmodifiableList方法接收一个list并最终返回一个不可修改的
 * list，针对此集合做任何修改性的操作都将报UsupportedOperationException异常
 */
public class C07Unsupported {
    public static void test(String msg, List<String> list){
        print(msg);
        Collection<String> c = list;
        Collection<String> subList = list.subList(1,8);
        Collection<String> c2 = new ArrayList<>(subList);
        try{
            c.retainAll(c2);
        }catch(Exception e){
            print("retainall():" + e);
        }

        try{
            c.removeAll(c2);
        }catch(Exception e){
            print("removeAll():" + e);
        }

        try{
            c.clear();
        }catch(Exception e){
            print("clear():" + e);
        }

        try{
            c.add("X");
        }catch(Exception e){
            print("retainall():" + e);
        }

        try{
            c.addAll(c2);
        }catch(Exception e){
            print("addAll():" + e);
        }

        try{
            c.remove("C");
        }catch(Exception e){
            print("remove():" + e);
        }

        /**
         * 当list是一个Arrays.asList方法生成的时候，那么
         * 这个方法是没有问题的，因为set并非是改变底层array
         * 的尺寸。
         *
         * 但是当是通过Collections.unmodifieableList生成
         * 的，那么就会报错。因为不允许更改list.
         */
        try{
            list.set(0,"X");
        }catch(Exception e){
            print("List.set():" + e);
        }
    }

    public static void main(String[] args){

        List<String> list = Arrays.asList("A B C D E F G H I J K L".split( " "));
        test("Modifiable Copy", new ArrayList<>(list));//不会报异常
        test("Arrays.asList()",list);//会报异常
        test("unmodifiableList", Collections.unmodifiableList(new ArrayList<>(list)));
    }
}
