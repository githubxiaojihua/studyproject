package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.*;

/**
 * 知识点：
 * DelayQueue的使用
 * DelayQueue是无界的BlockingQueue，其存放的对象均是实现了Delayed接口的类，
 * Delayed有一个getDelay()方法来告诉调用者还有多长时间过期以及已经过期了多长时间，
 * 同时DelayQueue还实现了Compared接口因此也必须重写compared方法，来保证元素的有序。
 * DelayeQueue其实也是一种priorityQueue.
 * 如果当前没有delay过期那么调用程序将阻塞，知道有过期的object出现。
 *
 * 本例构造了20个DelayTask，并将其分别放到了一个DelayQueue中和一个List中，用于
 * 观察其是否按照过期时间来排列任务的。
 *
 * 使用TimeUnit类，是一个枚举类，具体类型都有convert方法
 *
 * 从输出可以看出来，DelayQueue是通过过期时间和过期时间长短来优先排列任务的。
 * 与创建时间无关。
 */
public class C03DelayQueueDemo {
    public static void main(String[] args){
        Random random = new Random(47);
        ExecutorService service = Executors.newCachedThreadPool();
        DelayQueue<DelayTask> queue = new DelayQueue<>();
        for(int i=0; i<20; i++){
            queue.put(new DelayTask(random.nextInt(5000)));
        }
        queue.add(new DelayTask.EndGuard(5000,service));

        service.execute(new DelayTaskConsumer(queue));
    }
}

/**
 * 实现一个Delay任务，将来放到了DelayQueue中，不一定要实现Runnable方法，
 * 这里实现Runnable方法是为了使用其run方法。
 * 同时使用末端哨兵来按照创建顺序来打印task
 */
class DelayTask implements Runnable, Delayed{
    private static int count = 0;
    private final int id = count++;
    private final long delayMilliSecond;
    private final long trigger;
    //按照创建顺序存储Task
    private static List<DelayTask> taskList = new ArrayList<>();

    /**
     * 构造方法同时将对象增加到taskList中
     * @param delayMilliSecond
     */
    public DelayTask(long delayMilliSecond){
        this.delayMilliSecond = delayMilliSecond;
        trigger = System.nanoTime() + NANOSECONDS.convert(delayMilliSecond, MILLISECONDS);
        taskList.add(this);
    }

    @Override
    public void run(){
        System.out.println(this + " task " + id);
    }

    @Override
    public String toString(){
        return String.format("[%1$-3d]",delayMilliSecond);
    }

    /**
     * 策略模式。TimeUnit，有不同的具体策略
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit){
        return unit.convert(trigger - System.nanoTime(),NANOSECONDS);
    }

    /**
     * 提供了优先队列的比较方法
     * @param thatDelayed
     * @return
     */
    @Override
    public int compareTo(Delayed thatDelayed){
        DelayTask that = (DelayTask) thatDelayed;
        return (this.trigger < that.trigger) ? -1:(this.trigger > that.trigger ? 1 : 0);
    }

    public String summary(){
        return "(" + id + ":" + delayMilliSecond + ")";
    }

    static class EndGuard extends DelayTask{
        private ExecutorService service;
        public EndGuard(long delayMilliSecond,ExecutorService service){
            super(delayMilliSecond);
            this.service = service;
        }

        @Override
        public void run(){
            for(DelayTask task : taskList){
                System.out.println(task.summary());
            }
            System.out.println("shut down");
            service.shutdownNow();
        }
    }


}

class DelayTaskConsumer implements Runnable{
    private DelayQueue<DelayTask> queue;

    public DelayTaskConsumer(DelayQueue<DelayTask> queue){
        this.queue = queue;
    }
    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                //按照过期时间来优先执行相关任务
                queue.take().run();
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("finished DelayedTaskConsumer");
    }
}
