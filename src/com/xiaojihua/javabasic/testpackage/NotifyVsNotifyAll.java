package com.xiaojihua.javabasic.testpackage;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NotifyVsNotifyAll {

    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0; i<5; i++){
            service.execute(new Task1());
        }
        service.execute(new Task2());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            private boolean notifyALL = false;
            @Override
            public void run() {
                if(!notifyALL){
                    System.out.println("\n notify()");
                    Task1.blocker.blockerNotify();
                    notifyALL = true;
                }else{
                    System.out.println("\n notifyAll()");
                    Task1.blocker.blockerNotifyAll();
                    notifyALL = false;
                }
            }
        }, 400, 400);

        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("time cancels");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Task2 notifyall()");
        Task2.blocker.blockerNotifyAll();

        service.shutdownNow();

    }
}

class Blocker{
    synchronized public void waits(){
        try{
            while(!Thread.currentThread().isInterrupted()){
                wait();
                System.out.println(Thread.currentThread() + " ");
            }
        }catch(InterruptedException e){

        }
    }

    synchronized public void blockerNotify(){ notify();}
    synchronized public void blockerNotifyAll(){ notifyAll(); }
}

class Task1 implements Runnable{
    protected static Blocker blocker = new Blocker();

    @Override
    public void run(){
        blocker.waits();
    }
}

class Task2 implements Runnable{
    protected static Blocker blocker = new Blocker();

    @Override
    public void run(){
        blocker.waits();
    }
}