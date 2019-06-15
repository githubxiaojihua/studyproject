package com.xiaojihua.javabasic.chapter17ContainnerDeeper;

import java.util.LinkedList;
import java.util.Stack;
import static com.xiaojihua.javabasic.util.Print.*;

/**
 * 知识点：
 * 1、java.util.Stack继承自Vactor，可以当作Vactor来用。
 * 2、LinkedList也可以作为Stack来使用，其本身有很多方法来支持栈的操作。
 * 3、使用自定义工具类中的Stack来，作为栈的使用。
 */
public class C25Stacks {
    public static void main(String[] args){
        //使用java.util中自带的Stack
        Stack<String> stack = new Stack<>();
        for(C24Month m : C24Month.values()  ){
            stack.push(m.toString());
        }
        print("stack:" + stack);

        //将stack当作vactor来使用
        stack.addElement("The last line");
        print("element 5:" + stack.elementAt(5));
        print("pop all elements:");
        while(!stack.empty()){
            print(stack.pop());
        }

        //使用linkedlist来当作stack来使用
        LinkedList<String> lStack = new LinkedList<>();
        for(C24Month m : C24Month.values()){
            lStack.addFirst(m.toString());
        }
        print("lStack:" + lStack);
        while(!lStack.isEmpty()){
            print(lStack.removeFirst() + " " );
        }

        //使用util中自定义的Stack
        com.xiaojihua.javabasic.util.Stack<String> stack2 = new com.xiaojihua.javabasic.util.Stack<>();
        for(C24Month m : C24Month.values()){
            stack2.push(m.toString());
        }
        print("stack2:" + stack2);
        while(!stack2.isEmpty()){
            print(stack2.pop() + " ");
        }


    }
}
