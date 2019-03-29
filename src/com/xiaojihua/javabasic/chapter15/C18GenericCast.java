package com.xiaojihua.javabasic.chapter15;

/**
 * 知识点：
 * 1、存储的是Object数组，只是在返回元素的时候才进行强制类型转换。
 *
 */
public class C18GenericCast {
    private static final int SIZE = 10;
    public static void main(String[] args){
        FixedSizeStack<String> fs = new FixedSizeStack<>(SIZE);
        for(String s : "ABCDEFGHIG".split("")){
            fs.push(s);
        }
        for(int i=0; i<SIZE; i++){
            String s = fs.pop();
            System.out.print(s + " ");
        }
    }
}

class FixedSizeStack<T>{
    private Object[] container;
    private int index = 0;
    public FixedSizeStack(int size){
        container = new Object[size];
    }

    public void push(T item){
        container[index++] = item;
    }

    /**
     *
     * @return
     */
    public T pop(){
        return (T)container[--index];
    }
}
