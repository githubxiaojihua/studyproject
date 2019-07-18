package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 如果一个线程是daemon那么它所产生的线程也自动是daemon
 */
public class C03Daemons {
    public static void main(String[] args) throws InterruptedException{
        Thread demon = new Thread(new Deamon());
        demon.setDaemon(true);
        demon.start();

        TimeUnit.SECONDS.sleep(1);
    }
}

class DaemonSpawn implements Runnable{
    @Override
    public void run(){
        while(true){
            Thread.yield();
        }
    }
}

class Deamon implements Runnable{
    private Thread[] ts = new Thread[10];
    @Override
    public void run(){
        for(int i=0; i<ts.length; i++){
            ts[i] = new Thread(new DaemonSpawn());
            ts[i].start();
            System.out.println("Daemonspawn " + i + " is start");
        }

        for(int i=0; i<ts.length; i++){
            System.out.println("ts[" + i + "] is deamon : " + ts[i].isDaemon());
        }
        while(true){
            Thread.yield();
        }
    }
}