package com.xiaojihua.javabasic.chapter21thread.deadLock;

import com.xiaojihua.javabasic.chapter21thread.C02SimplePriorities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C03DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws Exception{
        int size = 5;
        int factor = 5;
        //初始化5只筷子
        C01Chopstick[] chopsticks = new C01Chopstick[size];
        for(int i=0; i<size; i++){
            chopsticks[i] = new C01Chopstick();
        }

        ExecutorService service = Executors.newCachedThreadPool();
        /*
            建立5个哲学家，并且将哲学家安排在相邻的两个筷子中间。这里直接
            使用了筷子数组的下标来定位，第一个哲学家在第0和1个筷子之间。。。。
            最后一个科学家在4和0的筷子之间，构成了闭环形式的资源竞争形态。
         */
        for(int i=0; i<size; i++){

            /*
                死锁的发生需要同时满足4个条件：
                1、互斥条件。使用的资源中至少有一个是不能共享的。本例中是筷子类一次只能
                被一个哲学家使用。
                2、至少有一个任务它必须持有一个资源并且正在等待获取一个当前被别的任务持有
                的资源。就本例而言要发生死锁，哲学家必须拿着一根筷子并且等待另一根在使用的筷子。
                3、资源不能被任务抢占，任务必须把释放资源当作普通事件，不能通过特殊途径抢占任务
                所占有的资源。就本例而言一个哲学家不能直接抢夺别人手里的筷子，必须等待别人自愿放弃。
                4、必须有循环等待。一个任务等待其他任务释放资源，后者又在等待其他任务释放资源，一直
                这样持续下去，直到有一个任务等待第一个任务释放资源，使得大家都被锁住。就本例
                而言每一个哲学家都试图先得到右边的筷子，然后再得到左边的筷子，用餐后，
                也先放下右边的筷子在放下左边的筷子。容易造成循环等待。

                如果要避免死锁或者解除死锁那么就需要破坏掉以上其中任何一条。
                在本例我们破坏掉第四条，目前来看是因为每个哲学家都先拿起右边的筷子造成了循环等待，那么
                我们可以让最后一个哲学家先拿起左边的筷子来破坏掉循环等待的条件，从而避免死锁。
             */
            //避免死锁
            if(i < size -1){
                service.execute(new C02Philosopher(chopsticks[i],chopsticks[i+1],i,factor));
            }else{
                service.execute(new C02Philosopher(chopsticks[0],chopsticks[i],i,factor));
            }

            //有死锁的风险。之所以说有死锁风险是因为，并不是一定会发生。
            //service.execute(new C02Philosopher(chopsticks[i],chopsticks[(i+1)%size],i,factor));
        }

        System.out.println("exist by print something");
        System.in.read();
        /*
            这里配合着哲学家进程的run方法中的try catch语句以及Thread.isInterrupted方法
            来使用。
         */
        service.shutdownNow();
    }
}
