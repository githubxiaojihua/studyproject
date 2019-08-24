package com.xiaojihua.javabasic.chapter21thread.deadLock;

/**
 * 哲学家吃饭的筷子实例，一只筷子不能同时被两个哲学家拿起
 */
public class C01Chopstick {
    //是否已经被拿起了，原来写的是static的，不能是static的，这样所有筷子都是一个标志了
    private boolean hasTacks = false;

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
