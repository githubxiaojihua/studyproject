package com.xiaojihua.javabasic.chapter21thread.shareResource;

import java.util.concurrent.atomic.AtomicInteger;

public class C05AtomicEvenGenerator extends C01IntGenerator {
    private AtomicInteger autoInt = new AtomicInteger(0);

    @Override
    public int next(){
        return autoInt.addAndGet(2);
    }

    public static void main(String[] args){
        C02EvenChecker.test(new C05AtomicEvenGenerator());
    }


}
