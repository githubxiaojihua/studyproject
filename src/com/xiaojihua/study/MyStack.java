package com.xiaojihua.study;

/**
 * 通过单链表实现栈
 */
public class MyStack<AnyType> extends MyLinkedListSign<AnyType> {

    /**
     * 调用父类的add()方法实现push
     * @param data
     * @return
     */
    public boolean push(AnyType data){
        return add(data);
    }

    /**
     * 调用父类的remove()方法实现pop
     * @return
     */
    public AnyType pop(){
        return remove();
    }

    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>();
        myStack.push("first");
        myStack.push("2");
        myStack.push("3");
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }
}
