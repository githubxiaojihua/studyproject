package com.xiaojihua.javabasic;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.xiaojihua.javabasic.util.Print.*;

public class Test{

    public static void main(String[] args) throws Exception{
        Restaurant restaurant = new Restaurant();
        Chief chief = new Chief(restaurant);
        Waiter waiter = new Waiter(restaurant);
        restaurant.setChief(chief);
        restaurant.setWaiter(waiter);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(chief);
        service.execute(waiter);

        TimeUnit.SECONDS.sleep(10);
        service.shutdownNow();
    }

}


class Meil{
    private static int sid = 0;
    private int id = sid++;

    @Override
    public String toString() {
        return "Meil{" +
                "id=" + id +
                '}';
    }
}

class Waiter implements Runnable{

    private Restaurant restaurant;
    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.getMeil() == null){
                        wait();
                    }
                    System.out.println("waiter got :" + restaurant.getMeil());
                    TimeUnit.MILLISECONDS.sleep(1000);
                    restaurant.setMeil(null);
                }

                synchronized (restaurant.getChief()){
                    restaurant.getChief().notifyAll();
                }
            }

        }catch(InterruptedException e){
           // e.printStackTrace();
            System.out.println("Interrupted by exception");
        }

    }
}

class Chief implements Runnable{
    private Restaurant restaurant;

    public Chief(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized(this){
                    while(restaurant.getMeil() != null){
                        wait();
                    }
                    TimeUnit.MILLISECONDS.sleep(1000);
                    restaurant.setMeil(new Meil());
                    System.out.println("order up!");
                }

                synchronized (restaurant.getWaiter()){
                    restaurant.getWaiter().notifyAll();
                }
            }
        }catch(InterruptedException e){
            //e.printStackTrace();
            System.out.println("Interrupted by exception");
        }
    }
}

class Restaurant {
    private Chief chief;
    private Waiter waiter;
    private Meil meil;

    public Restaurant() {
    }

    public Chief getChief() {
        return chief;
    }

    public void setChief(Chief chief) {
        this.chief = chief;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Meil getMeil() {
        return meil;
    }

    public void setMeil(Meil meil) {
        this.meil = meil;
    }
}