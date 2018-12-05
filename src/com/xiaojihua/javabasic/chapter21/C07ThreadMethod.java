package com.xiaojihua.javabasic.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 知识点：
 * 通过一个单独的方法，使用匿名内部类来实现任务
 * 这里使用的是继承Thread的方式，也可以使用Runnable的方式
 */
public class C07ThreadMethod {
    private int countDown = 5;
    private String name;
    private Thread t;
    C07ThreadMethod(String name){ this.name = name; }

    /**
     * 单独的方法创建线程
     * @return
     */
    public Thread doTask(){
        if(t == null){
            t = new Thread(name){
                public void run(){
                    while(true){
                        System.out.println(this);
                        if(--countDown < 0){ return; }
                    }
                }
                public String toString(){
                    return getName() + ":" + countDown;
                }
            };

        }
        return t;
    }

    public static void main(String[] args) {
        C07ThreadMethod c07ThreadMethod = new C07ThreadMethod("myThreadName");
        //使用Executor管理线程
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(c07ThreadMethod.doTask());
        service.shutdown();
    }
}
