package com.xiaojihua.javabasic.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1、synchronized并非只用在对于共享资源的竞争上，也用在线程之间的合作上，比如像本例
 * WaitPerson和Chef本身就是一个线程任务，方法上加上synchronized是因为要使用wait和
 * notifyall
 * 2、通过一个生产者消费者模型来，阐述两个线程之间的合作
 * 3、wait和notify都在线程任务中，以前的例子都是在其他类中。
 * 4、体现了面向对象的思维模式。比如本模型：有Meal(菜品类)、WaitPerson（服务员类)、Chef(厨师类)、
 * C12Restaurant(饭店类)。本例区别与其他实例的主要一点是，wait和notify在实现了Runnable的任务类
 * （WaitPerson\Chef）中，而不是在Meal中。如果在Meal中的话也是可以的，但是那就代表WaitPerson和Chef
 * 总是在弄一道菜，不符合逻辑。而现在Chef可以new很多不同的Meal。而WaitPerson和Chef作为线程任务类也
 * 是合适的，因为将来也可以有多个厨师和服务员。
 * 5、notifyall唤醒的是等待当前对象锁的进程。wait是调用当前对象的线程放弃对象的锁，并挂起。当然线程也是
 * 一个对象，可以获得和放弃自己的锁。
 *
 * 本例描述如下模型：
 * 有一个饭店，它有一个初始和服务员，服务员等待厨师出菜。当厨师准备好菜品后，通知服务员
 * ，之后服务员上菜，然后返回继续等待。厨师通知服务员后就等待新订单（服务员下单）
 */

/**
 * 模拟菜品类
 */
class Meal{
    private final int id;
    public Meal(int id){
        this.id = id;
    }
    public String toString(){
        return "Meal " + id;
    }

}

/**
 * 服务员任务
 */
class WaitPerson implements Runnable{
    //存储饭店对象
   private C12Restaurant c12Restaurant;
   public WaitPerson(C12Restaurant restaurant){
       c12Restaurant = restaurant;
   }
   @Override
   public void run(){
       try{
           while(!Thread.interrupted()){
               //获得当前线程的锁，如果meal==null挂起线程
               synchronized (this){
                   //通用写法并且要写到synchronized里面，防止错过信号。
                   while(c12Restaurant.meal == null){
                       wait();
                   }
               }
               System.out.println("wait person got " + c12Restaurant.meal);
                //获得restaurant的chef对象的锁，设置meal为null然后唤醒chef进程
               synchronized (c12Restaurant.chef){
                   c12Restaurant.meal = null;
                   c12Restaurant.chef.notifyAll();
               }

           }
           System.out.println("exiting nomally");
       }catch (InterruptedException e){
           System.out.println("exiting via InterruptedException");
       }

   }

}

/**
 * 厨师任务
 */
class Chef implements Runnable{
    //饭店对象
    private C12Restaurant c12Restaurant;
    //下单数量
    private int count = 0;
    public Chef(C12Restaurant restaurant){
        c12Restaurant = restaurant;
    }
    @Override
    public void run(){

        try{
            while(!Thread.interrupted()){
                //获得厨师对象锁，判断meal！=null的话，chef线程挂起
                synchronized (this){
                    while(c12Restaurant.meal != null){
                        wait();
                    }
                }
                //当下单大于10的时候停止所有进程
                /**
                 * 这里有个小知识点：
                 * 当发送shutdownNow的时候，如果TimeUnit.MILLISECONDS.sleep(100)
                 * 这一行不注销的话chef会通过报异常来中断，如果注销的话，会执行到while
                 * 循环的最后一句，然后在while循环的顶部退出，然后退出run，进而退出线程
                 */
                if(++count == 10){
                    System.out.println("Out of food , closing");
                    c12Restaurant.service.shutdownNow();
                }
                System.out.println("order up!");
                synchronized (c12Restaurant.waitPerson){
                    c12Restaurant.meal = new Meal(count);
                    c12Restaurant.waitPerson.notifyAll();
                }
                //注意，注销此句和不注销是有输出是由差别的。
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println("exitting via InterruptedException");
        }

    }
}

public class C12Restaurant {
    public Meal meal;
    public WaitPerson waitPerson = new WaitPerson(this);
    public Chef chef = new Chef(this);
    public ExecutorService service = Executors.newCachedThreadPool();
    public C12Restaurant(){
        service.execute(chef);
        service.execute(waitPerson);
    }

    public static void main(String[] args) {
        new C12Restaurant();
    }

}
