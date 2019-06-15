package com.xiaojihua.javabasic.chapter15generic;

/**
 * 使用内部链式存储机制来实现栈
 * 使用了末端哨兵来控制栈何时为空
 * @param <T>
 */
public class C08LinkedStack<T> {

    /**
     * 这是嵌套类，
     * 当使用内部类的时候，可以直接使用外层类的类型参数
     * 定义Node。拥有自己的类型参数
     * @param <U>
     */
    private static class Node<U>{
        U item;
        Node<U> next;

        Node(){ item = null; next = null; }

        Node(U item, Node<U> next){
            this.item = item;
            this.next = next;
        }

        /**
         * 判断是否是栈低
         * @return
         */
        boolean isEnd(){
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<>();// 末端哨兵

    public void push(T item){
        top = new Node<>(item, top);
    }

    /**
     * 当到达栈低后，top就不在下移，该方法一直返回null。
     * @return
     */
    public T pop(){
        T item = top.item;
        if(!top.isEnd()){
            top = top.next;
        }
        return item;
    }

    public static void main(String[] args) {
        C08LinkedStack<String> linkedStack = new C08LinkedStack<>();
        for(String s : "Phasers or stun!".split(" ")){
            linkedStack.push(s);
        }
        String s;
        while((s = linkedStack.pop()) != null){
            System.out.println(s);
        }
    }
}
