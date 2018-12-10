package com.xiaojihua.datastructure;

import java.util.*;

/**
 * 知识点：
 * 1、实现ArrayList
 * 2、对于标准库中的LIST遍历的时候使用iterator效率会更高
 */
public class C06MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_SIZE = 10;
    private AnyType[] items;//内部实际盛放元素的数组
    private int theSize;//元素数量

    C06MyArrayList(){
        clear();
    }

    public void clear(){
        doclear();
    }

    private void doclear(){
        theSize = 0;
        ensureSize(DEFAULT_SIZE);
    }

    /**
     * 扩展数组长度
     * 同时也可用于缩减数组达到节省空间的目的（当且仅当newSize=size）的时候
     * @param newSize
     */
    public void ensureSize(int newSize){
        if(newSize < theSize){
            return;
        }
        AnyType[] oldItems = items;
        items = (AnyType[])new Object[newSize];
        for(int i = 0; i < theSize; i++){
            items[i] = oldItems[i];
        }
    }

    public boolean isEmpty(){
        return theSize == 0;
    }

    public int size(){
        return theSize;
    }

    /**
     * 节省空间
     */
    public void trimToSize(){
        ensureSize(theSize);
    }

    public AnyType get(int index){
        if(index < 0 || index >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[index];
    }

    public AnyType set(int index,AnyType x){
        if(index < 0 || index >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType oldItem = items[index];
        items[index] = x;
        return oldItem;
    }

    /**
     * 在索引位置插入元素，其他元素后移
     * @param index
     * @param x
     */
    public void add(int index, AnyType x){
        if(index < 0 || index > theSize){
            throw new ArrayIndexOutOfBoundsException();
        }
        if((theSize + 1) == items.length){
            ensureSize(2 * size() + 1);
        }

        for(int i = theSize; i > index; i--){
            items[i] = items[i-1];
        }
        items[index] = x;
        theSize++;

    }

    public void add(AnyType x){
        add(size(), x);
    }

    /**
     * 删除指定位置的元素，其他元素前移
     * @param index
     * @return
     */
    public AnyType remove(int index){
        if(index < 0 || index > size() - 1){
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType old = items[index];
        for(int i = index; i < size(); i++){
            items[i] = items[i+1];
        }
        theSize--;
        return old;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<AnyType>{
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return items[current++];
        }

        /**
         * 因为每次next后才能remove所以要--current。但是这个限制
         * 这里先不实现，可以参考LinkedList的实现
         */
        @Override
        public void remove(){
            C06MyArrayList.this.remove(--current);
        }
    }

}

class TestArrayList {
    public static void main( String [ ] args )
    {
        C06MyArrayList<Integer> lst = new C06MyArrayList<>( );

        for( int i = 0; i < 10; i++ )
            lst.add( i );
        for( int i = 20; i < 30; i++ )
            lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        for(Integer i : lst){
            System.out.print(i + " ");
        }
    }
}


