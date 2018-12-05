package com.xiaojihua.javabasic.chapter21;

/**
 * 知识点：
 * 1、join()方法的使用，当前线程在另一个线程上调用join方法，当前线程将等待（挂起）一段时间(可以设置超时时间)，等另一个线程执行完毕
 * 或者超时，当前线程在继续执行。
 * 2、interrupted方法：调用join方法可以被中断，使用interrupted方法。
 */
class Sleeper extends Thread{
    private int sleepTime;

    Sleeper(String name, int sleepTime){
        super(name);
        this.sleepTime = sleepTime;
        start();
    }

    public void run(){
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was Interrupted! isInterrupted():" + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened!");
    }

}

class Joiner extends Thread{
    private Sleeper sleeper;
    Joiner(String name, Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run(){
        try {
            sleeper.join();//join，当前线程挂起，可以设置超时毫秒数
        } catch (InterruptedException e) {
            System.out.println("Joiner was Interrupted!");
        }
        System.out.println(getName() + "Joiner has completed!");
    }
}

public class C08Joining {
    public static void main(String[] args) {
        Sleeper sleeper1 = new Sleeper("sleeper1",1500);
        Joiner joiner1 = new Joiner("joiner1",sleeper1);
        sleeper1.interrupt();//中断join方法的调用
    }



}
