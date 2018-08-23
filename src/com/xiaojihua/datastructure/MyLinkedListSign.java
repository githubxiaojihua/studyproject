package com.xiaojihua.datastructure;

import java.util.Iterator;

/**
 * 单链表
 * @param <AnyType>
 */
public class MyLinkedListSign<AnyType> implements Iterable<AnyType> {

    // 头结点，始终指向第一个节点
    private Node<AnyType> beginMaker;
    // 尾节点，最后一个节点的next
    private Node<AnyType> endMaker;
    // 记录元素个数
    private int size;
    // 记录元素修改次数
    private int modCount;

    /**
     * 构造方法
     */
    public MyLinkedListSign(){
        this.init();
    }

    /**
     * 公用方法：返回size
     * @return
     */
    public int size(){
        return this.size;
    }

    /**
     * 公共方法：在链表尾部插入data
     * @param data
     */
    public boolean add(AnyType data){
        this.add(this.getNode(size()),data);
        return true;
    }

    /**
     * 公共方法：在链表尾部删除元素
     * @return
     */
    public AnyType remove(){
        return this.remove(this.getNode(size())).data;
    }

    /**
     * 公共方法：ToStirng
     */
    public String toString(){
        StringBuffer sb = new StringBuffer("[");
        for(AnyType x : this){
            sb.append(x + " ");
        }
        sb.append("]");
        return new String(sb);
    }

    /**
     *  接口实现方法
     * @return
     */
    @Override
    public Iterator<AnyType> iterator() {
        return new MyLinkedListSignIterator();
    }



    /**
     * 内部方法:初始化但列表
     */
    private void init(){
        this.beginMaker = new Node<>(null,null);
        this.endMaker = new Node<>(null,null);
        this.beginMaker.next = endMaker;
        this.size = 0;
        this.modCount = 0;
    }

    /**
     * 内部方法:在制定node后插入元素
     * @param node
     * @param data
     */
    private void add(Node<AnyType> node, AnyType data){

        Node<AnyType> newNode = new Node<>(data,null);
        Node<AnyType> oldNext = node.next;
        node.next = newNode;
        newNode.next = oldNext;
        this.size++;
        this.modCount++;

    }

    /**
     * 根据index获取NOde
     * @param index
     * @return
     */
    private Node<AnyType> getNode(int index){
        if(index > this.size()){
            throw new IndexOutOfBoundsException();
        }
        Node<AnyType> returnNode = beginMaker;
        for(int i=0; i<index; i++){
            returnNode = returnNode.next;
        }
        return returnNode;
    }

    /**
     * 内部方法：删除元素
     * @param node
     * @return
     */
    private Node<AnyType> remove(Node<AnyType> node){
        Node<AnyType> perNode = this.getPreNode(node);
        perNode.next = node.next;
        this.modCount++;
        this.size--;
        return node;
    }

    /**
     * 内部方法：获取元素的前一个节点
     * @param node
     * @return
     */
    private Node<AnyType> getPreNode(Node<AnyType> node){
        Node<AnyType> currentNode = beginMaker.next;
        Node<AnyType> perNode = beginMaker;
        for(int i=0; i<size(); i++){
            perNode = currentNode;
            currentNode = currentNode.next;
            if(currentNode == node){
                break;
            }

        }
        return perNode;
    }

    /**
     * 嵌套类定义节点
     * @param <AnyType>
     */
    private static class Node<AnyType>{

        public AnyType data;
        public Node<AnyType> next;

        Node(){}

        Node(AnyType data, Node<AnyType> next){
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 内部类实现迭代器
     */
    private class MyLinkedListSignIterator implements Iterator<AnyType>{

        // 标注起始位置
        private Node<AnyType> current = beginMaker.next;
        //内部存储一份修改次数，用于跟外部的比对，外部每次remove该值均会增加，迭代器内部remove也调用的是外部的remove，因此要保证两个一致，一旦不一致则不允许删除。
        //如果没有这个变量的话，容易造成在调用迭代器期间，调用了外部的remove，改变了正在迭代的链表结构。这容易造成迭代数据的错误。
        private int expectedModCount = modCount;
        //该变量保证，在迭代器remove后必须next()一下后才能再次删除。
        private boolean okToRemove = false;

        /**
         * 是否有下一个
         * @return
         */
        @Override
        public boolean hasNext() {
            return current != endMaker;
        }

        /**
         * 获取下一个元素
         * @return
         */
        @Override
        public AnyType next() {

            if(modCount != expectedModCount){
                throw new java.util.ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }

            AnyType data = current.data;
            current = current.next;
            this.okToRemove = true;
            return data;
        }

        /**
         * 迭代器的删除操作
         */
        public void remove(){
            if(modCount != expectedModCount){
                throw new java.util.ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }

            MyLinkedListSign.this.remove(MyLinkedListSign.this.getPreNode(current));
            expectedModCount++;
            okToRemove = false;
        }

    }

    public static void main(String[] args) {
        MyLinkedListSign<String> mylinkedList = new MyLinkedListSign<String>();
        mylinkedList.add("first");
        mylinkedList.add("third");
        System.out.println(mylinkedList.size());

        java.util.Iterator<String> itr = mylinkedList.iterator();
        while( itr.hasNext( ) )
        {
            System.out.println(itr.next( ));
            itr.remove( );

        }
        System.out.println(mylinkedList.size());
    }
}
