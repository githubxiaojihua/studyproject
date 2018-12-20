package com.xiaojihua.javabasic.chapter21.shareResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1、演示了程序如何结束多线程，通过设置一个voliate域cancel
 * 2、共享资源的访问
 *
 * 这是一个仿真程序：花园委员会希望了解每天通过多个大门进入公园的总人数，
 * 每个大门都有一个计数器，任何一个大门的计数器增加，那么总计数器增加。
 */

/**
 * 共享的计数器资源
 */
class Count{
    private int count = 0;//计数器
    private Random rand = new Random(47);

    /**
     * 对增加操作实施同步
     * @return
     */
    synchronized public int increment(){
        //这里没有直接使用return ++count，是为了使count赋值给temp
        //到最后++temp复制给count这期间有一半的时间产生让步，如果将
        //synchronized去掉那么程序就会崩溃
        int temp = count;
        if(rand.nextBoolean()){
            Thread.yield();//调用yield的时候并不释放锁
        }
        return (count = ++temp);
    }

    synchronized public int value(){
        return count;
    }
}

/**
 * 每个大门的计数器
 * 自己维护这个一个number，用于将来与总数count进行检查
 *
 */
class Entrence implements Runnable{
    private static Count count = new Count();//共享的资源
    //线程取消标志，使用了可见性标识符，由于每个线程都可以设置此值
    //并且此赋值操作是原子性的，而且要求任何线程更改完此值后，其他线程
    //即刻可以看到，因此没有将其作为synchronized方式来访问，
    //volatile使其具有可视性
    private static volatile boolean cancel = false;

    public static void cancel(){
        cancel = true;
    }
    private static List<Entrence> entrenceList = new ArrayList<>();
    private int number = 0;
    private final int id;

    public Entrence(int id){
        this.id = id;
        entrenceList.add(this);
    }

    @Override
    public void run() {
        while(!cancel){
            //增加Entrence内部的number的值
            //其实这里加不加synchronized无关紧要，因为访问当前实例number的只有一个线程
            synchronized (this){
                ++number;
            }
            //增加共享计数器的值
            System.out.println(this + " Total:" + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //while循环后就开始结束程序。run方法结束就代表结束
        System.out.println("stopping " + this);
    }

    //此处也可以不加synchronized，原因同上
    synchronized public int getValue(){
        return number;
    }

    public String toString(){
        return "Entrence " + id + ":" + getValue();
    }

    //静态方法返回总计数器，数值
    public static int getTotalCount(){
        return count.value();
    }

    //静态方法返回所有子计数器的和
    public static int sumEntrences(){
        int sum = 0;
        for(Entrence entrence : entrenceList){
           sum += entrence.getValue();
        }
        return sum;
    }
}

public class C08OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
        //创建线程
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++){
            service.execute(new Entrence(i));
        }
        //sleep三秒
        TimeUnit.SECONDS.sleep(3);
        //将cancel置为true，所有线程即刻可见
        Entrence.cancel();
        //shutdown，注意他与shutdownNow的区别
        service.shutdown();
        //等待每个任务结束，如果所有任务在超时之前全部结束那么返回true，否则返回false
        if(service.awaitTermination(250,TimeUnit.MILLISECONDS)){
            System.out.println("Some tasks were not terminated!");
        }
        System.out.println("Total:" + Entrence.getTotalCount());
        System.out.println("Sum of Entrences:" + Entrence.sumEntrences());
    }


}
