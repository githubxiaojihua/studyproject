package com.xiaojihua.javabasic.chapter21thread.deadLock;

public class C01Chopstick {
    private static boolean hasTacks = false;

    synchronized public void tack() throws InterruptedException{
        while(hasTacks) {
            wait();
        }
        hasTacks = true;

    }

    synchronized public void dropDown(){
        hasTacks = false;
        notifyAll();
    }


}
