package com.xiaojihua.javabasic.chapter21thread.deadLock;

import com.xiaojihua.javabasic.chapter21thread.C02SimplePriorities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C03DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws Exception{
        int size = 5;
        int factor = 5;
        C01Chopstick[] chopsticks = new C01Chopstick[size];
        for(int i=0; i<size; i++){
            chopsticks[i] = new C01Chopstick();
        }

        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0; i<size; i++){
            service.execute(new C02Philosopher(chopsticks[i],chopsticks[(i+1)%size],i,factor));
        }

        System.out.println("exist by print something");
        System.in.read();
        service.shutdownNow();
    }
}
