package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1、进程间的合作，通过组合使用snychronized\wait\notify\notifyall。
 * 2、wait\notifty\notifyall必须放在snychronized中。因为这几项操作
 * 都牵扯到释放锁和获得锁。因此加载同步对象上是对的。wait会将获得当前对象
 * 锁的线程挂起，notify和notifyall会唤醒当前对象上被挂起的线程。
 * 3、wait释放锁，挂起程序，sleep和yield不释放锁阻塞程序。
 * 4、wait和notify，notifyall是object类的方法
 *
 * 本例模拟：
 * 两个过程，
 * 1、将蜡涂到car上
 * 2、抛光它。
 * 抛光工作必须在涂蜡任务完成以前是不能开始的
 * 涂蜡任务在上另一层蜡的时候必须在抛光任务完成后开始
 */
class Car1{
    private boolean waxOn = false;//是否已经上蜡

    /**
     * 上蜡，并唤醒所有wait进程
     * waxOn = true
     */
    synchronized public void waxed(){
        waxOn = true;
        notifyAll();
    }

    /**
     * 抛光，并唤醒所有wait进程
     * waxOn = false
     */
    synchronized public void buffed(){
        waxOn = false;
        notifyAll();
    }

    /**
     * 抛光，必须等到上蜡完成即waxOn = true
     * @throws InterruptedException
     */
    synchronized public void waitForWaxint() throws InterruptedException {
        //这里必须用while。原因是要检查所感兴趣的条件，并在条件不满足的时候回到wait中
        //比如刚被唤醒，某个其他任务又将waxOn==false
        //重点：当调用wait的时候程序就停在wait的地方，然后notifyall后会继续执行，也就是
        //接着while循环判断，并不是执行完wait立即再进行while，并且在不停的循环
        while(waxOn == false){
            //System.out.println("test");
            wait();//使用此资源的线程再此处挂起，直到notyfy和notifyall。并且释放锁
            //当在此wait的线程被唤醒的时候必须首先获得在wait时放弃的锁，如果锁不可用则不会被唤醒
        }

    }

    /**
     * 涂另一层蜡，必须等待抛光完成即waxOn = false
     * @throws InterruptedException
     */
    synchronized public void waitForBuffing() throws InterruptedException {
        while(waxOn == true)
            wait();
    }
}

/**
 * 上蜡任务
 */
class WaxOn implements Runnable{
    private Car1 car;
    public WaxOn(Car1 car){
        this.car = car;
    }
    @Override
    public void run(){
       try{
           while(!Thread.interrupted()) {
               System.out.println("wax on");
               TimeUnit.MILLISECONDS.sleep(200);
               //上蜡,waxOn = true，唤醒所有
               car.waxed();
               //如果waxOn = true挂起当前线程，等待notifyAll
               car.waitForBuffing();
           }

       }catch(InterruptedException e){
           System.out.println("Exiting via Interrupted");
       }

       //try catch之外无论是否有异常抛出都执行。但如果打开下面这一句那么是不能执行的，编译也通过不了
        //throw new RuntimeException();
        System.out.println("Ending Wax on task");
    }
}

/**
 * 抛光任务
 */
class WaxOff implements Runnable{
    private Car1 car;
    public WaxOff(Car1 car){
        this.car = car;
    }
    public void run(){
        try{
            while(!Thread.interrupted()){
                //如果waxOn = false挂起当前线程，等待notityAll
                car.waitForWaxint();
                System.out.println("wax off");
                TimeUnit.MILLISECONDS.sleep(200);
                //抛光，waxOn = false，唤醒所有
                car.buffed();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via Interrupted");
        }
        System.out.println("Ending Wax off task");
    }
}

public class C10WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car1 car = new Car1();
        ExecutorService service = Executors.newCachedThreadPool();
        /**
         * 初始状态为waxOn = false
         * 因此调用抛光任务，即可被挂起，
         * 然后调用上蜡任务，上蜡完成后waxOn = true
         * 挂起等待抛光，并唤醒被挂起的抛光，
         * 抛光完成后，waxOn = false，并唤醒挂起的上蜡任务，然后再下一个while，挂起自己
         */
        service.execute(new WaxOff(car));
        service.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        //调用shutdownNow会向所有线程发送Interrupt()
        //当分别去掉上蜡任务和抛光任务的sleep后也能实现中断
        /**
         * 这里有个小的知识点：
         * 1、当将上蜡任务和抛光任务中的sleep语句去掉后，输出将不一样，只会有一个异常输出
         * 原因是，当存在sleep的时候，调用shutdownNow的时候，大多时间都在sleep，而且
         * shutdownNow会向service中的所有线程发送interrup()方法，因此两个任务都会抛出
         * InterruptedException异常进行中断。当都去掉后，因为同一时间只有一个任务在wait
         * 而wait方法本身和抛出InterruptedException，因此当执行如下语句的时候，只有一个
         * 异常输出，另外一个是通过判断Thread.interrupted()来终止的while循环。
         */
        service.shutdownNow();

    }
}
