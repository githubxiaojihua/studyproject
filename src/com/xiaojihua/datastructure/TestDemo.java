package com.xiaojihua.datastructure;

public class TestDemo<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_TREES = 1;
    private Node<AnyType>[] theTrees;
    private int currentSize;

    TestDemo(int size){
        theTrees = new Node[size];
        makeEmpty();
    }

    TestDemo(){
        this(DEFAULT_TREES);
    }

    TestDemo(AnyType x){
        this();
        theTrees[1] = new Node<>(x);
        currentSize = 1;
    }

    public void insert(AnyType x){
        merge(new TestDemo<>(x));
    }

    public Node<AnyType> removeMin(){
        int minIndex = findMinIndex();
        Node<AnyType> item = theTrees[minIndex];
        TestDemo<AnyType> deleteQueue = new TestDemo<>();
        deleteQueue.expandTheTrees(minIndex);
        Node<AnyType> deleteTree = theTrees[minIndex].firstChild;
        deleteQueue.currentSize = (1 << minIndex) -1;

        for(int i = 0; i < minIndex; i++){
            deleteQueue.theTrees[i] = deleteTree;
            deleteTree = deleteTree.nextSibling;
            deleteQueue.theTrees[i].nextSibling = null;
        }

        theTrees[minIndex] = null;
        currentSize = currentSize - deleteQueue.currentSize + 1;

        merge(deleteQueue);
        return item;


    }

    private void merge(TestDemo<AnyType> rhs){
        if(this == rhs){
            return;
        }
        int newSize = currentSize + rhs.currentSize;
        if(newSize > capacity()){
            //扩容
            expandTheTrees(Math.max(theTrees.length, rhs.theTrees.length) + 1);
        }

        Node<AnyType> carr = null;
        for(int i = 0, j = 1; j <= newSize; j *= 2){
            Node<AnyType> t1 = theTrees[i];
            Node<AnyType> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;

            int switchNum = t1 == null ? 0 : 1;
            switchNum += t2 == null ? 0 : 2;
            switchNum += carr == null ? 0 : 4;

            switch (switchNum){
                case 0:
                case 1:
                    break;
                case 2:
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 3:
                    carr = combineTree(t1, t2);
                    theTrees[i] = null;
                    rhs.theTrees[i] = null;
                    break;
                case 4:
                    theTrees[i] = carr;
                    carr = null;
                    break;
                case 5:
                    carr = combineTree(t1, carr);
                    theTrees[i] = null;
                    break;
                case 6:
                    carr = combineTree(t2, carr);
                    rhs.theTrees[i] = null;
                    break;
                case 7:
                    theTrees[i] = carr;
                    carr = combineTree(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }
        for(int i = 0; i < rhs.theTrees.length; i++){
            rhs.theTrees[i] = null;
        }
    }


    public void makeEmpty(){
        currentSize = 0;
        for(int i = 0; i < theTrees.length; i++){
            theTrees[i] = null;
        }
    }

    private int capacity(){
        return 1 << theTrees.length -1;
    }

    private void expandTheTrees(int newSize){
        if(newSize <= theTrees.length){
            return;
        }
        Node<AnyType>[] oldTrees = theTrees;
        theTrees = new Node[newSize];
        for(int i = 0; i < oldTrees.length; i++){
            theTrees[i] = oldTrees[i];
        }
    }

    private Node<AnyType> combineTree(Node<AnyType> t1, Node<AnyType> t2){
        if(t1.element.compareTo(t2.element) > 0){
            return combineTree(t2, t1);
        }
        t2.nextSibling = t1.firstChild;
        t1.firstChild = t2;
        return t1;
    }

    private static class Node<AnyType>{
        AnyType element;
        Node<AnyType> firstChild;
        Node<AnyType> nextSibling;

        Node(AnyType x, Node<AnyType> firstChild, Node<AnyType> nextSibling){
            this.element = x;
            this.firstChild = firstChild;
            this.nextSibling = nextSibling;
        }

        Node(AnyType x){
            this(x, null, null);
        }

    }

    public Node<AnyType> findMin(){
        return theTrees[findMinIndex()];
    }

    private int findMinIndex(){
        int minIndex = 0;
        for(int i = 1; i < theTrees.length; i++){
            if(theTrees[minIndex].element.compareTo(theTrees[i].element) > 0){
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        Integer[] ints = new Integer[10];
        System.out.println(ints[2]);
    }

}
