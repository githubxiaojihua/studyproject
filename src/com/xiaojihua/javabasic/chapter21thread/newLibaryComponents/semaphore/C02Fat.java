package com.xiaojihua.javabasic.chapter21thread.newLibaryComponents.semaphore;

public class C02Fat {
    private static int count = 0;
    private final int id = count++;
    private volatile double d;

    public void operation(){
        System.out.println(this);
    }

    public C02Fat(){
        for(int i=0; i<10000; i++){
            d += (Math.PI + Math.E) / (double)i;
        }
    }

    @Override
    public String toString(){
        return " Fat:" + id;
    }

}
