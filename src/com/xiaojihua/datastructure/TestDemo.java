package com.xiaojihua.datastructure;

import java.util.*;

/**
 * 练习专用类
 *
 */

/**
 * 将前缀表达式转换为后缀表达式（操作符号只有+*（）），优先级由高到低（），* ，+
 * 1、当读取到一个操作数的时候（在本例中是char），立即把它放到输出中
 * 2、操作符不立即输出，放到栈中。
 * 3、如果遇到右括号则弹出栈，并将弹出的符号放到输出中，直到遇到左括号为止，左括号只弹出但是不输出
 * 4、如果遇到+*（等则将栈等优先级高的元素弹出（含优先级相同的）并放到输出中，直到遇到更低优先级。但是除非是在处理）否则绝不将（弹出。
 * 5、弹出工作完成以后则压入当前操作符。
 * 6、如果输入结束则将栈中所有元素弹出，并放到输出中。
 *
 * 假设：表达式由字母和操作符组成
 */
public class TestDemo<AnyType> {
    private AnyType[] theArray;
    private int theSize;
    private int front,tail;
    private static final int DEFAULT_SIZE = 10;

    TestDemo(int capacity){
        theArray = (AnyType[]) new Object[capacity];
        theSize = 0;
        front = tail = 0;
    }

    TestDemo(){
        this(DEFAULT_SIZE);
    }

    public int size(){ return theSize; }

    public boolean isEmpty(){ return front == tail; }

    public boolean add(AnyType x){
        if(size() + 1 == theArray.length){
            ensureCapacity(2 * theArray.length + 1);
        }

        theArray[tail] = x;
        tail = (tail + 1) % theArray.length;
        theSize++;
        return true;
    }

    public AnyType remove(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        AnyType removeItem = theArray[front];
        front = (front + 1) % theArray.length;
        theSize--;
        return removeItem;
    }

    private void ensureCapacity(int capacity){
        if(capacity < size()){
            throw new IllegalStateException();
        }
        AnyType[] old = theArray;
        theArray = (AnyType[])new Object[capacity];
        int j = 0;
        for(int i = front; i != tail; i = (i + 1) % old.length){
            theArray[j++] = old[i];
        }
        front = 0;
        tail = j;

    }

    /**
     * toString
     * @return
     */
    public String elementToString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i=0; i<theArray.length; i++){
            sb.append(theArray[i]);
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        TestDemo<Integer> myQueue = new TestDemo<>();
        for(int i=0; i<15; i++){
            myQueue.add(i);
        }
        System.out.println(myQueue.elementToString());
        myQueue.remove();
        myQueue.remove();
        myQueue.remove();
        myQueue.remove();
        myQueue.remove();
        for(int i=8; i<11; i++){
            myQueue.add(i);
        }
        System.out.println(myQueue.elementToString());
        System.out.println(myQueue.toString());
    }

}



