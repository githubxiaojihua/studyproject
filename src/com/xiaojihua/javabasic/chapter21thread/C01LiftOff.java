package com.xiaojihua.javabasic.chapter21thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1、Thread.yield()方法的调用是对线程调度器的一个建议，它在声明：程序已经执行完成最后的部分，可以将CPU切换给其他的任务。（并不是强制的）,yield代表让步
 * 2、实现Runable必须实现run方法，但是这个方法并不特殊，它不提供任何内在的线程能力，要实现线程行为必须显式的附着在一个线程上，比如下面的
 * 程序就是附着在main线程上。
 * 3、Runable配合Thread的使用
 * 4、Thread.yield()在多线程中的作用
 * 5、多线程会自动适应多个CPU。开发者无需关心
 * 6、多线程如果是运行在单CPU环境中它的作用在于解决程序的阻塞，也就是说程序的响应性更好，因为并不是顺序执行的所以在一些线程阻塞后程序的其他线程
 * 还是能进行响应。多线程如果是运行在多CPU中那么确实是提高了效率，因为可以将任务自动分配给不同的处理器处理。
 * 7、使用Executor（优先选择），俗成执行器，内部生成一个Thread并且执行对应的任务（Runable，或者继承自Thread）。
 * 8、sleep的两种写法
 *
 * 一些基本概念：
 * 创建任务（C0LiftOff），并通过某种途径将一个线程附着到一个任务上，使得线程驱动任务。
 */
public class C01LiftOff implements Runnable {
    private int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public C01LiftOff(){}
    public C01LiftOff(int countDown){ this.countDown = countDown; }

    public String status(){
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "),";
    }
    @Override
    public void run() {
        try{
            while (countDown-- > 0){
                System.out.print(status());
                //Thread.yield();
                //Thread.sleep(100);// 老写法
                TimeUnit.MICROSECONDS.sleep(100);//新写法
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        //实例一：单独调用run
        //LiftOff liftOff = new LiftOff();
        //liftOff.run();

        //实例二：将Runable对象转换为工作任务的传统方式是把它交给一个Thread构造器
        //新启动了一个线程
        //Thread t = new Thread(new LiftOff());
        //t.start();//在新线程里调用Runable的run方法
        //System.out.println("Waiting for LiftOFF!");//仍然属于main线程，会在倒计时输出前输出

        //实例三：采用多个线程
        //可以注销或者开启Thread.yield()方法观察程序的执行：当开启时候，线程的执行是交叉的，当注销的时候
        //交叉的程度不如开启的时候。
        /*for(int i = 0; i < 5; i++){
            //此处没有提供对于新线程对象的引用，但是垃圾回收器在此线程对应的任务的run方法结束并死亡之前是无法
            //对此对象进行回收的。但是对于非Thread变量则不然。
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOFF!");*/

        //实例四：比较优雅的管理线程，Executor在线程(Thread)和任务（Runnable）之间提供了一个中间层，用于管理线程。
        //ExecutorService(具有线程生命周期管理的Executor，比如shutdown)
        //单个Executor可以被用来创建和管理系统中的所有任务。
        //应该优先选择Executor方式
        ExecutorService service = Executors.newCachedThreadPool();
        // ExecutorService service = Executors.newFixedThreadPool(5);//限制进程池中的进程数量
        //ExecutorService service = Executors.newSingleThreadExecutor();//进程数量为1的进程池。
        for(int i = 0; i < 5; i++){
            service.execute(new C01LiftOff());
        }
        service.shutdown();

    }
}
