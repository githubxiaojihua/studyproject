package com.xiaojihua.javabasic.chapter21.shareResource;

import java.util.concurrent.Executor;
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
 */
public class AtomicityTest implements Runnable {
    private int i = 0;
    synchronized public int getValue(){ return i; }
    synchronized public void evenIncreatement(){ i++; i++;}
    @Override
    public void run() {
        while (true){
            evenIncreatement();
        }
    }

    public static void main(String[] args) {
        //启动一个新的线程
        ExecutorService service = Executors.newCachedThreadPool();
        AtomicityTest atomicityTest = new AtomicityTest();
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
