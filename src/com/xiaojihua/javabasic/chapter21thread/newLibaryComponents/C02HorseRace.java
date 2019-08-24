package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 知识点:
 *  java.util.concurrent包中的工具类之：CyclicBarrier的使用
 *  CyclicBarrier(num，Runnable对象)，当在CyclicBarrier上调用
 *  await()方法而等待的线程数量达到num的时候，会先执行Runnable对象
 *  然后唤醒所有等待的线程，进入下一轮，周而复始，直到所有线程结束。
 *
 *  下面的例子模拟了一个赛马游戏，
 *  不断显示每匹马的进度，最后打印胜出的马
 */
public class C02HorseRace {
    private CyclicBarrier barrier;
    private static final int FINISH = 70;
    private List<Horse> horseList = new ArrayList<>();
    private ExecutorService service = Executors.newCachedThreadPool();

    /**
     * 在初始化的时候进行操作
     */
    public C02HorseRace(int horseNum, int paseTime){
        //初始化CyclicBarier
        barrier = new CyclicBarrier(horseNum,()->{
            StringBuilder finishStr = new StringBuilder();
            for(int i=0; i<FINISH; i++){
                finishStr.append("=");
            }
            System.out.println(finishStr);

            for(Horse h : horseList){
                System.out.println(h.track());
            }

            for(Horse h : horseList){
                if(h.getSteps() >= FINISH){
                    System.out.println(h + " win!!!!");
                    service.shutdownNow();
                    /*
                        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        这里的return比较关键，
                        return是退出当前方法
                        并不是退出当前循环退出当前循环是
                        continue和break
                        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        如果这里不退出的话，会继续执行下面的sleep语句
                        会导致在sleep的过程中被主动中断而报错，程序也将不会结束，
                        虽然不在有输出，但是也不会结束
                     */
                    return;
                }
            }
            try{
                TimeUnit.MILLISECONDS.sleep(paseTime);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        });

        for(int i=0; i<horseNum; i++){
            Horse horse = new Horse(barrier);
            horseList.add(horse);
            service.execute(horse);
        }



    }

    public static void main(String[] args){
        //这里直接调用构造方法
        new C02HorseRace(6,200);
    }
}

/**
 * 赛马对象
 */
class Horse implements Runnable{
    private static int count = 0;
    private final int id = count++;
    private CyclicBarrier cyclic;
    private int steps;
    private static Random random = new Random(47);

    public Horse(CyclicBarrier cyclic){
        this.cyclic = cyclic;
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                synchronized(this){
                    steps += random.nextInt(3);
                }
                //System.out.println(this + " step++");
                //在CyclicBarrier上调用await()方法，挂起当前进程
                cyclic.await();
            }


        }catch(InterruptedException e){
            System.out.println("InterruptedException " + e);
        }catch(BrokenBarrierException exception){
            System.out.println("BrokenBarrierException " + exception);
        }

    }

    /**
     * 获取马已经跑的步数
     * @return
     */
    synchronized public int getSteps(){
        return steps;
    }

    /**
     * 返回马的赛跑进度
     * @return
     */
    public String track(){
        StringBuilder builer = new StringBuilder();
        for(int i=0; i<steps; i++){
            builer.append("*");
        }
        builer.append(id);
        return builer.toString();
    }

    @Override
    public String toString(){
        return "Horse:" + id;
    }
}
