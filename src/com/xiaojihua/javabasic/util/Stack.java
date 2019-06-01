package com.xiaojihua.javabasic.util;

import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<>();
    public void push(T v){ storage.push(v);}
    public T peek(){ return storage.getFirst(); }
    public T pop(){ return storage.removeFirst(); }
    public boolean isEmpty(){ return storage.isEmpty(); }
    public String toString(){ return storage.toString(); }
}
