package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * 演示priorityBlockingQueue，优先阻塞队列。
 * priorityBlockingQueue是一个优先队列，对于入队没有阻塞机制，但是对于提取是有阻塞机制的，
 * 另外其中存储的元素必须是实现Comparable接口的，否则会报ClassCastException.
 * 对于相同优先级的对象priorityBlockingQueue不保证其执行顺序，如果一定要确定执行顺序的话
 * 可以使用单独的Comparator接口实现类。
 *
 * priorityBlockingQueue的优先机制是跟PriorityQueue一样的
 *
 * PriorityProductor和PriorityConsumer通过PriorityBlockingQueue实现了解耦合。
 * PriorityBlockingQueue提供了必要的synchronized机制，因此不需要额外的同步代码。
 * 当队列中没有更多的元素来提供消费的话，那么就会阻塞消费进程。
 */
public class C04PriorityBlockingQueueDemo {
    public static void main(String[] args){
        PriorityBlockingQueue<PriorityTask> queue = new PriorityBlockingQueue<>();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new PriorityProductor(queue,service));
        service.execute(new PriorityConsumer(queue));
    }
}

/**
 * 用于加入到PriorityBlockingQueue中的对象，同样Runnable接口其实并非是必须要实现的。
 * Comparable接口是必须要实现的
 */
class PriorityTask implements Runnable, Comparable<PriorityTask>{
    private static int count = 0;
    private final int id = count++;
    private final int priority;
    private static List<PriorityTask> taskList = new ArrayList<>();
    private Random random = new Random(47);

    public PriorityTask(int priority){
        this.priority = priority;
        taskList.add(this);
    }

    @Override
    public String toString(){
        return String.format("%1$-3d",priority) + " Task " + id;
    }

    @Override
    public int compareTo(PriorityTask task){
        return this.priority < task.priority ? 1: (this.priority > task.priority ? -1 : 0);
    }

    @Override
    public void run(){
        try{
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        }catch(InterruptedException e){
            //认为是正常的退出因此什么也不打印
        }
        System.out.println(this);
    }

    public String perform(){
        return "(" + id + " : " + priority + ")";
    }

    /**
     * 末端哨兵
     */
    static class EndSential extends PriorityTask{
        private ExecutorService service;
        public EndSential(int priority, ExecutorService service){
            super(priority);
            this.service = service;
        }

        @Override
        public void run(){
            for(PriorityTask task : taskList){
                System.out.println(task.perform());
            }
            System.out.println("shut down all thread!");
            service.shutdownNow();
        }
    }

}

/**
 * 生产者
 */
class PriorityProductor implements Runnable{
    private Queue<PriorityTask> taskQueue;
    private static Random random = new Random(47);
    private ExecutorService service;

    public PriorityProductor(Queue<PriorityTask> taskQueue,ExecutorService service){
        this.taskQueue = taskQueue;
        this.service = service;
    }

    @Override
    public void run(){
        //无界限队列因此永远不会阻塞
        for(int i=0; i<20; i++){
            taskQueue.add(new PriorityTask(random.nextInt(10)));
            //为了让消费进程也尽快消费
            Thread.yield();
        }

        try{
            //最高优先级
            for(int i=0; i<10; i++){
                TimeUnit.MILLISECONDS.sleep(250);
                taskQueue.add(new PriorityTask(10));
            }
        }catch(InterruptedException e){
            //认为是正常退出因此什么也不干
        }

        for(int i=0; i<10; i++){
            taskQueue.add(new PriorityTask(i));
        }
        taskQueue.add(new PriorityTask.EndSential(-1,service));
        System.out.println("Finished PrioritiedTaskProducer");
    }
}

/**
 * 消费者
 */
class PriorityConsumer implements Runnable{
    private PriorityBlockingQueue<PriorityTask> priorityQueue;

    public PriorityConsumer(PriorityBlockingQueue<PriorityTask> priorityQueue){
        this.priorityQueue = priorityQueue;
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                //使用当前线程来运行每一个任务
                priorityQueue.take().run();
            }
        }catch(InterruptedException e){
            System.out.println("consumer has interrupted!");
        }
        System.out.println("Finished PrioritedTaskConsumer.");
    }
}
