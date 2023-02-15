package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 知识点：
 * 1、原子操作实例，在本例中，getValue和evenIncreatement两个方法都必须是synchronized不然的话
 * 程序会检测到奇数并且退出。
 * 可以将getValue的synchronized去掉，运行程序进行实验
 * 本实例中有两个线程main和ExecutorService创造的线程
 * 2、由于本例中i为private的，并通过public的两个方法进行访问。那么要达到原子性、可视性可以直接在
 * 方法上使用synchronized，不必使用volatile
 * 3、可以使用原子类对方法进行改进从而去掉synchronized。比如打开注销掉的那几行。
 * 4、当任务本身交给多个线程的时候也会发生线程安全问题
 *
 * 原子操作是不能被线程调用机制中断的操作，一旦操作开始那么它一定可以在可能发生上下文切换之前执行完毕。
 */
public class C05AtomicityTest implements Runnable {
    private int i = 0;
    //private AtomicInteger i = new AtomicInteger(0);
    synchronized public int getValue(){ return i; }
    //public int getValue(){ return i.get(); }
    synchronized public void evenIncreatement(){ i++; i++;}
    /*public void evenIncreatement(){
        i.addAndGet(2);
    }*/
    @Override
    public void run() {
        while (true){
            evenIncreatement();
        }
    }

    public static void main(String[] args) {
        //启动一个新的线程
        ExecutorService service = Executors.newCachedThreadPool();
        C05AtomicityTest atomicityTest = new C05AtomicityTest();
        service.execute(atomicityTest);
        //main线程开始读取并检测
        while(true){
            int val = atomicityTest.getValue();
            if(val % 2 != 0){
                System.out.println(val);
                System.exit(0);//正常终止程序，如果参数值为非零则是异常终止
            }
        }
    }
}
