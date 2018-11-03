package com.xiaojihua.datastructure;


import java.util.NoSuchElementException;

/**
 * 数组实现队列
 * 约定 front = tail时队列为空
 * 使用循环数组实现
 * @param <AnyType>
 */
public class MyQueue<AnyType>{

    // 存值数组
    private AnyType[] elements;
    // 队头，队尾指针
    private int front,tail;
    // 元素个数
    private int size;
    // 初始值
    private static final int DEFAULT_SIZE = 16;

    /**
     * 初始构造函数
     */
    MyQueue(){
        elements = (AnyType[])new Object[DEFAULT_SIZE];
        front = tail = 0;
    }

    /**
     * 根据初始值初始化
     * @param capacity
     */
    MyQueue(int capacity){
        elements = (AnyType[])new Object[capacity];
        front = tail = 0;
    }

    /**
     * 返回元素数量
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * 是否为空。约定front = tail时为空
     * @return
     */
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 入队操作
     * @param data
     * @return
     */
    public boolean add(AnyType data) {
        //当front == tail + 1的时候认为容量满了需要扩容
        if(front == (tail + 1)%elements.length){
            // 扩容
            this.ensureCapacity();
        }
        elements[tail] = data;
        size++;
        tail = (tail + 1) % elements.length;
        return true;
    }

    /**
     * 出队操作
     * @return
     */
    public AnyType remove(){
        if(front == tail){
            throw new NoSuchElementException();
        }
        AnyType data = elements[front];
        front = (front+1)%elements.length;
        size--;
        return data;
    }

    /**
     * @return
     */
    public int getElementsSize(){
        return this.elements.length;
    }

    /**
     * 扩充容量，扩充原来容量的两倍
     *
     */
    public void ensureCapacity(){

        AnyType[] oldArray = elements;
        int j = 0;
        elements = (AnyType[]) new Object[elements.length*2];
        for(int i=front; i!=tail; i=(i+1)%oldArray.length){
            elements[j++] = oldArray[i];
        }
        // 重新设置front 和 tail
        this.front = 0;
        this.tail = j;

    }

    /**
     * toString
     * @return
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i=front; i!=tail; i = (i+1)%elements.length){
            sb.append(elements[i]);
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * toString
     * @return
     */
        public String elementToString(){
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for(int i=0; i<elements.length; i++){
                sb.append(elements[i]);
                sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
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
