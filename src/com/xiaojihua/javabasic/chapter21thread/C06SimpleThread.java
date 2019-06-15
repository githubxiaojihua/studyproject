package com.xiaojihua.javabasic.chapter21thread;

/**
 * 知识点：
 * 1、通过继承Thread来实现任务。还有一种是通过实现Runnable接口来实现
 */
public class C06SimpleThread extends Thread {
    private int countDown = 10;
    private static int threadId = 0;
    C06SimpleThread(){
        super(Integer.toString(threadId++));//指定线程名称
        start();//start是在构造器中调用
    }
    public String toString(){
        return "#" + getName() + "(" + countDown + ")";//通过getName获取线程名称
    }

    /**
     * 任务对应的run方法
     */
    public void run(){
        while(true){
            if( --countDown < 0){ return; }//退出循环
            System.out.print(this);

        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){
            new C06SimpleThread();
        }
    }
}
