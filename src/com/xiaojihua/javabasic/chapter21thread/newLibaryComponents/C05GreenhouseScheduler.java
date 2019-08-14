package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 通过ScheduledThreadPoolExecutor来提供定时执行和定时循环执行的线程池。
 * 改写原来的GreenHoseController程序。
 */
public class C05GreenhouseScheduler {

    //共享资源，因此需要设置volatile
    private volatile boolean lightOn = false;
    private volatile boolean waterOn = false;

    //共享资源其可视性通过 get 和 set 方法的synchronized来保证
    private String thermostat = "Day";

    public synchronized String getThermostat(){ return thermostat; }
    public synchronized void setThermostat(String thermostat){ this.thermostat = thermostat; }

    //创建一个ScheduledThreadPoolExecutor缓冲10个线程
    ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

    //内部调用ScheduleThreadPoolExecutor的schedule方法来完成定时器
    public void schedule(Runnable runnable, long delay){
        scheduler.schedule(runnable, delay, TimeUnit.MILLISECONDS);
    }

    //内部调用ScheduleThreadPoolExecutor的scheduleAtFixedRate方法来完成循环任务
    public void repeat(Runnable runnable, long delay, long repeat){
        scheduler.scheduleAtFixedRate(runnable,delay,repeat,TimeUnit.MILLISECONDS);
    }

    class LightOn implements Runnable{
        @Override
        public void run(){
            System.out.println("set greenhose light on");
            lightOn = true;
        }
    }
    class LightOff implements Runnable{
        @Override
        public void run(){
            System.out.println("set greenhose lightOff");
            lightOn = false;
        }
    }
    class WaterOn implements Runnable{
        @Override
        public void run(){
            System.out.println("set greenhose water on");
            waterOn = true;

        }
    }
    class WaterOff implements Runnable{
        @Override
        public void run(){
            System.out.println("set greenhose water off");
            waterOn = false;

        }
    }

    class ThermostatNight implements Runnable{
        @Override
        public void run(){
            System.out.println("set greenhose ThermostatNight");
            setThermostat("Night");
        }
    }

    class ThermostatDay implements Runnable{
        @Override
        public void run(){
            System.out.println("set greenhose ThermostatDay");
            setThermostat("Day");
        }
    }

    class Being implements Runnable{
        @Override
        public void run(){
            System.out.println("Being !!!!!");
        }
    }


    class Terminate implements Runnable{
        @Override
        public void run(){
            System.out.println("shutdown all schedule");
            scheduler.shutdownNow();

            //打印收集的数据信息
            //需要一个单独的进程应为上面的语句会关闭scheduler
            new Thread(){
                @Override
                public void run(){
                    for(DataItem dataItem : datas){
                        System.out.println(dataItem);
                    }
                }
            }.start();
        }
    }

    static class DataItem{
        private final Calendar time;
        private final float temperature;
        private final float humidity;

        public DataItem(Calendar time, float temperature, float humidity){
            this.time = time;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        @Override
        public String toString(){
            return time.getTime() + String.format(" temperature:%1$.1f  humidity:%2$.2f ",temperature,humidity);
        }
    }

    private List<DataItem> datas = Collections.synchronizedList(new ArrayList<>());
    //需要单独学习一下Calendar类的使用
    private Calendar time = Calendar.getInstance();
    {
        time.set(Calendar.MINUTE,30);
        time.set(Calendar.SECOND,00);
    }
    private float temperature = 65.0f;
    private int tempDirect = +1;
    private float humidity = 50.0f;
    private int humidityDirect = +1;
    private Random random = new Random(47);

    class CollectData implements Runnable{
        @Override
        public void run(){
            System.out.println("Collecting data");
            //个人感觉加不加synchronized关键子在这里不重要
            synchronized(C05GreenhouseScheduler.this){
                time.set(Calendar.MINUTE,(time.get(Calendar.MINUTE) + 30));
                if(random.nextInt(5) == 4){
                    tempDirect = -tempDirect;
                }
                temperature =  temperature + tempDirect * (1.0f + random.nextFloat());

                if(random.nextInt(5) == 4){
                    humidityDirect = -humidityDirect;
                }
                humidity =  humidity + humidityDirect * random.nextFloat();

                datas.add(new DataItem((Calendar) time.clone(),temperature,humidity));
            }

        }
    }



    public static void main(String[] args){
        C05GreenhouseScheduler greenHorse = new C05GreenhouseScheduler();
        greenHorse.schedule(greenHorse.new Terminate(),5000);

        greenHorse.repeat(greenHorse.new Being(),0,1000);
        greenHorse.repeat(greenHorse.new ThermostatNight(),0,2000);
        greenHorse.repeat(greenHorse.new LightOn(),0,200);
        greenHorse.repeat(greenHorse.new LightOff(),0,400);
        greenHorse.repeat(greenHorse.new WaterOn(),0,600);
        greenHorse.repeat(greenHorse.new WaterOff(),0,800);

        greenHorse.repeat(greenHorse.new ThermostatDay(),0,1400);
        greenHorse.repeat(greenHorse.new CollectData(),500,500);
    }



}
