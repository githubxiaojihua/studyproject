package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.*;

import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、List的一些用法
 */
public class C08Lists {
    private static boolean b;
    private static int i;
    private static String s;
    private static Iterator<String> it;
    private static ListIterator<String> lit;

    private static void basicTest(List<String> a){
        a.add("x");//在最后增加
        a.add(1,"X");//在第一个位置增加
        a.addAll(C05Countries.names(25));//在最后添加一个collection
        a.addAll(3,C05Countries.names(25));//在第三个位置之后添加一个collection

        //判断一个元素和一个collection是否在a中存在
        b = a.contains("1");
        b = a.containsAll(C05Countries.names(25));

        //list提供随机访问，如果是ArrayList那么消耗比较小
        //如果是LinkedList则消耗比较大
        s = a.get(1);
        i = a.indexOf("1");
        i = a.lastIndexOf("1");
        b = a.isEmpty();

        it = a.iterator();//普通的iterator
        lit = a.listIterator();//listIterator
        lit = a.listIterator(3);//返回一个从index=3开始的listIterator

        a.remove(1);//通过索引删除
        a.remove("3");//通过对象删除
        a.set(1,"y");//设置index=1的位置为y

        //保留与参数之间的交集，注意：改变了a集合
        a.retainAll(C05Countries.names(25));
        //删除参数之中的所有元素
        a.removeAll(C05Countries.names(25));

        i = a.size();
        a.clear();//删除所有元素
    }

    public static void iterMotion(List<String> a){
        ListIterator<String> it = a.listIterator();
        b = it.hasNext();
        s = it.next();
        i = it.nextIndex();
        b = it.hasPrevious();
        s = it.previous();
        i = it.previousIndex();
    }

    /**
     * 通过ListIterator可以对list进行更改（add,remove等）
     * 但是在add和remove后都必须next一下才行，因为add
     * 和remove都改变了原来list的结构。
     * @param a
     */
    public static void iterManipulation(List<String> a){
        ListIterator<String> it = a.listIterator();
        it.add("a");
        it.next();
        it.remove();
        it.next();
        it.set("r");
    }

    public static void testVisual(List<String> a){
        print(a);
        List<String> b = C05Countries.names(25);
        a.addAll(b);
        a.addAll(b);
        print(a);

        ListIterator<String> it = a.listIterator(a.size()/2);
        it.add("one");
        print(a);
        print(it.next());
        it.remove();
        print(it.next());
        it.set("8");
        print(a);

        it = a.listIterator(a.size());
        while(it.hasPrevious()){
            printnb(it.previous() + " ");
        }
        print();
        print("testVisual finished!");
    }

    /**
     * 一些只有linkedlist才有的方法
     */
    public static void testLinkedList(){
        LinkedList<String> ll = new LinkedList<>();
        ll.addAll(C05Countries.names(20));
        print(ll);
        ll.addFirst("one");
        ll.addFirst("two");
        print(ll);

        //peeking
        //Like "peeking" at the top of a stack
        print(ll.getFirst());

        //Like popping a stack:
        print(ll.removeFirst());
        print(ll.removeFirst());

        // Treat it like a queue, pulling elements
        // off the tail end:
        print(ll.removeLast());
    }

    public static void main(String[] args){
        // Make and fill a new list each time:
        basicTest(new LinkedList<String>(C05Countries.names(25)));
        basicTest(new ArrayList<String>(C05Countries.names(25)));
        iterMotion(new LinkedList<String>(C05Countries.names(25)));
        iterMotion(new ArrayList<String>(C05Countries.names(25)));
        iterManipulation(new LinkedList<String>(C05Countries.names(25)));
        iterManipulation(new ArrayList<String>(C05Countries.names(25)));
        testVisual(new LinkedList<String>(C05Countries.names(25)));
        testLinkedList();
    }
}
