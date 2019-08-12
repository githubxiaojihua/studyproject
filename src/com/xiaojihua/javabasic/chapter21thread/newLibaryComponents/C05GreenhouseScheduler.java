package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class C05GreenhouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";

    synchronized public String getThermostat(){
        return thermostat;
    }

    synchronized public void setThermostat(String thermostat){
        this.thermostat = thermostat;
    }

    ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable runnable, long delay){
        scheduler.schedule(runnable,delay, TimeUnit.MILLISECONDS);
    }

    public void scheduleRate(Runnable runnable,long delay, long repeat){
        scheduler.scheduleAtFixedRate(runnable,delay,repeat,TimeUnit.MILLISECONDS);
    }

    /*class LightOn implements Runnable{}

    class LightOff implements Runnable{}

    class WaterOn implements Runnable{}

    class WaterOff implements Runnable{}

    class Bing implements Runnable{}*/






}
