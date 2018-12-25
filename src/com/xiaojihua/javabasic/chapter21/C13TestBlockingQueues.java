package com.xiaojihua.javabasic.chapter21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 使用同步队列来实现，生产者与消费者。同步队列作为对于wait和nofifyall的更高的抽象级别，
 * 可以提供更好的编程体验。
 * 知识点：
 * 1、同步队列在任一时刻都只允许一个任务插入和删除元素。
 * 2、BlockingQueue作为接口，ArrayBlockingQueue和LinkedBlockingQueue为主要实现。
 * 3、队列为空的时候挂起消费者任务，不为空时恢复消费者任务
 * 4、下例中将多个LiftOff任务串行化执行了。
 */


/**
 * 消费者。何以忽略同步问题，因为BlockingQueue已经解决了
 */
class LiftOffRunner implements Runnable{
    private BlockingQueue<C01LiftOff> rockets;//同步队列
    public LiftOffRunner(BlockingQueue<C01LiftOff> rockets){
        this.rockets = rockets;
    }
    public void add(C01LiftOff liftOff){
        try {
            rockets.put(liftOff);//压入同步队列
        } catch (InterruptedException e) {
            System.out.println("Interrupted during put()");
        }
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                //从同步队列中获取任务，队列为空的时候阻塞线程
                C01LiftOff rocket = rockets.take();
                rocket.run();//通过消费者所在线程，执行获取的任务
            }
        }catch(InterruptedException e){
            System.out.println("Waking from take()");
        }
        System.out.println("Exiting LiftOffRunner");
    }
}

/**
 * 生产者 + 测试
 */
public class C13TestBlockingQueues {
    /**
     * 静态方法：接收输入流输入信息，但是什么也不做
     */
    static void getKey(){
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 静态方法：打印信息，并且接收输入
     * @param message
     */
    static void getKey(String message){
        System.out.println(message);
        getKey();
    }

    /**
     * 静态方法：
     * 根据不同的BlockingQueue初始化消费者任务
     * 创造线程运行消费者任务
     * 向队列压入5个LiftOff任务，消费者将开始消费
     * 通过输入流分割三次test的调用，便于观察
     * 在每一次单独调用的test中 5个LiftOff任务串行执行，并且没有任务的时候，调用线程是阻塞的
     * @param msg
     * @param queue
     */
    static void test(String msg, BlockingQueue<C01LiftOff> queue){
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread thread = new Thread(runner);
        thread.start();
        for(int i = 0; i < 5; i++){
            runner.add(new C01LiftOff());
        }
        getKey("press 'Enter' (" + msg + ")");
        thread.interrupt();
        System.out.println("Finished " + msg + "test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue", new LinkedBlockingQueue<>());
        test("ArrayBlockingQueue", new ArrayBlockingQueue<>(3));
        test("SynchronousQueue", new SynchronousQueue<>());
        /*Thread t = new Thread(new AddLiftOff(new LinkedBlockingQueue<>()));
        t.start();*/
    }
}

/**
 * 750页练习29
 */
class AddLiftOff implements Runnable{
    private LiftOffRunner runner;
    public AddLiftOff(BlockingQueue<C01LiftOff> blockingQueue){
        runner = new LiftOffRunner(blockingQueue);
    }
    public void run(){
        addLiftOffs();
        runner.run();

    }
    private void addLiftOffs(){
        for(int i = 0; i < 5; i++){
            runner.add(new C01LiftOff());
        }
    }
}