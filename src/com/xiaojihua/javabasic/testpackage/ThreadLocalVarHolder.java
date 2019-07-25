package com.xiaojihua.javabasic.testpackage;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalVarHolder {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        private Random random = new Random(47);
        @Override
        protected Integer initialValue(){
            return random.nextInt(1000);
        }
    };

    public static void increment(){
        threadLocal.set(threadLocal.get() + 1);
    }

    public static int get(){
        return threadLocal.get();
    }

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0; i<5; i++){
            service.execute(new Acceccor(i));
        }

        try{
            TimeUnit.MILLISECONDS.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        service.shutdownNow();

    }
}

class Acceccor implements Runnable{
    private final int id;
    public Acceccor(int id){
        this.id = id;
    }

    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            ThreadLocalVarHolder.increment();
            System.out.println("#" + this + "," + ThreadLocalVarHolder.get());
        }
    }

    @Override
    public String toString(){
        return "#id" + ":" + id;
    }

}