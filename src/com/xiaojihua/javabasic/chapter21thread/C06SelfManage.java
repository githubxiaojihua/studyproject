package com.xiaojihua.javabasic.chapter21thread;

/**
 * 知识点:
 * 除了实现Runnable、从Thread继承的第三种创建任务的方式，
 * 自管理的方式：实现Runnable，但不是在外部附着到一个线程上而是
 * 在内部有一个Thread。
 *
 * 实现Runnable接口的好处是还可以从其它类继承，但是如果从Thread
 * 继承那么是不能再继承其它类的
 */
public class C06SelfManage implements Runnable {
    private int count = 5;
    //内部Thread
    private Thread t = new Thread(this);

    /**
     * 在构造函数里面start
     */
    public C06SelfManage(){
        t.start();
    }

    public String toString(){
        return Thread.currentThread().getName() + "---" + count;
    }

    @Override
    public void run(){
        while(true){
            System.out.println(this);
            if(--count <= 0){
                return;
            }
        }
    }

    public static void main(String[] args){
        for(int i=0; i<5; i++){
            new C06SelfManage();
        }
    }

}
