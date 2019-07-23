package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 知识点：
 * 通过一个适用于多线程的serialNumber生成器来理解，synchronized和线程安全。
 * 相关的类包括：
 * C05SerialNumberCheck,C05SerialNumberGen和本类
 * C05SerialNumberCheck,C05SerialNumberGen都是在本类中使用多线程访问的共享资源
 * 因此在他们内部只要是会被多线程共同访问的都应该增加synchronized来保证线程安全
 *
 * 如果没有线程安全保障的话，会导致生成重复的serialNumber
 *
 */
public class C05SerialNumberTask {
    private static C05SerialNumberCheck check = new C05SerialNumberCheck(100);
    private static C05SerialNumberGen gen = new C05SerialNumberGen();
    private static final int taskNum = 10;

    static class InnerTask implements Runnable{
        @Override
        public void run(){
            while(true){
                int num = gen.next();
                if(check.contains(num)){
                    System.out.println("depucated num :" + num);
                    System.exit(-1);
                }
                check.add(num);
            }
        }
    }

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0; i<taskNum; i++){
            service.execute(new InnerTask());
        }
        service.shutdown();
    }
}
